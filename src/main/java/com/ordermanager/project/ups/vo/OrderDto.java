package com.ordermanager.project.ups.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.ordermanager.project.ups.domain.CreateOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单实体类
 */
@ApiModel("订单信息")
public class OrderDto implements Serializable {
    private static final long serialVersionUID = 6133982481768974680L;

    @ApiModelProperty(value = "服务商单号,追踪号")
    private String trackingNumber;
    @ApiModelProperty(value = "客方订单号")
    private String customerReferenceNumber;
    @ApiModelProperty(value = "服务商标识")
    private String serviceType;
    @ApiModelProperty(value = "运输方式代码（依据服务商来定）")
    private String baseChannelCode;
    @ApiModelProperty(value = "是否购买保险")
    private Integer insuranceSign;
    @ApiModelProperty(value = "保险金额")
    private double insuranceAmount;
    @ApiModelProperty(value = "敏感货品类型")
    private String goodsType;
    @ApiModelProperty(value = "申报包裹类型")
    private String parcelType;
    @ApiModelProperty(value = "配货总数量")
    private Integer cargoTotalQuantity;
    @ApiModelProperty(value = "配货总重量")
    private double cargoTotalWeight;
    @ApiModelProperty(value = "配货总价值")
    private double cargoTotalValue;
    @ApiModelProperty(value = "币种")
    private String currency;
    @ApiModelProperty(value = "是否需要退件")
    private Integer returnSign;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "包裹标志", allowableValues = "0，未确认；1、已确认；2、已预报；3、已收货；4、正处理；5、已发货、6、异常包裹")
    private Integer orderStatus;
    @ApiModelProperty(value = "一票多件的件数")
    private Integer totalPieces;
    @ApiModelProperty(value = "长")
    private double length;
    @ApiModelProperty(value = "宽")
    private double width;
    @ApiModelProperty(value = "高")
    private double height;
    @ApiModelProperty(value = "口岸名称")
    private String portName;

    @ApiModelProperty(value = "订单日期 YYYY-MM-DD")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    // 收发件人信息
    @ApiModelProperty(value = "寄件方公司名称")
    private String shipperCompany;
    @ApiModelProperty(value = "寄件方联系人")
    private String shipperContact;
    @ApiModelProperty(value = "寄件方联系电话")
    private String shipperTel;
    @ApiModelProperty(value = "寄件方手机")
    private String shipperMobile;
    @ApiModelProperty(value = "寄件方邮箱")
    private String shipperEmail;
    @ApiModelProperty(value = "寄件方详细地址")
    private String shipperAddress;
    @ApiModelProperty(value = "寄件方国家简码")
    private String shipperCountry;
    @ApiModelProperty(value = "寄件方所在省")
    private String shipperProvince;
    @ApiModelProperty(value = "寄件方所属城市")
    private String shipperCity;
    @ApiModelProperty(value = "寄件方邮编")
    private String shipperPostCode;
    @ApiModelProperty(value = "收件人公司名")
    private String deliveryCompany;
    @ApiModelProperty(value = "收件人名")
    private String deliveryContact;
    @ApiModelProperty(value = "收件人联系电话")
    private String deliveryTel;
    @ApiModelProperty(value = "收件人手机")
    private String deliveryMobile;
    @ApiModelProperty(value = "收件人邮箱")
    private String deliveryEmail;
    @ApiModelProperty(value = "收件人地址1")
    private String deliveryAddress1;
    @ApiModelProperty(value = "收件人地址2")
    private String deliveryAddress2;
    @ApiModelProperty(value = "收件人地址3")
    private String deliveryAddress3;
    @ApiModelProperty(value = "目的国家简码")
    private String deliveryCountry;
    @ApiModelProperty(value = "收件人州或省")
    private String deliveryProvince;
    @ApiModelProperty(value = "收件人城市")
    private String deliveryCity;
    @ApiModelProperty(value = "收件人邮编")
    private String deliveryPostCode;
    @ApiModelProperty(value = "收件人税号")
    private String deliveryTariff;

    // 申报信息
    @ApiModelProperty(value = "申报信息")
    @JsonProperty("orderDeclarationInfo")
    private List<OrderDeclarationInfoDto> orderDeclarationInfo = new ArrayList<>();

    public OrderDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }

    public void setCustomerReferenceNumber(String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }

    public String getBaseChannelCode() {
        return baseChannelCode;
    }

    public void setBaseChannelCode(String baseChannelCode) {
        this.baseChannelCode = baseChannelCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getInsuranceSign() {
        return insuranceSign;
    }

    public void setInsuranceSign(Integer insuranceSign) {
        this.insuranceSign = insuranceSign;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getParcelType() {
        return parcelType;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public Integer getCargoTotalQuantity() {
        return cargoTotalQuantity;
    }

    public void setCargoTotalQuantity(Integer cargoTotalQuantity) {
        this.cargoTotalQuantity = cargoTotalQuantity;
    }

    public double getCargoTotalWeight() {
        return cargoTotalWeight;
    }

    public void setCargoTotalWeight(double cargoTotalWeight) {
        this.cargoTotalWeight = cargoTotalWeight;
    }

    public double getCargoTotalValue() {
        return cargoTotalValue;
    }

    public void setCargoTotalValue(double cargoTotalValue) {
        this.cargoTotalValue = cargoTotalValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getReturnSign() {
        return returnSign;
    }

    public void setReturnSign(Integer returnSign) {
        this.returnSign = returnSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(Integer totalPieces) {
        this.totalPieces = totalPieces;
    }

    public String getShipperCompany() {
        return shipperCompany;
    }

    public void setShipperCompany(String shipperCompany) {
        this.shipperCompany = shipperCompany;
    }

    public String getShipperContact() {
        return shipperContact;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDeliveryAddress1() {
        return deliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return deliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public String getDeliveryAddress3() {
        return deliveryAddress3;
    }

    public void setDeliveryAddress3(String deliveryAddress3) {
        this.deliveryAddress3 = deliveryAddress3;
    }

    public void setShipperContact(String shipperContact) {
        this.shipperContact = shipperContact;
    }

    public String getShipperTel() {
        return shipperTel;
    }

    public void setShipperTel(String shipperTel) {
        this.shipperTel = shipperTel;
    }

    public String getShipperMobile() {
        return shipperMobile;
    }

    public void setShipperMobile(String shipperMobile) {
        this.shipperMobile = shipperMobile;
    }

    public String getShipperEmail() {
        return shipperEmail;
    }

    public void setShipperEmail(String shipperEmail) {
        this.shipperEmail = shipperEmail;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public String getShipperProvince() {
        return shipperProvince;
    }

    public void setShipperProvince(String shipperProvince) {
        this.shipperProvince = shipperProvince;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperPostCode() {
        return shipperPostCode;
    }

    public void setShipperPostCode(String shipperPostCode) {
        this.shipperPostCode = shipperPostCode;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryContact() {
        return deliveryContact;
    }

    public void setDeliveryContact(String deliveryContact) {
        this.deliveryContact = deliveryContact;
    }

    public String getDeliveryTel() {
        return deliveryTel;
    }

    public void setDeliveryTel(String deliveryTel) {
        this.deliveryTel = deliveryTel;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPostCode() {
        return deliveryPostCode;
    }

    public void setDeliveryPostCode(String deliveryPostCode) {
        this.deliveryPostCode = deliveryPostCode;
    }

    public String getDeliveryTariff() {
        return deliveryTariff;
    }

    public void setDeliveryTariff(String deliveryTariff) {
        this.deliveryTariff = deliveryTariff;
    }

    public List<OrderDeclarationInfoDto> getOrderDeclarationInfo() {
        return orderDeclarationInfo;
    }

    public void setOrderDeclarationInfo(List<OrderDeclarationInfoDto> orderDeclarationInfo) {
        this.orderDeclarationInfo = orderDeclarationInfo;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Map<String, Object> toMap(CreateOrder createdOrder) {
        createdOrder.setTrackingNumber(this.trackingNumber);
        createdOrder.setOrderNumber(this.customerReferenceNumber);
        ImmutableMap.Builder<String, Object> consigneeBuilder = ImmutableMap.builder();
        if (StringUtils.isNotEmpty(this.deliveryContact)) {
            consigneeBuilder.put("name", this.deliveryContact);
        }
        if (StringUtils.isNotEmpty(this.deliveryCountry)) {
            consigneeBuilder.put("country", this.deliveryCountry);
        }
        if (StringUtils.isNotEmpty(this.deliveryProvince)) {
            consigneeBuilder.put("state", this.deliveryProvince);
        }
        if (StringUtils.isNotEmpty(this.deliveryCity)) {
            consigneeBuilder.put("city", this.deliveryCity);
        }
        if (StringUtils.isNotEmpty(this.deliveryPostCode)) {
            consigneeBuilder.put("zip", this.deliveryPostCode);
        }
        if (StringUtils.isNotEmpty(this.deliveryMobile)) {
            consigneeBuilder.put("phone", this.deliveryMobile);
        } else if (StringUtils.isNotEmpty(this.deliveryTel)) {
            consigneeBuilder.put("phone", this.deliveryTel);
        }
        if (StringUtils.isNotEmpty(this.deliveryEmail)) {
            consigneeBuilder.put("email", this.deliveryEmail);
        }
        if (StringUtils.isNotEmpty(this.deliveryAddress1)) {
            consigneeBuilder.put("addressLine1", this.deliveryAddress1);
        }
        if (StringUtils.isNotEmpty(this.deliveryAddress2)) {
            String address2 = this.deliveryAddress2;
            if (StringUtils.isNotEmpty(this.deliveryAddress3)) {
                address2 += ", " + this.deliveryAddress3;
            }
            consigneeBuilder.put("addressLine2", address2);
        }
        if (StringUtils.isNotEmpty(this.deliveryCompany)) {
            consigneeBuilder.put("companyName", this.deliveryCompany);
        }

        ImmutableMap.Builder<String, Object> shipperBuilder = ImmutableMap.builder();
        if (StringUtils.isNotEmpty(this.shipperContact)) {
            shipperBuilder.put("name", this.shipperContact);
        }
        if (StringUtils.isNotEmpty(this.shipperCountry)) {
            shipperBuilder.put("country", this.shipperCountry);
        }
        if (StringUtils.isNotEmpty(this.shipperProvince)) {
            shipperBuilder.put("state", this.shipperProvince);
        }
        if (StringUtils.isNotEmpty(this.shipperCity)) {
            shipperBuilder.put("city", this.shipperCity);
        }
        if (StringUtils.isNotEmpty(this.shipperPostCode)) {
            shipperBuilder.put("zip", this.shipperPostCode);
        }
        if (StringUtils.isNotEmpty(this.shipperMobile)) {
            shipperBuilder.put("phone", this.shipperMobile);
        } else if(StringUtils.isNotEmpty(this.shipperTel)) {
            shipperBuilder.put("phone", this.shipperTel);
        }
        if (StringUtils.isNotEmpty(this.shipperEmail)) {
            shipperBuilder.put("email", this.shipperEmail);
        }
        if (StringUtils.isNotEmpty(this.shipperAddress)) {
            shipperBuilder.put("addressLine1", this.shipperAddress);
        }
        if (StringUtils.isNotEmpty(this.shipperCompany)) {
            shipperBuilder.put("companyName", this.shipperCompany);
        }

        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        builder.put("shipperItemId", this.customerReferenceNumber)
                .put("displayItemId", this.customerReferenceNumber)
                .put("consignee", consigneeBuilder.build())
                .put("shipper", shipperBuilder.build())
                .put("weightUnitOfMeasure", "kg")
                .put("value", this.cargoTotalValue)
                .put("currency", this.currency)
                .put("weight", this.cargoTotalWeight)
                .put("dimensions",
                        ImmutableMap.<String, Object>builder()
                                .put("length", this.length / 100)
                                .put("width", this.width / 100)
                                .put("height", this.height / 100)
                                .build())
                .put("dimensionsUnitOfMeasure", "m");
        if (CollectionUtils.isNotEmpty(this.getOrderDeclarationInfo())) {
            List<Map<String, Object>> products = Lists.newArrayList();
            for (OrderDeclarationInfoDto orderDeclarationInfo : this.getOrderDeclarationInfo()) {
                products.add(ImmutableMap.<String, Object>builder()
                        .put("harmonizationCode", orderDeclarationInfo.getPdHscode())
                        .put("description", orderDeclarationInfo.getPdNameEn())
                        .put("sku", orderDeclarationInfo.getPdSku())
                        .put("quantity", orderDeclarationInfo.getPdQuantity())
                        .put("value", orderDeclarationInfo.getPdValue())
                        .put("weight", orderDeclarationInfo.getPdWeight()).build());
            }
            builder.put("products", products);
        }

        return builder.build();
    }
}
