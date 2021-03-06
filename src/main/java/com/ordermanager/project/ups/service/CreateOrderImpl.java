package com.ordermanager.project.ups.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.ordermanager.common.constant.Constants;
import com.ordermanager.common.exception.BusinessException;
import com.ordermanager.common.utils.Md5Utils;
import com.ordermanager.common.utils.StringUtils;
import com.ordermanager.common.utils.http.ImageFileUtil;
import com.ordermanager.common.utils.http.OkHttpUtils;
import com.ordermanager.project.ups.domain.ApiLog;
import com.ordermanager.project.ups.domain.CreateOrder;
import com.ordermanager.project.ups.domain.SysParam;
import com.ordermanager.project.ups.mapper.ApiLogMapper;
import com.ordermanager.project.ups.mapper.CreateOrderMapper;
import com.ordermanager.project.ups.mapper.ProviceMapper;
import com.ordermanager.project.ups.mapper.SysParamMapper;
import com.ordermanager.project.ups.vo.OrderDeclarationInfoDto;
import com.ordermanager.project.ups.vo.OrderDto;
import com.ordermanager.project.ups.vo.PrintLabelModel;
import com.ordermanager.project.ups.vo.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@Service
public class CreateOrderImpl implements ICreateOrder{


    public static Logger logger= LoggerFactory.getLogger(CreateOrderImpl.class);

    @Autowired(required = false)
    private CreateOrderMapper createOrderMapper;

    @Autowired(required = false)
    private SysParamMapper sysParamMapper;

    @Autowired(required = false)
    private ProviceMapper proviceMapper;

    @Autowired(required = false)
    private ApiLogMapper apiLogMapper;

    @Value("${sdlcups.address}")
    private String address;

    @Value("${sdlcups.addressPrefix}")
    private String addressPrefix;




    @Override
    public CreateOrder getByOrderNumber(String number) {
        CreateOrder createOrder = null;
        if(number!=null){
            createOrder = createOrderMapper.getByOrderNumber(number);
        }
        return createOrder;
    }


    public List<ApiLog> selectApiLogList(ApiLog apiLog) {
        return apiLogMapper.selectApiLogList(apiLog);
    }


    public List<CreateOrder> selectCreateOrderList(CreateOrder createOrder) {
        return createOrderMapper.selectCreateOrderList(createOrder);
    }



