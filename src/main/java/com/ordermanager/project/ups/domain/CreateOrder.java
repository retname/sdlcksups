package com.ordermanager.project.ups.domain;


import com.ordermanager.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

public class CreateOrder extends BaseEntity {


  /** 日志id*/
  private Integer id;
    /**订单号  */
  private String orderNumber;
    /** 返回状态值 */
  private String responseStatus;
    /** 渠道号*/
  private String trackingNumber;
    /** 面单地址*/
  private String labelUrl;
    /** 面单类型*/
  private String format;
    /** 渠道返回单号*/
  private String orderNo;
    /** 价格*/
  private BigDecimal price;
    /**价格明细 */
  private String priceDetail;
    /** 基础运费，接口返回值*/
  private String packageDetail;
    /** 结果*/
  private String result;

  private String pdfAddress;
  private String pngAddress;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public String getLabelUrl() {
    return labelUrl;
  }

  public void setLabelUrl(String labelUrl) {
    this.labelUrl = labelUrl;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getPriceDetail() {
    return priceDetail;
  }

  public void setPriceDetail(String priceDetail) {
    this.priceDetail = priceDetail;
  }

  public String getPackageDetail() {
    return packageDetail;
  }

  public void setPackageDetail(String packageDetail) {
    this.packageDetail = packageDetail;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getPdfAddress() {
    return pdfAddress;
  }

  public void setPdfAddress(String pdfAddress) {
    this.pdfAddress = pdfAddress;
  }

  public String getPngAddress() {
    return pngAddress;
  }

  public void setPngAddress(String pngAddress) {
    this.pngAddress = pngAddress;
  }
}
