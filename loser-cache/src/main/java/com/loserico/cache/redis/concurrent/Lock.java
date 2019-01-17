package com.loserico.cache.redis.concurrent;

import com.loserico.cache.redis.cache.interfaze.Expirable;

/**
 * <b>分布式锁</b><p>
 * 提供加锁成功与否的返回结果与解锁操作
 * <p>
 * Copyright: Copyright (c) 2018-05-18 11:52
 * <p>
 * Company: DataSense
 * <p>
 * @author Rico Yu	ricoyu520@gmail.com
 * @version 1.0
 * @on
 */
public interface Lock extends Expirable {

	/**
	 * 是否成功获取锁
	 * 
	 * @return boolean
	 */
	public boolean locked();

	/**
	 * 释放锁。需要先检查是否成功获取锁，没获得锁就调用该方法将抛异常
	 * 
	 * @throws IllegalMonitorStateException
	 */
	public void unlock();

	/**
	 * 释放锁。需要先检查是否成功获取锁，没获得锁就调用该方法将抛异常</br>
	 * 在事务环境，不管事务提交与否，都会释放锁
	 * <p/>
	 * 注意这是异步执行的，在事务提交或者回滚后才会真正unlock。所以在成功获取到锁以后，可以立即调用lock.unlockAnyway()，实际释放锁会在后面的业务代码执行完毕后才进行
	 * @throws IllegalMonitorStateException
	 * @on
	 */
	public void unlockAnyway();
}
