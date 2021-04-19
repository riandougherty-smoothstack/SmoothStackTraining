package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class UserDAO extends PersonDAO {
	public UserDAO(final ConnectionManager cm, final int id) {
		super(cm, "user", "id", id);
	}
	
	public String getRole() throws SQLException {
		return this.getRSFromParams_Different("name", "user_role", "id", this.getRSFromParams_Easy("role_id").getInt(1)).getString(1);
	}
}
