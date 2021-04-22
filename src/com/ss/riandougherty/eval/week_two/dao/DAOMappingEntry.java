package com.ss.riandougherty.eval.week_two.dao;

import java.util.HashMap;
import java.util.Map;

public final class DAOMappingEntry {
	private final String primaryTable;
	private final Map<String, String> mapping;
	
	public DAOMappingEntry(final String primaryTable, final Map<String, String> mapping) {
		this.primaryTable = primaryTable;
		this.mapping = mapping;
	}
	
	public String getPrimaryTable() {
		return this.primaryTable;
	}
	
	public Map<String, String> getMapping() {
		return this.mapping;
	}
	
	public String getPrimarKeyNameFromTableName(final String tableName) {
		return this.mapping.get(tableName);
	}
	
	public Map<String, DAOTable> generateDAOPart() {
		final Map<String, DAOTable> ret = new HashMap<>();
		
		mapping.forEach((table, primaryKeyName) -> {
			ret.put(table, new DAOTable(table, primaryKeyName, new HashMap<>()));
		});
		
		return ret;
	}
}
