package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Date;
import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class PassengerDAO extends PersonDAO {
	public PassengerDAO(final ConnectionManager cm, final int id) {
		super(cm, "passenger", "id", id);
	}
	
	public String getGender() throws SQLException {
		return this.getRSFromParams_Easy("gender").getString(1);
	}
	
	public Date getDateOfBirth() throws SQLException {
		return this.getRSFromParams_Easy("dob").getDate(1);
	}
}
