package com.ordermanager.project.ups.vo;

import java.io.Serializable;

public class OrderDeclarationInfoDto implements Serializable {
	private static final long serialVersionUID = -2088155740973520372L;
	
	private String pdNameEn;// 申报名称(英文)
	private String pdNameCn;// 申报名称(中文)
	private double pdWeight;// 净重
	private Integer pdQuantity;// 数量
	private double pdValue;// 申报价值
	private String pdHscode;// 海关编码
	private String pdSku;// SKU
	private String pdUrl;// 申报品URL
	private String pdRemark;// 备注

	public OrderDeclarationInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getPdNameEn() {
		return pdNameEn;
	}

	public void setPdNameEn(String pdNameEn) {
		this.pdNameEn = pdNameEn;
	}

	public String getPdNameCn() {
		return pdNameCn;
	}

	public void setPdNameCn(String pdNameCn) {
		this.pdNameCn = pdNameCn;
	}

	public double getPdWeight() {
		return pdWeight;
	}

	public void setPdWeight(double pdWeight) {
		this.pdWeight = pdWeight;
	}

	public Integer getPdQuantity() {
		return pdQuantity;
	}

	public void setPdQuantity(Integer pdQuantity) {
		this.pdQuantity = pdQuantity;
	}

	public double getPdValue() {
		return pdValue;
	}

	public void setPdValue(double pdValue) {
		this.pdValue = pdValue;
	}

	public String getPdHscode() {
		return pdHscode;
	}

	public void setPdHscode(String pdHscode) {
		this.pdHscode = pdHscode;
	}

	public String getPdSku() {
		return pdSku;
	}

	public void setPdSku(String pdSku) {
		this.pdSku = pdSku;
	}

	public String getPdUrl() {
		return pdUrl;
	}

	public void setPdUrl(String pdUrl) {
		this.pdUrl = pdUrl;
	}

	public String getPdRemark() {
		return pdRemark;
	}

	public void setPdRemark(String pdRemark) {
		this.pdRemark = pdRemark;
	}

	@Override
	public String toString() {
		return "OrderDeclarationInfoDTO [pdNameEn=" + pdNameEn + ", pdNameCn=" + pdNameCn + ", pdWeight=" + pdWeight
				+ ", pdQuantity=" + pdQuantity + ", pdValue=" + pdValue + ", pdHscode=" + pdHscode + ", pdSku=" + pdSku
				+ ", pdUrl=" + pdUrl + ", pdRemark=" + pdRemark + "]";
	}

}

