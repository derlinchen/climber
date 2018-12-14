package com.climber.bean;

public class SysColumn extends BaseBean {

	private String columnID;

	private String columnCode;

	private String text;

	private String xtype;

	private String xtypename;

	private String dataIndex;

	private int flex;
	
	private String format;

	private boolean hidden;

	public String getColumnID() {
		return columnID;
	}

	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getXtypename() {
		return xtypename;
	}

	public void setXtypename(String xtypename) {
		this.xtypename = xtypename;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public int getFlex() {
		return flex;
	}

	public void setFlex(int flex) {
		this.flex = flex;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
