package com.peacefish.spring.transaction;

import java.util.function.Supplier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Spring事务工具类
 * 在Spring的Service层另起线程，但是该线程需要在事务内运行时可以使用这个帮助类
 * 
 * @author Rico Yu ricoyu520@gmail.com
 * @since 2017-04-19 17:38
 * @version 1.0
 * @on
 *
 */
@Component
@Transactional
@Deprecated
public class TransManager {
	private static TransManager instance;
	
	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	void setup() {
		instance = applicationContext.getBean(TransManager.class);
	}
	
	public static TransManager instance() {
		return instance;
	}

	public <R> R apply(Supplier<R> f) {
		return f.get();
	}

	public void run(Runnable f) {
		f.run();
	}

	/**
	 * 在事务正确提交以后运行task
	 * @param task
	 */
	public void afterCommit(Runnable task) {
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
			@Override
			public void suspend() {
			}

			@Override
			public void resume() {
			}

			@Override
			public void flush() {
			}

			@Override
			public void beforeCommit(boolean readOnly) {
			}

			@Override
			public void beforeCompletion() {
			}

			@Override
			public void afterCompletion(int status) {
			}

			@Override
			public void afterCommit() {
				task.run();
			}
		});
	}

}