package com.climber.bean;

public class SetSelect extends BaseBean{
	
	private String id;
	
	private String name;

	private String sqlid;
	
	private String sqltext;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSqlid() {
		return sqlid;
	}

	public void setSqlid(String sqlid) {
		this.sqlid = sqlid;
	}

	public String getSqltext() {
		return sqltext;
	}

	public void setSqltext(String sqltext) {
		this.sqltext = sqltext;
	}
	
}
