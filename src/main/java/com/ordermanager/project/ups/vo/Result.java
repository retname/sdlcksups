package com.ordermanager.project.ups.vo;

import io.swagger.annotations.ApiModelProperty;

public class Result {

	@ApiModelProperty(value = "返回码00000是成功，其他为失败")
	private String  code;
	@ApiModelProperty(value = "返回信息")
	private String msg;
	@ApiModelProperty(value = "返回结果")
	private Object data;

	/**
	 * 成功信息封装
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static Result ok(String msg,Object obj){
		Result result=new Result();
		result.setCode("00000");
		result.setMsg(msg);
		result.setData(obj);
		return result;
	}

	/**
	 * 错误信息封装
	 * @param msg
	 * @param obj
	 * @return
	 */
	public static Result failure(String msg,Object obj){
		Result result=new Result();
		result.setCode("99999");
		result.setMsg(msg);
		result.setData(obj);
		return result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
