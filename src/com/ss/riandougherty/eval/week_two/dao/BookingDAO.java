package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class BookingDAO extends BaseDAO {
	
	public BookingDAO(final ConnectionManager cm, final int id) {
		super(cm, "booking", "id", id);
	}
	
	public PassengerDAO getPassenger() throws SQLException {
		return new PassengerDAO(this.cm, this.getRSFromParams_Different("id", "passenger", "booking_id", id_obj).getInt(1));
	}
	
	public UserDAO getBookingAgent() throws SQLException {
		return new UserDAO(this.cm, this.getRSFromParams_Different("agent_id", "booking_agent", "booking_id", id_obj).getInt(1));
	}
	
	public UserDAO getBookingUser() throws SQLException {
		return new UserDAO(this.cm, this.getRSFromParams_Different("user_id", "booking_user", id_name, id_obj).getInt(1));
	}
	
	public PaymentDAO getBookingPayment() throws SQLException {
		return new PaymentDAO(this.cm, (int) this.id_obj);
	}
	
	public boolean isActive() throws SQLException {
		return this.getRSFromParams_Easy("is_active").getBoolean(1);
	}

	@Override
	public void delete(final Connection con) throws SQLException {
		final PreparedStatement st;
		st = con.prepareStatement("UPDATE `booking` SET `is_active` = 0 WHERE `id` = ?");
		st.setObject(1, this.id_obj);
		
		st.execute();
		
		getBookingPayment().delete(con);
	}
}
