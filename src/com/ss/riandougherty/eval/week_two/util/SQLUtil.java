package com.ss.riandougherty.eval.week_two.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class SQLUtil {
	private SQLUtil() {}
	
	/*
	 * Internal helpers
	 */
	
	private static void appendTicked(final StringBuilder dest, final String field) {
		dest.append('`');
		dest.append(field);
		dest.append('`');
	}
	
	private static void appendWhere(final StringBuilder dest, final String matchField) {
		dest.append(" WHERE ");
		appendTicked(dest, matchField);
		dest.append(" = ?");
	}
	
	private static void appendMatch(final StringBuilder dest, final String table, final String matchField) {
		dest.append(" FROM ");
		appendTicked(dest, table);
		appendWhere(dest, matchField);
	}
	
	/*
	 * SQL String Construction
	 */
	
	public static String generateSelect(final String table, final String field, final String matchField) {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ");
		
		if(field == null) {
			sb.append('*');
		} else {
			appendTicked(sb, field);
		}
		
		sb.append(" FROM ");
		appendTicked(sb, table);
		
		if(matchField != null) {
			appendWhere(sb, matchField);
		}
		
		return sb.toString();
	}
	
	public static String generateDelete(final String table, final String matchField) {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("DELETE");
		
		appendMatch(sb, table, matchField);
		
		return sb.toString();
	}
	
	public static String generateUpdate(final String table, final Collection<String> toUpdate, final String matchField) {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE ");
		appendTicked(sb, table);
		sb.append(" SET ");
		
		sb.append(StringUtil.getFormattedStringFromMapper(toUpdate, s -> {
			final StringBuilder tmp = new StringBuilder();
			
			appendTicked(tmp, s);
			tmp.append(" = ?");
			
			return tmp.toString();
		}));
		
		appendWhere(sb, matchField);
		
		return sb.toString();
	}
	
	public static String generateInsert(final String table, final Collection<String> valueParams) {
		final StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO ");
		appendTicked(sb, table);
		
		sb.append(" (");
		sb.append(StringUtil.getFormattedStringFromMapper(valueParams, s -> {
			final StringBuilder tmp = new StringBuilder();
			
			appendTicked(tmp, s);
			
			return tmp.toString();
		}));
		
		sb.append(") VALUES (");
		
		sb.append(StringUtil.getFormattedStringFromMapper(valueParams, s -> {
			return "?";
		}));
		sb.append(")");
		
		return sb.toString();
	}
	
	/*
	 * SQL Execution
	 */
	
	public static ResultSet execute(final Connection con, final String sql, final Collection<Object> params) throws SQLException {
		final PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		if(params != null) {
			int index = 1;
			
			for(final Object param : params) {
				st.setObject(index, param);
				
				index++;
			}
		}
		
		return st.executeQuery();
	}
	
	public static ResultSet execute(final Connection con, final String sql) throws SQLException {
		return execute(con, sql, null);
	}
	
	public static Object executeKey(final Connection con, final String sql, final Collection<Object> params) throws SQLException {
		final ResultSet rs;
		rs = execute(con, sql, params);
		
		final Object ret;
		
		if(rs.next()) {
			ret = rs.getObject(1);
		} else {
			ret = null;
		}
		
		return ret;
	}
	
	public static Object executeKey(final Connection con, final String sql) throws SQLException {
		return executeKey(con, sql, null);
	}
}
