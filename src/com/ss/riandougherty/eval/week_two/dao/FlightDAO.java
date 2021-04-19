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
	
	public static FlightDAO addFlight(final ConnectionManager cm, int route_id, int airplane_id, Time departure_time, int reserved_seats, float seat_price) throws SQLException {
		final Connection con = cm.getConnection();
		final PreparedStatement st;
		st = con.prepareStatement("INSERT INTO `flight` (`route_id`, `airplane_id`, `departure_time`, `reserved_seats`, `seat_price`");
		st.setInt(1, route_id);
		st.setInt(2, airplane_id);
		st.setTime(3, departure_time);
		st.setInt(4, reserved_seats);
		st.setFloat(5, seat_price);
		
		st.execute();
		
		final ResultSet rs = st.getGeneratedKeys();
		
		rs.next();
		
		int flight_id = rs.getInt(1);
		
		final FlightDAO flight = new FlightDAO(cm, flight_id);
		
		con.commit();
		con.close();
		
		return flight;
	}
}
