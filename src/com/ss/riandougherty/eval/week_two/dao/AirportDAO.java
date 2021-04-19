package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class AirportDAO extends BaseDAO {
	public AirportDAO(final ConnectionManager cm, final String id) {
		super(cm, "airport", "iata_id", id);
	}
	
	public String getCity() throws SQLException {
		return getRSFromParams_Easy("city").getString(1);
	}
}
