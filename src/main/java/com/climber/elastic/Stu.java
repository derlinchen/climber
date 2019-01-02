package com.climber.elastic;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "stu", type = "doc")
public class Stu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Field(index=true, store = true, type = FieldType.Long)
	private Long id;

	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
	private String stuId;

	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
	private String stuName;

	@Field(index = true, store = true, type = FieldType.Date)
	private Date createTime;
	
	@Field(index = true, analyzer = "ik_max_word", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
	private String sex;
	

	public Stu() {
	}
	
	public Stu(Long id, String stuId, String stuName, Date createTime) {
		this.id = id;
		this.stuId = stuId;
		this.stuName = stuName;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
