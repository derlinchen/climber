package com.climber.parser;

public class Column {
	
	private String colId;
	
    private String colName;
    
    private String colCode;
    
    private String dataType;
    
    private String colComment;
    
    private boolean FK;
    
    private boolean PK;

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColComment() {
		return colComment;
	}

	public void setColComment(String colComment) {
		this.colComment = colComment;
	}

	public boolean isFK() {
		return FK;
	}

	public void setFK(boolean fK) {
		FK = fK;
	}

	public boolean isPK() {
		return PK;
	}

	public void setPK(boolean pK) {
		PK = pK;
	}
    
}
