package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.dao.FlightDAO;

public final class Accessor {
	private final ConnectionManager cm;
	
	public Accessor(final ConnectionManager cm) {
		this.cm = cm;
	}
	
	public List<FlightDAO> getFlights() throws SQLException {
		final Connection con = this.cm.getConnection();
		
		final PreparedStatement st;
		final ResultSet rs;
		st = con.prepareStatement("SELECT `id` FROM `flight`");
		rs = st.executeQuery();
		
		List<FlightDAO> flights = new ArrayList<>();
		
		while(rs.next()) {
			flights.add(new FlightDAO(this.cm, rs.getInt(1)));
		}
		
		return flights;
	}
}
