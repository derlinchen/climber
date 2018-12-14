package com.climber.bean;

public class ReturnList {

	private int total;

	private Object data;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		if (this.data == null)
			return "{\"total\":0,\"data\":[]}";
		else {
			return "{\"total\":" + this.total + ",\"data\":" + this.data.toString() + "}";
		}
	}
}
