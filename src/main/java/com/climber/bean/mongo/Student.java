package com.climber.bean.mongo;

public class Student {
	
	private String id;
	
	private String stdname;
	
	private String stddesc;
	
	public Student() {
	}

	public Student(String stdname, String stddesc) {
		this.stdname = stdname;
		this.stddesc = stddesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
		this.stdname = stdname;
	}

	public String getStddesc() {
		return stddesc;
	}

	public void setStddesc(String stddesc) {
		this.stddesc = stddesc;
	}
	
	
}
