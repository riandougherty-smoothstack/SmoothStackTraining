package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class RouteDAO extends BaseDAO {
	public RouteDAO(final ConnectionManager cm, final int id) {
		super(cm, "route", "id", id);
	}
	
	private AirportDAO getAirport(final String field) throws SQLException {
		return new AirportDAO(this.cm, this.getRSFromParams_Easy(field).getString(1));
	}
	
	public AirportDAO getOrigin() throws SQLException {
		return getAirport("origin_id");
	}
	
	public AirportDAO getDestination() throws SQLException {
		return getAirport("destination_id");
	}
}
