package com.loserico.java8;

import java.time.ZoneId;
import java.util.Arrays;

import org.junit.Test;

public class StringsTest {

	@Test
	public void testStringJoin() {
		String joined = String.join("/", "usr", "local", "bin");
		System.out.println(joined);
		
		String ids = String.join(",", ZoneId.getAvailableZoneIds());
		System.out.println(ids);
		String[] zoneIds = ids.split("/");
		Arrays.asList(zoneIds).forEach(System.out::println);
	}
}
