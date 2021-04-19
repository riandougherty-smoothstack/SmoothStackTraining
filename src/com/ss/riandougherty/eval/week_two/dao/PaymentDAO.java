package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class PaymentDAO extends BaseDAO {
	public PaymentDAO(final ConnectionManager cm, final int id) {
		super(cm, "booking_payment", "booking_id", id);
	}
	
	public String getStripeID() throws SQLException {
		return this.getRSFromParams_Easy("stripe_id").getString(1);
	}
	
	public boolean isRefunded() throws SQLException {
		return this.getRSFromParams_Easy("refunded").getBoolean(1);
	}
	
	@Override
	public void delete(final Connection con) throws SQLException {
		final PreparedStatement st;
		st = con.prepareStatement("UPDATE `booking_payment` SET `refunded` = 1 WHERE `booking_id` = ?");
		st.setObject(1, this.id_obj);
		
		st.execute();
	}
}
