package com.climber.bean;

public class BaseBean {

	private String ids;

	private int start;

	private int end;

	private int limit;

	private int total;

	private String search;

	private int action;

	private ReturnValue rtv;

	public BaseBean() {
		this.ids = "";
		this.action = 0;
		this.rtv = new ReturnValue();
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public ReturnValue getRtv() {
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	public void initSearchParam(String search) {
		this.end = this.limit;
		this.start = this.start + 1;
		this.search = search;
	}

}
