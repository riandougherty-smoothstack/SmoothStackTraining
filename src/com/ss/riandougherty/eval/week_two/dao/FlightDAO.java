package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class FlightDAO extends BaseDAO {
	public FlightDAO(final ConnectionManager cm, final int id) {
		super(cm, "flight", "id", id);
	}
	
	public AirplaneDAO getPlane() throws SQLException {
		return new AirplaneDAO(this.cm, this.getRSFromParams_Easy("airplane_id").getInt(1));
	}
	
	public RouteDAO getRoute() throws SQLException {
		return new RouteDAO(this.cm, this.getRSFromParams_Easy("route_id").getInt(1));
	}
	
	public int getTakenSeats() throws SQLException {
		return this.getRSFromParams_Easy("reserved_seats").getInt(1);
	}
	
	public int getCapacity() throws SQLException {
		return getPlane().getCapacity();
	}
	
	public int getRemainingSeats() throws SQLException {
		return getCapacity() - getTakenSeats();
	}
	
	public float getSeatPrice() throws SQLException {
		return this.getRSFromParams_Easy("seat_price").getFloat(1);
	}
	
	public Time getDepartureTime() throws SQLException {
		return this.getRSFromParams_Easy("departure_time").getTime(1);
	}
	
	public List<BookingDAO> getBookings() throws SQLException {
		final Connection con = cm.getConnection();
		
		final PreparedStatement st;
		st = con.prepareStatement("SELECT `booking_id` FROM `flight_bookings` WHERE `flight_id` = ?");
		st.setObject(1, this.id_obj);
		
		ResultSet rs;
		rs = st.executeQuery();
		
		final List<BookingDAO> bookings = new ArrayList<>();
		
		while(rs.next()) {
			bookings.add(new BookingDAO(cm, rs.getInt(1)));
		}
		
		con.commit();
		con.close();
		
		return bookings;
	}

	@Override
	public void delete(final Connection con) throws SQLException {
		for(final BookingDAO booking : getBookings()) {
			booking.delete(con);
		}
		
		super.delete(con);
	}
}
