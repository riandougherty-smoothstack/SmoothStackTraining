package com.ss.riandougherty.eval.week_two.dao;

import java.util.Map;

public final class DAOTable {
	private final String tableName;
	private final String primaryKeyName;
	private final Map<String, Object> properties;
	
	public DAOTable(final String tableName, final String primaryKeyName, final Map<String, Object> properties) {
		this.tableName = tableName;
		this.primaryKeyName = primaryKeyName;
		this.properties = properties;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public String getPrimaryKeyName() {
		return this.primaryKeyName;
	}
	
	public Map<String, Object> getProperties() {
		return this.properties;
	}
	
	public Object getProperty(final String key) {
		return this.properties.get(key);
	}
	
	public void setProperty(final String key, final Object value) {
		this.properties.put(key, value);
	}
	
	public Object getKey() {
		return this.getProperty(primaryKeyName);
	}
	
	public void setKey(final Object value) {
		this.setProperty(primaryKeyName, value);
	}
}
