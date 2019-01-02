package com.climber.service;

import com.climber.elastic.Stu;

public interface ElasticService {
	
	public <T> void createIndex(Class<T> clazz);
	
	public void createDocument(Stu stu);
	
	public Stu getByStuId(String stuId);
}
