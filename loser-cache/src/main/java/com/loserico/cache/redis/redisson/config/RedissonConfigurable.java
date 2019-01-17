package com.loserico.cache.redis.redisson.config;

import org.redisson.config.Config;

import com.loserico.commons.resource.PropertyReader;

/**
 * Redisson 配置器接口
 * <p>
 * Copyright: Copyright (c) 2018-05-17 20:44
 * <p>
 * Company: DataSense
 * <p>
 * @author Rico Yu	ricoyu520@gmail.com
 * @version 1.0
 * @on
 */
public interface RedissonConfigurable {

	/**
	 * 配置Redisson
	 * @return Config
	 */
	Config getConfig(PropertyReader propertyReader);
}
