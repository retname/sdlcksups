package com.ordermanager.project.ups.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestInfo implements Serializable {
	private static final long serialVersionUID = 2548836959594948611L;

	@JsonProperty("body")
	private RequestBodyDto body;

	public RequestBodyDto getBody() {
		return body;
	}

	public void setBody(RequestBodyDto body) {
		this.body = body;
	}

}
