package com.ordermanager.project.ups.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PrintLabelModel implements Serializable {
	private static final long serialVersionUID = 2548836959594948611L;


	private List<String> orderCode;//订单号



	public List<String> getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(List<String> orderCode) {
		this.orderCode = orderCode;
	}

}
