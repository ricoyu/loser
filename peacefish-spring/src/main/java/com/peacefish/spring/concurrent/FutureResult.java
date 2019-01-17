package com.peacefish.spring.concurrent;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.peacefish.spring.exception.AsyncExecutionException;

/**
 * 封装Concurrent报下的Future接口, 出错则抛AsyncExecutionException
 * <p>
 * Copyright: Copyright (c) Nov 9, 2017
 * <p>
 * Company: DataSense
 * <p>
 * @author Rico Yu	ricoyu520@gmail.com
 * @version 1.0
 * @on
 * @param <T>
 */
public class FutureResult<T> implements Serializable {

	private static final long serialVersionUID = -1576544068293276948L;

	private static final Logger logger = LoggerFactory.getLogger(FutureResult.class);

	private Future<T> future;

	public FutureResult(Future<T> future) {
		this.future = future;
	}

	/**
	 * 返回结果
	 * 
	 * @return
	 */
	public T get() {
		try {
			return future.get();
		} catch (Exception e) {
			logger.error("msg", e);
			throw new AsyncExecutionException("", e);
		}
	}

	/**
	 * 如果从future对象获取时抛出异常，则返回supplier的返回值
	 * 
	 * @return
	 */
	public T orElseGet(Supplier<T> supplier) {
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("msg", e);
		}
		return supplier.get();
	}

	/**
	 * 如果从future对象获取时抛出异常，则返回result
	 * 
	 * @param result
	 * @return
	 */
	public T orElseGet(T result) {
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("msg", e);
		}
		return result;
	}

	/**
	 * 如果返回结果不为null，那么消费次结果。
	 * 不管消费与否，最终都返回结果T
	 * @param consumer
	 * @return T
	 */
	public T ifPresent(Consumer<T> consumer) {
		T result = get();
		if (result != null) {
			consumer.accept(result);
			logger.info("Consumed! {}", result);
		}
		return result;
	}
}
