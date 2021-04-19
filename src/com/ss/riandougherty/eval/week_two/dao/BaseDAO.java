package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public abstract class BaseDAO {
	protected final ConnectionManager cm;
	protected final String table;
	protected final String id_name;
	protected final Object id_obj;
	
	public BaseDAO(final ConnectionManager cm, final String table, final String id_name, final Object id_obj) {
		this.cm = cm;
		this.table = table;
		this.id_name = id_name;
		this.id_obj = id_obj;
	}
	
	public static ResultSet getRSFromParams(final Connection con, final String select_param, final String from_param, final String where_param, final Object object) throws SQLException {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT `");
		sb.append(select_param);
		sb.append("` FROM `");
		sb.append(from_param);
		sb.append("` WHERE `");
		sb.append(where_param);
		sb.append("` = ?");
		
		final PreparedStatement st;
		st = con.prepareStatement(sb.toString());
		
		st.setObject(1, object);
		
		final ResultSet rs;
		rs = st.executeQuery();
		
		if(!rs.next()) {
			throw new SQLException("Not found.");
		}
		
		return rs;
	}
	
	public static void deleteFromParams(final Connection con, final String table_param, final String id_name_param, final Object id_obj_param) throws SQLException {
		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM `");
		sb.append(table_param);
		sb.append("` WHERE `");
		sb.append(id_name_param);
		sb.append("` = ?");
		
		final PreparedStatement st;
		st = con.prepareStatement(sb.toString());
		st.setObject(1, id_obj_param);
		
		st.execute();
	}
	
	private ResultSet getRSFromParams_Internal(final String field, final String table_param, final String id_name_param, final Object id_obj_param) throws SQLException {
		final Connection con = cm.getConnection();
		
		final ResultSet rs;
		rs = getRSFromParams(con, field, table_param, id_name_param, id_obj_param);
		
		con.commit();
		con.close();
		
		return rs;
	}
	
	protected ResultSet getRSFromParams_Different(final String field, final String table_param, final String id_name_param, final Object id_obj_param) throws SQLException {
		return getRSFromParams_Internal(field, table_param, id_name_param, id_obj_param);
	}
	
	protected ResultSet getRSFromParams_Easy(final String field) throws SQLException {
		return getRSFromParams_Internal(field, table, id_name, id_obj);
	}
	
	public Object getID() {
		return this.id_obj;
	}
	
	public void delete(final Connection con) throws SQLException {
		deleteFromParams(con, this.table, this.id_name, this.id_obj);
	}
}
