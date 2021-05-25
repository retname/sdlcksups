package com.ordermanager.project.ups.domain;


import java.util.Date;

public class SysParam {
  /**主键ID */
  private Integer id;
  /**KEY */
  private String appKey;
  /**md5加密 密码 */
  private String secret;
  /** 接口地址*/
  private String interfaceAddress;
  /** 产品编码*/
  private String serviceCode;
  /** 面单大小*/
  private String labelSize;
  /**签名服务，不传或传0: 无签名 、 */
  private String signatureService;
  /** */
  private Date createTime;
  /** */
  private Date updateTime;
  /**参数说明 */
  private String paramNote;

  private Integer deletedFlag;
  /**接口地址类型 */
  private Integer urlType;



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getInterfaceAddress() {
    return interfaceAddress;
  }

  public void setInterfaceAddress(String interfaceAddress) {
    this.interfaceAddress = interfaceAddress;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getLabelSize() {
    return labelSize;
  }

  public void setLabelSize(String labelSize) {
    this.labelSize = labelSize;
  }

  public String getSignatureService() {
    return signatureService;
  }

  public void setSignatureService(String signatureService) {
    this.signatureService = signatureService;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getParamNote() {
    return paramNote;
  }

  public void setParamNote(String paramNote) {
    this.paramNote = paramNote;
  }

  public Integer getDeletedFlag() {
    return deletedFlag;
  }

  public void setDeletedFlag(Integer deletedFlag) {
    this.deletedFlag = deletedFlag;
  }

  public Integer getUrlType() {
    return urlType;
  }

  public void setUrlType(Integer urlType) {
    this.urlType = urlType;
  }
}