    @Override
    public Result batchOrder(OrderDto orderDto) {
        logger.info("enter UPS createOrder interface");
        Result result=new Result();
        boolean isFlag = true;
        try {
            //1????????????????????????????????????????????????????????????????????????????????????????????????????????????
            CreateOrder createOrder =getByOrderNumber(orderDto.getCustomerReferenceNumber());
            if(createOrder!=null&&createOrder.getResponseStatus().equals("200")){
                Map<String,Object> map=new HashMap<>();
                map.put("orderCode",createOrder.getOrderNumber());
                map.put("trackingNumber",createOrder.getTrackingNumber());
                return Result.ok("???????????????????????????",map);
            }else if(createOrder!=null){
                isFlag = false;
            }else{
                createOrder = new CreateOrder();
                createOrder.setOrderNumber(orderDto.getCustomerReferenceNumber());
            }
            //????????????????????????????????????
            /*if(orderDto.getShipperProvince().length()>2){
                Provice provice = proviceMapper.getByStateName(orderDto.getShipperProvince());
                if(provice!=null){
                    orderDto.setShipperProvince(provice.getStateCode());
                }else{
                    return Result.failure("????????????"+orderDto.getShipperProvince()+"??????????????????",null);
                }
            }*/
            //?????????????????????
            SysParam sysParam = sysParamMapper.getByUrlType(Constants.URL_TYPE_CREATE);
            if(sysParam==null){
                return Result.failure("?????????????????????????????????",null);
            }
            long time = System.currentTimeMillis();
            String sign = getSign(sysParam, time);
            //????????????
            String checkResult = checkAddress(orderDto,time,sign);
            if(StringUtils.isNotEmpty(checkResult)){
                return Result.failure("???????????????",checkResult);
            }
            String jsonParam = soapStr(orderDto,sysParam.getAppKey(),time,sign);    //????????????
            String intervaceResult = OkHttpUtils.post(sysParam.getInterfaceAddress(), jsonParam);   //??????
            if(StringUtils.isNotEmpty(intervaceResult)){   //????????????????????????
                Map<String,Object> intervaceMap = (Map<String, Object>) JSONUtils.parse(intervaceResult);
                String sts  = intervaceMap.get("STS").toString();
                if("OK".equals(sts.toUpperCase())){
                    Map dataMap =  MapUtils.getMap(intervaceMap,"data");
                    createOrder.setOrderNo(dataMap.get("ord_no").toString());
                    createOrder.setPrice(new BigDecimal(dataMap.get("price").toString()));
                    createOrder.setPriceDetail(dataMap.get("price_detail").toString());
                    createOrder.setResponseStatus("200");
                    createOrder.setResult(sts);
                    Map printLable = getPrintLable(createOrder.getOrderNo(), time, sign);  //????????????
                    if(MapUtils.getString(printLable,"tracking_no")!=null){
                        createOrder.setTrackingNumber(MapUtils.getString(printLable,"tracking_no"));
                        createOrder.setFormat(MapUtils.getString(printLable,"label_type"));
                        createOrder.setLabelUrl(MapUtils.getString(printLable,"label_url"));
                        createOrder.setPngAddress(MapUtils.getString(printLable,"png_address"));
                        createOrder.setPdfAddress(MapUtils.getString(printLable,"pdf_address"));
                    }else{
                        createOrder.setResult(MapUtils.getString(printLable,"errmsg"));
                    }
                    result.setCode("00000");
                    result.setMsg("???????????????");
                    Map resultDataMap = new HashMap();
                    resultDataMap.put("orderCode",createOrder.getOrderNumber());
                    resultDataMap.put("trackingNumber",createOrder.getTrackingNumber());
                    result.setData(resultDataMap);
                }else if("KO".equals(sts.toUpperCase())){
                    String errMsg = MapUtils.getString(intervaceMap,"errmsg");
                   /*createOrder.setResponseStatus("500");
                    createOrder.setResult(errMsg);*/
                    isFlag = false;
                    result.setCode("99999");
                    result.setMsg("????????????"+errMsg);
                }else{
                    result.setCode("99999");
                    result.setMsg("????????????,???????????????????????????");
                }
            }
            //??????createOrder
            createOrder.setCreateTime(new Date());
            if(isFlag){
                createOrderMapper.insertCreateOrder(createOrder);
            }
        }catch (Exception e){
            return Result.failure("????????????",e.getMessage());
        }
        return result;
    }

