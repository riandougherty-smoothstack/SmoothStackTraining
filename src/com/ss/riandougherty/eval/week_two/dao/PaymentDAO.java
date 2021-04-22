package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Payment;

public final class PaymentDAO extends BaseDAO<Payment> {
	public PaymentDAO(final ConnectionManager cm, final Payment payment) {
		super(cm, PaymentDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("booking_payment").getProperties();
		
		tmpProperties.put("id", payment.getID());
		tmpProperties.put("stripe_id", payment.getStripeID());
		tmpProperties.put("refunded", payment.isRefunded());
	}
	
	public PaymentDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, PaymentDAO.class);
		
		populateDAOTable(this.propertyMapping.get("booking_payment"), keyValue);
	}

	@Override
	public Payment getEntity() throws SQLException {
		DAOTable tmpDAOTable = this.propertyMapping.get("booking_payment");
		
		Object key;
		Integer keyTyped;
		Object stripeID, isRefunded;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		stripeID = tmpDAOTable.getProperty("stripe_id");
		isRefunded = tmpDAOTable.getProperty("refunded");
		
		throwErrorIfNull(Arrays.asList(stripeID, isRefunded));
		
		return new Payment(keyTyped, (String) stripeID, ((int) isRefunded) != 0 ? true : false);
	}
}
