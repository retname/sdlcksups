package com.ordermanager.project.ups.controller;


import com.ordermanager.project.ups.service.ICreateOrder;
import com.ordermanager.project.ups.vo.OrderDto;
import com.ordermanager.project.ups.vo.PrintLabelModel;
import com.ordermanager.project.ups.vo.RequestInfo;
import com.ordermanager.project.ups.vo.Result;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Api请求接口
 */
@RestController
@RequestMapping(value = "/rest/ups")
public class ApiController{


    private static final Map<String, Boolean> ORDER_LOCK_MAP = new HashMap<String, Boolean>(8192);



    @Autowired
    private ICreateOrder iCreateOrder;


    @CrossOrigin(origins = "*")
    @RequestMapping(name = "下单接口",value = "/batchOrder", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Result postCommand(@ApiParam(value = "OrderDto object", required = true) @RequestBody RequestInfo requestInfo) {
        OrderDto orderDto = requestInfo.getBody().getOrder().get(0);
        if(orderDto!=null){
            if(lockOrder(orderDto.getCustomerReferenceNumber())){
                //下单
                Result result = iCreateOrder.batchOrder(orderDto);
                //解锁
                unlockOrder(orderDto.getCustomerReferenceNumber());
                return result;
            }else{
                return Result.failure("单号："+orderDto.getCustomerReferenceNumber()+"正在下单中,请稍后",null);
            }
        }else {
            return Result.failure("订单信息不能为空！",null);
        }
    }



    //获取面单
    @CrossOrigin(origins = "*")
    @RequestMapping(name = "获取面单接口(开发用)",value = "/printLabel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Result postCommandInfoForInterface(@ApiParam(value = "PrintLabel object", required = true) @RequestBody PrintLabelModel pm){
        if(CollectionUtils.isNotEmpty(pm.getOrderCode())){
            Result result=iCreateOrder.printLabel(pm);
            return result;
        }else{
            return Result.failure("请输入订单号！",null);
        }
    }


    //获取面单
    @CrossOrigin(origins = "*")
    @RequestMapping(name = "价格试算",value = "/calPrice", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Result calPrice(@ApiParam(value = "OrderDto object", required = true) @RequestBody RequestInfo requestInfo) {
        OrderDto orderDto = requestInfo.getBody().getOrder().get(0);
        if(orderDto!=null){
            Result result=iCreateOrder.calPrice(orderDto);
            return result;
        }else{
            return Result.failure("订单信息不能为空！",null);
        }
    }



    /**
     * 加锁
     * @param key
     * @return
     */
    private synchronized boolean lockOrder(String key) {
        if (null == ORDER_LOCK_MAP.get(key)) {// 未锁定
            ORDER_LOCK_MAP.put(key, Boolean.TRUE);// 锁定
            return true;
        } else {// 已锁定
            return false;
        }
    }

    /**
     * 解锁
     * @param key
     */
    private synchronized void unlockOrder(String key) {
        ORDER_LOCK_MAP.remove(key);
    }


}
