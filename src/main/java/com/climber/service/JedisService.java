package com.climber.service;

public interface JedisService {
	
	/**
	 * 通过key获取redis的value值
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 向redis存放key-value的数据
	 * @param key
	 * @param value
	 */
	public void set(String key, String value);
	
	/**
	 * 通过key删除redis的值
	 * @param key
	 */
	public void del(String key);
	
	/**
	 * 向已有的key中的value后追加值
	 * @param key
	 * @param value
	 * @return
	 */
	public Long append(String key, String value);
	
	/**
	 * 判断redis是否存在key的值
	 * @param key
	 * @return
	 */
	public boolean exists(String key);
	
}
