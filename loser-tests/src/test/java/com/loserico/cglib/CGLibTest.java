package com.loserico.cglib;

import org.junit.Test;

import com.loserico.commons.jackson.JacksonUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Mixin;

public class CGLibTest {

	@Test
	public void testMixin() {
		Object mish = Mixin.create(new Object[]{new A(), new B()});
		System.out.println(JacksonUtils.toJson(mish));
	}
	
	@Test
	public void testEnhancer() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(A.class);
//		enhancer.setSuperclass(B.class);
		Object object = enhancer.create();
	}
}
