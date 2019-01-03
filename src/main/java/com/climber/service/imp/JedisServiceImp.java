package com.climber.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.climber.service.JedisService;
import com.climber.utils.LoggerUtils;

@Service("jedisService ")
public class JedisServiceImp implements JedisService {

	private static Logger logger = LoggerUtils.getLogger(JedisServiceImp.class);
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		String rtv = null;
		Jedis jedis = null;
		try {
			// 获取ShardedJedis对象
			jedis = jedisPool.getResource();
			rtv = jedis.get(key);
		} catch (Exception e) {
			logger.error(e);
		} finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return rtv;
	}
	
	@Override
	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			// 获取ShardedJedis对象
			jedis = jedisPool.getResource();
			// 存入键值对
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(jedis != null){
				// 回收ShardedJedis实例
				jedis.close();
			}
		}
	}

	@Override
	public void del(String key) {
		Jedis jedis = null;
		try {
			// 获取ShardedJedis对象
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	@Override
	public Long append(String key, String value) {
		Long rtv = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			rtv = jedis.append(key, value);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
		return rtv;
	}
	
	@Override
	public boolean exists(String key) {
		boolean rtv = false;
		Jedis jedis = null;
		try {
			// 获取ShardedJedis对象
			jedis = jedisPool.getResource();
			rtv = jedis.exists(key);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
		return rtv;
	}

}
