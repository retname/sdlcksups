package com.ordermanager.project.ups.domain;


import com.ordermanager.framework.web.domain.BaseEntity;

public class ApiLog extends BaseEntity {

  /** ID */
  private Integer id;

  /** 接口地址*/
  private String apiUrl;

  /** 请求参数*/
  private String requestParams;

  /** 返回状态 */
  private String responseStatus;

  /**返回内容 */
  private String responseBody;

  /** 请求头*/
  private String requestHeader;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }


  public String getRequestParams() {
    return requestParams;
  }

  public void setRequestParams(String requestParams) {
    this.requestParams = requestParams;
  }


  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(String responseStatus) {
    this.responseStatus = responseStatus;
  }


  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }


  public String getRequestHeader() {
    return requestHeader;
  }

  public void setRequestHeader(String requestHeader) {
    this.requestHeader = requestHeader;
  }
}
