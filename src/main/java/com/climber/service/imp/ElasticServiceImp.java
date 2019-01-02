package com.climber.service.imp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.climber.elastic.Stu;
import com.climber.elastic.repository.StudentRepository;
import com.climber.service.ElasticService;

@Service("elasticService ")
public class ElasticServiceImp implements ElasticService {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private StudentRepository studentRepository;

	@Override
	public <T> void createIndex(Class<T> clazz) {
		elasticsearchTemplate.createIndex(clazz);
	}
	
	@Override
	public  void createDocument(Stu stu) {
		studentRepository.save(stu);
	}
	
	@Override
	public Stu getByStuId(String stuId) {
		return studentRepository.getByStuId(stuId);
	}
	
	
}
