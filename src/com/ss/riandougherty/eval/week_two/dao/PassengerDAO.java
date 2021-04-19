package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public static PassengerDAO addPassenger(final ConnectionManager cm, int booking_id, String firstName, String lastName, Date dob, String gender, String address) throws SQLException {
		final Connection con = cm.getConnection();
		final PreparedStatement st;
		st = con.prepareStatement("INSERT INTO `passenger` (`booking_id`, `given_name`, `family_name`, `dob`, `gender`, `address`");
		st.setInt(1, booking_id);
		st.setString(2, firstName);
		st.setString(3, lastName);
		st.setDate(4, dob);
		st.setString(5, gender);
		st.setString(6, address);
		
		st.execute();
		
		final ResultSet rs = st.getGeneratedKeys();
		
		rs.next();
		
		int passenger_id = rs.getInt(1);
		
		final PassengerDAO passenger = new PassengerDAO(cm, passenger_id);
		
		con.commit();
		con.close();
		
		return passenger;
	}
}
