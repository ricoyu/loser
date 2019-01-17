package com.loserico.orm.dao;

import java.util.List;
import java.util.Map;

import com.loserico.orm.bean.Page;

/**
 * 定义在XML中命名SQL查询接口，返回resultset将绑定到给定类型的Bean中。
 * ResultSet中field_name到Bean中property的映射规则为 foo_bar --> fooBar username --> username
 * 
 * @author Rico Yu ricoyu520@gmail.com
 * @since 2017-01-27 17:04
 * @version 1.0
 *
 */
public interface SQLOperations {

	/**
	 * 返回单个对象，不存在则返回null
	 * 
	 * @param queryName
	 * @param paramName
	 * @param paramValue
	 * @param clazz
	 * @return
	 */
	public <T> T namedSqlQuerySingleResult(String queryName, String paramName, Object paramValue, Class<T> clazz);

	public <T> T namedSqlQuerySingleResult(String queryName, Map<String, Object> params, Class<T> clazz);

	/**
	 * 返回单个对象，不存在则返回null
	 * 
	 * @param queryName
	 * @param paramName
	 * @param paramValue
	 * @param clazz
	 * @return
	 */
	public <T> T namedSqlQuerySingleResult(String queryName, Class<T> clazz);

	/**
	 * 没有分页和参数的命名SQL查询
	 * 
	 * @param queryName
	 * @param clazz
	 * @return
	 */
	public <T> List<T> namedSqlQuery(String queryName, Class<T> clazz);

	/**
	 * 支持分页的命名SQL查询，不带参数，同时会自动调用queryName_count来获取总记录数
	 * 
	 * @param queryName
	 * @param clazz
	 * @param page
	 * @return List<T>
	 */
	public <T> List<T> namedSqlQuery(String queryName, Class<T> clazz, Page page);

	/**
	 * 不带分页的命名SQL查询，一个参数。
	 * 
	 * @param queryName
	 * @param paramName
	 * @param paramValue
	 * @param clazz
	 * @return List<T>
	 */
	public <T> List<T> namedSqlQuery(String queryName, String paramName, Object paramValue, Class<T> clazz);

	/**
	 * 支持分页的命名SQL查询，支持一个参数，同时会自动调用queryName_count来获取总记录数
	 * 
	 * @param queryName
	 * @param paramName
	 * @param paramValue
	 * @param clazz
	 * @param page
	 * @return
	 */
	public <T> List<T> namedSqlQuery(String queryName, String paramName, Object paramValue, Class<T> clazz, Page page);

	/**
	 * 不带分页的命名SQL查询
	 * 
	 * @param queryName
	 * @param params
	 * @param clazz
	 * @return List<T>
	 */
	public <T> List<T> namedSqlQuery(String queryName, Map<String, Object> params, Class<T> clazz);

	/**
	 * 支持分页的命名SQL查询，同时会自动调用queryName_count来获取总记录数 支持Velocity风格的SQL模版
	 * 
	 * @param queryName
	 * @param params
	 * @param clazz
	 * @param page
	 * @return List<T>
	 */
	public <T> List<T> namedSqlQuery(String queryName, Map<String, Object> params, Class<T> clazz, Page page);

	/**
	 * 跟namedSqlQuery的差别就是结果集不封装到Bean里面
	 * 
	 * @param queryName
	 * @return
	 */
	public List<?> namedRawSqlQuery(String queryName);

	/**
	 * 跟namedSqlQuery的差别就是结果集不封装到Bean里面
	 * 
	 * @param queryName
	 * @return
	 */
	public List<?> namedRawSqlQuery(String queryName, String propertyName, Object value);

	/**
	 * 跟namedSqlQuery的差别就是结果集不封装到Bean里面
	 * 
	 * @param queryName
	 * @return
	 */
	public List<?> namedRawSqlQuery(String queryName, Map<String, Object> params);

	/**
	 * 跟namedSqlQuery的差别就是结果集不封装到Bean里面
	 * 
	 * @param queryName
	 * @return
	 */
	public Object namedRawSqlQuerySingleResult(String queryName, Map<String, Object> params);

	/**
	 * 跟namedSqlQuery的差别就是结果集不封装到Bean里面
	 * 
	 * @param queryName
	 * @return
	 */
	public Object namedRawSqlQuerySingleResult(String queryName, String paramName, Object paramValue);

	/**
	 * 根据SQL查询返回单个结果，并自动转换成期望对象类型
	 * 
	 * @param queryName
	 * @param paramName
	 * @param paramValue
	 * @param type 返回值类型
	 * @return
	 */
	public <T> T namedRawSqlQuerySingleResult(String queryName, String paramName, Object paramValue, Class<T> type);

