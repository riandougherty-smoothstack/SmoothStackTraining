package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Booking;

public final class BookingDAO extends BaseDAO<Booking> {
	public BookingDAO(final ConnectionManager cm, final Booking booking) {
		super(cm, BookingDAO.class);
		
		Map<String, Object> tmpProperties;
		tmpProperties = this.propertyMapping.get("booking").getProperties();
		
		tmpProperties.put("id", booking.getID());
		tmpProperties.put("is_active", booking.isActive());
		tmpProperties.put("confirmation_code", booking.getConfirmationCode());
		
		tmpProperties = this.propertyMapping.get("booking_agent").getProperties();
		
		tmpProperties.put("booking_id", booking.getID());
		tmpProperties.put("agent_id", booking.getAgent().getID());
		
		tmpProperties = this.propertyMapping.get("booking_user").getProperties();
		
		tmpProperties.put("booking_id", booking.getID());
		tmpProperties.put("user_id", booking.getUser().getID());
	}
	
	public BookingDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, BookingDAO.class);
		
		populateDAOTable(this.propertyMapping.get("booking"), keyValue);
		populateDAOTable(this.propertyMapping.get("flight_bookings"), keyValue);
		populateDAOTable(this.propertyMapping.get("booking_agent"), keyValue);
		populateDAOTable(this.propertyMapping.get("booking_user"), keyValue);
	}

	@Override
	public Booking getEntity() throws SQLException {
		DAOTable tmpDAOTable;
		
		tmpDAOTable = this.propertyMapping.get("booking");
		
		Object key;
		Integer keyTyped;
		Object isActive, confirmationCode, flightID, agentID, userID;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		isActive = tmpDAOTable.getProperty("is_active");
		confirmationCode = tmpDAOTable.getProperty("confirmation_code");
		
		tmpDAOTable = this.propertyMapping.get("flight_bookings");
		flightID = tmpDAOTable.getProperty("flight_id");
		
		tmpDAOTable = this.propertyMapping.get("booking_agent");
		agentID = tmpDAOTable.getProperty("agent_id");
		
		tmpDAOTable = this.propertyMapping.get("booking_user");
		userID = tmpDAOTable.getProperty("user_id");
		
		throwErrorIfNull(Arrays.asList(isActive, confirmationCode, flightID));
		
		return new Booking(keyTyped, ((int) isActive) != 0 ? true : false, (String) confirmationCode, new UserDAO(cm, (int) (long) agentID).getEntity(), new UserDAO(cm, (int) (long) userID).getEntity(), new FlightDAO(cm, (int) (long) flightID).getEntity());
	}
}
