package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public abstract class PersonDAO extends BaseDAO {
	public PersonDAO(ConnectionManager cm, String table, String id_name, Object id_obj) {
		super(cm, table, id_name, id_obj);
	}

	public String getFirstName() throws SQLException {
		return this.getRSFromParams_Easy("given_name").getString(1);
	}
	
	public String getLastName() throws SQLException {
		return this.getRSFromParams_Easy("family_name").getString(1);
	}
	
	public String getFullName() throws SQLException {
		return this.getFirstName() + " " + this.getLastName();
	}
}