    @Override
    public Result printLabel(PrintLabelModel pm) {
        Lock lock = new ReentrantLock();
        Result result = new Result();
        List<Map<String,Object>> dataMap = null;
        lock.lock();
        try {
            dataMap= createOrderMapper.getByFormatAndOrderNum(pm.getOrderCode());
            if(dataMap.size()>0){
                long time = System.currentTimeMillis();
                SysParam sysParam = sysParamMapper.getByUrlType(Constants.URL_TYPE_LABEL);  //??????????????????
                //String interfaceAddress =  sysParam.getInterfaceAddress();  //???????????????????????????
                //StringBuilder urlPrefix = new StringBuilder(interfaceAddress.substring(0,interfaceAddress.lastIndexOf("/")));
                String sign = getSign(sysParam, time);
                //??????????????????????????????????????????????????????  ?????????????????????????????????????????????
                Map<String,Object> haveTrackingNumMap = null;
               /* List<String> orderNoList = dataMap.stream().filter(stringObjectMap -> StringUtils.isEmpty(MapUtils.getString(stringObjectMap, "tracking_number")))
                        .map(map -> MapUtils.getString(map, "order_no")).collect(Collectors.toList());*/

                for (int i = 0; i < dataMap.size(); i++) {
                    if(StringUtils.isEmpty(MapUtils.getString(dataMap.get(i),"tracking_number"))){
                        String orderNo = MapUtils.getString(dataMap.get(i),"order_no");
                        Map printLableMap = getPrintLable(orderNo, time, sign);   //??????????????????
                        String trackingNo = MapUtils.getString(printLableMap, "tracking_no");
                        String pdfUrl = MapUtils.getString(printLableMap, "label_url");
                        String pdfAddress = MapUtils.getString(printLableMap, "pdf_address");
                        String pngAddress = MapUtils.getString(printLableMap, "png_address");

                        haveTrackingNumMap = new HashMap<>();
                        haveTrackingNumMap.put("order_no",orderNo);
                        haveTrackingNumMap.put("order_number",MapUtils.getString(dataMap.get(i), "order_number"));
                        haveTrackingNumMap.put("tracking_number",trackingNo);
                        haveTrackingNumMap.put("pdf_address",pdfAddress);
                        haveTrackingNumMap.put("png_address",pngAddress);

                        dataMap.remove(dataMap.get(i));
                        dataMap.add(haveTrackingNumMap);
                        //???????????????????????????
                        createOrderMapper.updateByOrderNo(orderNo,trackingNo,pdfUrl,pdfAddress,pngAddress,"pdf");
                    }
                }
            }

        }catch (Exception e){
            result.setMsg("?????????????????????"+e.getMessage());
            result.setCode("99999");
            return result;
        }finally {
            lock.unlock();
        }
        result.setMsg("?????????????????????");
        result.setCode("00000");
        result.setData(dataMap);
        return result;
    }

    @Override
    public Result calPrice(OrderDto orderDto) {
        Result result=new Result();
        SysParam sysParam = sysParamMapper.getByUrlType(Constants.URL_TYPE_CALPRICE);
        if(sysParam==null){
            return Result.failure("?????????????????????????????????",null);
        }
        long time = System.currentTimeMillis();
        String sign = getSign(sysParam, time);
        try {
              String checkResult = checkAddress(orderDto,time,sign);
              if(StringUtils.isNotEmpty(checkResult)){
                return Result.failure("???????????????",checkResult);
              }
              String jsonParam = soapStr(orderDto,sysParam.getAppKey(),time,sign);    //????????????
              String intervaceResult = OkHttpUtils.post(sysParam.getInterfaceAddress(), jsonParam);
              if(StringUtils.isNotEmpty(intervaceResult)) {   //????????????????????????
                  Map<String,Object> intervaceMap = (Map<String, Object>) JSONUtils.parse(intervaceResult);
                  String sts  = intervaceMap.get("STS").toString();
                  if("OK".equals(sts.toUpperCase())){
                      Map dataMap =  MapUtils.getMap(intervaceMap,"data");
                      result.setCode("00000");
                      result.setMsg("???????????????");
                      result.setData(dataMap);
                  }else if("KO".equals(sts.toUpperCase())){
                      return Result.failure("",MapUtils.getString(intervaceMap,"errmsg"));
                  }else{
                      return Result.failure("","??????????????????????????????????????????");
                  }
              }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure("????????????",e.getMessage());
        }
        return result;
    }


