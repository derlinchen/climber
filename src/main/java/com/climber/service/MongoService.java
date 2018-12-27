package com.climber.service;

import java.util.List;

public interface MongoService {

	/**
	 * 通过泛型对数据进行查询，返回的数据为根据ID进行查询的结果
	 * @param clazz
	 * @param id
	 * @return
	 */
	<T> T findById(Class<T> clazz, String id);
	
	/**
	 * 泛型，查询所有数据的结果
	 * @param clazz
	 * @return
	 */
	<T> List<T> findAll(Class<T> clazz);
	
	/**
	 * 根据对象进行数据查询
	 * @param obj 传递的对象
	 * @param clazz 传递的类
	 * @param up 是否排序
	 * @return
	 */
	<T> List<T> findByCondition(Object obj, Class<T> clazz, boolean sort);
	
	/**
	 * 删除对象，对方法进行封装，通过obj获取对象的属性和值，然后根据条件进行删除
	 * @param obj
	 */
	void remove(Object obj);
	
	/**
	 * 添加对象
	 * @param obj
	 */
	void add(Object obj);
	
	/**
	 * 添加对象，如果对象存在，则更新
	 * @param obj
	 */
	void saveOrUpdate(Object obj);
	
}
