package com.climber.elastic;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "stu", type = "doc")
public class Stu implements Serializable {

	private static final long serialVersionUID = 566392553987979222L;

	@Id
	@Field
	private Long id;

	@Field
	private String stuId;

	@Field
	private String stuName;

	@Field
	private Date createTime;

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
