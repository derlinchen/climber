package com.climber.service.imp;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.climber.service.MongoService;

@Service("mongoService")
public class MongoServiceImp implements MongoService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public <T> T findById(Class<T> clazz, String id) {
		return this.mongoTemplate.findById(id, clazz);
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		return this.mongoTemplate.findAll(clazz);
	}
	
	@Override
	public <T> List<T> findByCondition(Object obj, Class<T> clazz, boolean sort) {
		Field[] fields = clazz.getDeclaredFields();
		Query query = new Query();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(obj);
				if(value != null){
					query.addCriteria(Criteria.where(field.getName()).is(value));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} 
		if(sort){
			query.with(new Sort(Direction.ASC, "id"));
		} else{
			query.with(new Sort(Direction.DESC, "id"));
		}
		return this.mongoTemplate.find(query, clazz);
	}
	
	@Override
	public void remove(Object obj) {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Query query = new Query();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(obj);
				if(value != null){
					query.addCriteria(Criteria.where(field.getName()).is(value));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		this.mongoTemplate.remove(query,clazz);
	}

	@Override
	public void add(Object obj) {
		this.mongoTemplate.insert(obj);
	}

	@Override
	public void saveOrUpdate(Object obj) {
		this.mongoTemplate.save(obj);
	}

}