    /**
     * ??????????????????
     * @param orderNo  ???????????????????????????????????????
     * @return
     */
    private Map getPrintLable(String orderNo,Long time,String sign){
        Map result = new HashMap();
        SysParam urlType = sysParamMapper.getByUrlType(Constants.URL_TYPE_LABEL);
        String interfaceAddress =  urlType.getInterfaceAddress();  //???????????????????????????
        StringBuilder urlPrefix = new StringBuilder(interfaceAddress.substring(0,interfaceAddress.lastIndexOf("/")));
        try {
            if(urlType!=null){
                Map paramMap = new HashMap();
                paramMap.put("ord_no",orderNo);
                Map<String,Object> jsonMap = new HashMap<>();
                jsonMap.put("appKey",urlType.getAppKey());
                jsonMap.put("timestamp",time);
                jsonMap.put("signature",sign);
                jsonMap.put("params",paramMap);
                String jsonResult = OkHttpUtils.post(urlType.getInterfaceAddress(), JSONUtils.toJSONString(jsonMap));
                if(StringUtils.isNotEmpty(jsonResult)){
                    Map<String,Object> resultMap = (Map<String, Object>) JSONUtils.parse(jsonResult);
                    if("KO".equals(MapUtils.getString(resultMap,"STS"))){
                        result.put("errmsg",MapUtils.getString(resultMap,"errmsg"));
                    }else if(("OK".equals(MapUtils.getString(resultMap,"STS")))){
                        List<Map<String,Object>> labelList = (List<Map<String, Object>>) resultMap.get("labels");
                        if(labelList.size()>0){
                            result = labelList.get(0);
                            String labelUrl = urlPrefix.append(MapUtils.getString(result,"label_url")).toString();
                            String trackeingNumber = MapUtils.getString(result,"tracking_no");
                            result.put("label_url",labelUrl);
                            String ourPdfUrl = ImageFileUtil.saveToImgByStr(labelUrl,this.address,trackeingNumber+".pdf",addressPrefix);
                            logger.info("??????????????????pdf?????????"+ourPdfUrl);
                            String pdfAddress = address+"/"+trackeingNumber+".pdf";
                            String pngAddress = address+"/"+trackeingNumber+".png";
                            ImageFileUtil.pdf2img(pdfAddress,pngAddress);   //??????pdf???png
                            String pngUrl = addressPrefix + "/pdf/" + trackeingNumber + ".png";
                            logger.info("??????????????????png?????????"+pngUrl);
                            result.put("pdf_address",ourPdfUrl);
                            result.put("png_address",pngUrl);
                        }
                    }else{
                        result.put("errmsg","??????????????????????????????????????????");
                    }
                }
            }
        }catch (Exception e){
            result.put("errmsg","?????????????????????"+e.getCause());
        }

        return result;
    }


