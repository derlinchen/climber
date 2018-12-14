package com.climber.bean;

public class ReturnValue {

	private boolean success;

	private String msg;
	
	private Object data;

	public ReturnValue() {
		this.success = true;
		this.msg = "";
		this.data = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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