	public Object namedRawSqlQuerySingleResult(String queryName);

	/**
	 * 返回单个值的查询 比如type是BigDecimal.class，那么这个查询返回的是BigDecimal
	 * 
	 * @param queryName
	 * @param params
	 * @param type
	 * @return
	 */
	public <T> T namedScalarQuery(String queryName, Map<String, Object> params, Class<T> type);

	public <T> T namedScalarQuery(String queryName, String paramName, Object paramValue, Class<T> type);

	/**
	 * 返回单个值的列表查询 比如type是BigDecimal.class，那么这个查询返回的是BigDecimal
	 * 
	 * @param queryName
	 * @param params
	 * @param type
	 * @return List<T>
	 */
	public <T> List<T> namedScalarListQuery(String queryName, String paramName, Object paramValue, Class<T> type);
	
	/**
	 * 返回单个值的列表查询 比如type是BigDecimal.class，那么这个查询返回的是BigDecimal
	 * 
	 * @param queryName
	 * @param params
	 * @param type
	 * @return List<T>
	 */
	public <T> List<T> namedScalarListQuery(String queryName, Map<String, Object> params, Class<T> type);

	/**
	 * 查询返回多行数据，每行数据又有types.length列，将每列的值转成types对应的数据类型并返回
	 * 
	 * @param queryName
	 * @param params
	 * @param types
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List namedScalarListQuery(String queryName, Map<String, Object> params, Class... types);

	/**
	 * 查询返回多行数据，每行数据又有types.length列，将每列的值转成types对应的数据类型并返回
	 * @param queryName
	 * @param types
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List namedScalarListQuery(String queryName, Class... types);

	/**
	 * 查询返回多行数据，每将每列的值转成type对应的数据类型并返回
	 * @param queryName
	 * @param types
	 * @return
	 */
	public <T> List<T> namedScalarListQuery(String queryName, Class<T> type);


	/**
	 * SQL 标量查询
	 * @param sql
	 * @param type
	 * @return T
	 */
	public <T> T sqlScalarQuery(String sql, Class<T> type);

	/**
	 * SQL 标量查询
	 * @param sql
	 * @param type
	 * @return T
	 */
	public <T> T sqlScalarQuery(String sql, Map<String, Object> params, Class<T> type);

	/**
	 * SQL 标量查询
	 * @param sql
	 * @param type
	 * @return T
	 */
	public <T> T sqlScalarQuery(String sql, String paramName, Object paramValue, Class<T> type);
	
	/**
	 * 根据给定的查询条件判断是否有符合条件的记录存在
	 * 
	 * @param queryName
	 * @param params
	 * @return
	 */
	public boolean ifExists(String queryName, Map<String, Object> params);

	/**
	 * 非命名的SQL查询，不带参数
	 * 
	 * @param sql
	 * @param clazz
	 * @return List<T>
	 */
	public <T> List<T> sqlQuery(String sql, Class<T> clazz);

	/**
	 * 非命名的SQL查询，带一个参数
	 * 
	 * @param sql
	 * @param paramName
	 * @param paramValue
	 * @param clazz
	 * @return
	 */
	public <T> List<T> sqlQuery(String sql, String paramName, Object paramValue, Class<T> clazz);

	public <T> List<T> sqlQuery(String sql, String countSql, String paramName, Object paramValue, Class<T> clazz,
			Page page);

	/**
	 * 非命名的SQL查询
	 * 
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	public <T> List<T> sqlQuery(String sql, Map<String, Object> params, Class<T> clazz);

	/**
	 * 非命名SQL，带参数和分页
	 * 
	 * @param sql
	 * @param countSql
	 * @param params
	 * @param clazz
	 * @param page
	 * @return
	 */
	public <T> List<T> sqlQuery(String sql, String countSql, Map<String, Object> params, Class<T> clazz, Page page);

	public int sqlCountQuery(String sql, Map<String, Object> params);

	/**
	 * 执行更新
	 * 
	 * @param queryName
	 * @param params
	 * @return int 更新影响的条数
	 */
	public int executeUpdate(String queryName, Map<String, Object> params);

	/**
	 * 执行更新
	 * 
	 * @param queryName
	 * @param params
	 * @return int 更新影响的条数
	 */
	public int executeUpdate(String queryName, String paramName, Object paramValue);

	/**
	 * 直接在Java代码里面写SQL语句查询，结果不封装到Bean
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<?> rawSqlQuery(String sql, Map<String, Object> params);
	
	/**
	 * 直接在Java代码里面写SQL语句查询，结果不封装到Bean,返回单个结果
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object rawSqlQuerySingleResult(String queryName, Map<String, Object> params);
}