    /**
     * ??????????????????
     * @param orderDto
     * @return
     */
    private String soapStr(OrderDto orderDto,String appkey,Long time,String sign){
        //????????????
        List<Map<String,Object>> productsList = new LinkedList<>();
        Map<String,Object> productsMap = null;
        List<OrderDeclarationInfoDto> declarationInfoDtos = orderDto.getOrderDeclarationInfo();
        if(CollectionUtils.isNotEmpty(declarationInfoDtos)){
            for (OrderDeclarationInfoDto declarationInfoDto : declarationInfoDtos) {
                productsMap =  new LinkedHashMap<>();
                productsMap.put("sku",declarationInfoDto.getPdSku());
                productsMap.put("cn_name",declarationInfoDto.getPdNameCn());
                productsMap.put("en_name",declarationInfoDto.getPdNameEn());
                productsMap.put("hs_code",declarationInfoDto.getPdHscode());
                productsMap.put("quantity",declarationInfoDto.getPdQuantity());
                productsMap.put("price",declarationInfoDto.getPdValue());
                productsMap.put("weight",declarationInfoDto.getPdWeight());
                productsList.add(productsMap);
            }
        }

        //???????????????
        Map<String,Object> senderMap = new LinkedHashMap<>();
        senderMap.put("firstname",orderDto.getShipperContact());
        senderMap.put("lastname","");
        senderMap.put("company",orderDto.getShipperCompany());
        senderMap.put("addr1",orderDto.getShipperAddress());
        senderMap.put("addr2","");
        senderMap.put("city",orderDto.getShipperCity());
        senderMap.put("province",orderDto.getShipperProvince());
        senderMap.put("postcode",orderDto.getShipperPostCode());
        senderMap.put("country",orderDto.getShipperCountry());
        if(orderDto.getShipperTel()!=null){
            senderMap.put("mobile",orderDto.getShipperTel());
        }else{
            senderMap.put("mobile",orderDto.getShipperMobile());
        }
        senderMap.put("email",orderDto.getShipperEmail());

        //???????????????
        Map<String,Object> receiverMap = new LinkedHashMap<>();
        receiverMap.put("firstname",orderDto.getDeliveryContact());
        receiverMap.put("lastname","");
        receiverMap.put("company",orderDto.getDeliveryCompany());
        receiverMap.put("addr1",orderDto.getDeliveryAddress1());
        receiverMap.put("addr2",orderDto.getDeliveryAddress2()+" "+orderDto.getDeliveryAddress3());
        receiverMap.put("city",orderDto.getDeliveryCity());
        receiverMap.put("province",orderDto.getDeliveryProvince());
        receiverMap.put("postcode",orderDto.getDeliveryPostCode());
        receiverMap.put("country",orderDto.getDeliveryCountry());
        if(orderDto.getDeliveryTel()!=null){
            receiverMap.put("mobile",orderDto.getDeliveryTel());
        }else{
            receiverMap.put("mobile",orderDto.getDeliveryMobile());
        }
        receiverMap.put("email",orderDto.getDeliveryEmail());
        receiverMap.put("addr_type",0);    //????????????	0 ?????? 1 ???????????? 2 ????????????

        //other??????
        Map<String,Object> otherMap = new LinkedHashMap<>();
        otherMap.put("label_size","4*6");
        otherMap.put("signature_service",0);
        otherMap.put("remark",orderDto.getRemark());

        //????????????
        Map<String,Object> packageDeatilMap = new LinkedHashMap<>();
        packageDeatilMap.put("weight",orderDto.getCargoTotalWeight());
        packageDeatilMap.put("length",orderDto.getLength());
        packageDeatilMap.put("height",orderDto.getHeight());
        packageDeatilMap.put("width",orderDto.getWidth());
        packageDeatilMap.put("insurance_value",orderDto.getInsuranceAmount());  //????????????
        packageDeatilMap.put("desc","");
        packageDeatilMap.put("value",orderDto.getCargoTotalValue());
        List<Map<String,Object>> packageList = new ArrayList<>();
        packageList.add(packageDeatilMap);

        //????????????
        Map<String,Object> paramMap = new LinkedHashMap<>();
        if(orderDto.getOrderDate()!=null){
            paramMap.put("mailing_date",orderDto.getOrderDate());
        }else{
            paramMap.put("mailing_date",LocalDate.now());
        }
        paramMap.put("reference_no",orderDto.getCustomerReferenceNumber());
        paramMap.put("service_code",orderDto.getServiceType()); //??????????????????????????????
        paramMap.put("packages",packageList);   //????????????
        paramMap.put("others",otherMap);   //????????????
        paramMap.put("receiver",receiverMap); //?????????
        paramMap.put("sender",senderMap);   //?????????
        paramMap.put("products",productsList); //????????????

        Map<String,Object> allJson = new LinkedHashMap<>();
        allJson.put("appKey",appkey);
        allJson.put("timestamp",time);
        allJson.put("signature",sign);
        allJson.put("params",paramMap);
        return JSONUtils.toJSONString(allJson);
    }


