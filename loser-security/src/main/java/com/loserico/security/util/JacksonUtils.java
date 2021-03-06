package com.loserico.security.util;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * Jackson工具类
 * <p>
 * Copyright: Copyright (c) 2017-10-30 13:13
 * <p>
 * Company: DataSense
 * <p>
 * @author Rico Yu	ricoyu520@gmail.com
 * @version 1.0
 * @on
 */
public final class JacksonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

	private static final ObjectMapper mapper = new ObjectMapper();
	static {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(ofPattern("yyyy-MM-dd HH:mm:ss")));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern("yyyy-MM-dd")));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ofPattern("dd-MM-yyyy")));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(ofPattern("HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(ofPattern("yyyy-MM-dd HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ofPattern("yyyy-MM-dd")));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ofPattern("dd-MM-yyyy")));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(ofPattern("HH:mm:ss")));
		mapper.registerModule(javaTimeModule);

		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
				.withSetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
	}

	/**
	 * JSON字符串转MAP
	 * 
	 * @param json
	 * @return
	 */
	public static <T> Map<String, T> toMap(String json) {
		if (isBlank(json)) {
			return emptyMap();
		}
		Map<String, T> map = new HashMap<String, T>();
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, T>>() {
			});
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 将对象转成json串
	 * 
	 * @param T
	 * @return String
	 */
	public static <T> String toJson(T object) {
		if(object == null) {
			return null;
		}
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return json;
	}
}