package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.ss.riandougherty.eval.week_two.dao.BookingDAO;
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
	
	public ConnectionManager getConnectionManager() {
		return this.cm;
	}
	
	public String getRandomCode() {
		final StringBuffer sb = new StringBuffer();
		
		int i;
		
		for(i = 0; i < 16; i++) {
			sb.append(ThreadLocalRandom.current().nextInt(10));
		}
		
		return sb.toString();
	}
	
	// returns confirmation code
	public BookingDAO createBooking(final FlightDAO flight) throws SQLException {
		final Connection con = cm.getConnection();
		
		final String confirmationCode = getRandomCode();
		
		PreparedStatement st;
		st = con.prepareStatement("INSERT INTO `booking` (`is_active`, `confirmation_code`) VALUES (?, ?)");
		st.setInt(1, 1);
		st.setString(2, confirmationCode);
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		int booking_id = rs.getInt(1);
		
		con.commit();
		
		st = con.prepareStatement("INSERT INTO `flight_bookings` (`flight_id`, `booking_id`) VALUES (?, ?)");
		st.setInt(1, (int) flight.getID());
		st.setInt(2, booking_id);
		
		con.commit();
		con.close();
		
		return new BookingDAO(this.cm, booking_id);
	}
}