    /**
     * ??????????????????????????????????????????????????????
     * @param dto
     * @return
     */
    private String checkAddress(OrderDto dto,Long time,String sign){
        SysParam sysParam = sysParamMapper.getByUrlType(Constants.URL_TYPE_CHECK);
        StringBuilder errorMsg = new StringBuilder();
       //???????????????
        Map shipperMap = new LinkedHashMap();
        shipperMap.put("firstname",dto.getShipperContact());
        shipperMap.put("lastname","");
        shipperMap.put("company",dto.getShipperCompany());
        shipperMap.put("country",dto.getShipperCountry());
        shipperMap.put("addr1",dto.getShipperAddress());
        shipperMap.put("addr2","");
        shipperMap.put("province",dto.getShipperProvince());
        shipperMap.put("city",dto.getShipperCity());
        shipperMap.put("postcode",dto.getShipperPostCode());
        shipperMap.put("mobile",dto.getShipperMobile());
        shipperMap.put("addr_type",0); //????????????	0 ?????? 1 ???????????? 2 ????????????
        shipperMap.put("email",dto.getShipperEmail());

        //???????????????
        Map deliveryMap = new LinkedHashMap();
        deliveryMap.put("firstname",dto.getDeliveryContact());
        deliveryMap.put("lastname","");
        deliveryMap.put("company",dto.getDeliveryCompany());
        deliveryMap.put("country",dto.getDeliveryCountry());
        deliveryMap.put("addr1",dto.getDeliveryAddress1());
        deliveryMap.put("addr2",dto.getDeliveryAddress2()+dto.getDeliveryAddress3());
        deliveryMap.put("province",dto.getDeliveryProvince());
        deliveryMap.put("city",dto.getDeliveryCity());
        deliveryMap.put("postcode",dto.getDeliveryPostCode());
        deliveryMap.put("mobile",dto.getDeliveryMobile());
        deliveryMap.put("addr_type",0);
        deliveryMap.put("email",dto.getDeliveryEmail());

        Map paramJson = new LinkedHashMap();
        paramJson.put("appKey",sysParam.getAppKey());
        paramJson.put("timestamp",time);
        paramJson.put("signature",sign);
        paramJson.put("params",deliveryMap);

        try {
            //???????????????????????????
            String deliveryResult = OkHttpUtils.post(sysParam.getInterfaceAddress(), JSONUtils.toJSONString(paramJson));
            if(StringUtils.isNotEmpty(deliveryResult)){
                Map<String,Object> resultMap = (Map<String,Object>) JSONUtils.parse(deliveryResult);
                if(MapUtils.isNotEmpty(resultMap)){
                    if("KO".equals(resultMap.get("STS").toString().toUpperCase())){
                        errorMsg.append("?????????").append(resultMap.get("errmsg").toString()) ;
                    }
                }
            }
            //???????????????????????????
            paramJson.put("params",shipperMap);
            String shipperResult = OkHttpUtils.post(sysParam.getInterfaceAddress(), JSONUtils.toJSONString(paramJson));
            if(StringUtils.isNotEmpty(shipperResult)){
                Map<String,Object> resultMap = (Map<String,Object>) JSONUtils.parse(shipperResult);
                if(MapUtils.isNotEmpty(resultMap)){
                    if("KO".equals(resultMap.get("STS").toString().toUpperCase())){
                        errorMsg.append("?????????")
                                .append(resultMap.get("errmsg").toString()) ;
                        return errorMsg.toString();
                    }
                }
            }
        }catch (Exception e){
             throw new BusinessException(e.toString());
        }
        return errorMsg.toString();
    }


    /**
     * ???????????? ???????????????????????????
     * sign??????
     * 		MD5(appkey+secret+timestamp)
     * @return
     */
    private  String getSign(SysParam sysParam,Long time){
        StringBuilder signStr = new StringBuilder(sysParam.getAppKey())
                .append(sysParam.getSecret())
                .append(time);
        return Md5Utils.hash(signStr.toString());
    }


    //??????????????????????????? ???30??????????????????
    @Scheduled(cron = "0 0/30 * * * ?")
    public void queryPrintLabel(){
        logger.info("printLabel Timer begin");
        try{
            long time = System.currentTimeMillis();
            SysParam sysParam = sysParamMapper.getByUrlType(Constants.URL_TYPE_LABEL);
            String sign = getSign(sysParam, time);

            /*String interfaceAddress =  sysParam.getInterfaceAddress();  //???????????????????????????
            StringBuilder urlPrefix = new StringBuilder(interfaceAddress.substring(0,interfaceAddress.lastIndexOf("/")));*/

            List<CreateOrder> createOrders =  createOrderMapper.getIsEmptyTrackingNum("200");
            if (CollectionUtils.isNotEmpty(createOrders)) {
                for (CreateOrder createOrder : createOrders) {
                    Map map = getPrintLable(createOrder.getOrderNo(), time, sign);
                    createOrderMapper.updateByOrderNo(createOrder.getOrderNo(),MapUtils.getString(map,"tracking_no"),MapUtils.getString(map, "label_url"),MapUtils.getString(map, "pdf_address"),MapUtils.getString(map, "png_address"),"pdf");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("?????????????????????????????????",e.getMessage());
        }
        logger.info("printLabel Timer end");
    }





}
