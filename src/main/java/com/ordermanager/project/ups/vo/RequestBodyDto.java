package com.ordermanager.project.ups.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBodyDto implements Serializable {
	private static final long serialVersionUID = 1960191772652313800L;

	@JsonProperty("order")
	private List<OrderDto> order;

    public List<OrderDto> getOrder() {
		return order;
	}

	public void setOrder(List<OrderDto> order) {
		this.order = order;
	}

}
