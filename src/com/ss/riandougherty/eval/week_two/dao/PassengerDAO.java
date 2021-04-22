package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Passenger;

public final class PassengerDAO extends BaseDAO<Passenger> {
	public PassengerDAO(final ConnectionManager cm, final Passenger passenger) {
		super(cm, PassengerDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("passenger").getProperties();
		
		tmpProperties.put("id", passenger.getID());
		tmpProperties.put("booking_id", passenger.getBooking());
		tmpProperties.put("given_name", passenger.getFirstName());
		tmpProperties.put("family_name", passenger.getLastName());
		tmpProperties.put("dob", passenger.getDateOfBirth());
		tmpProperties.put("gender", passenger.getGender());
		tmpProperties.put("address", passenger.getAddress());
	}
	
	public PassengerDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, PassengerDAO.class);
		
		populateDAOTable(this.propertyMapping.get("passenger"), keyValue);
	}

	@Override
	public Passenger getEntity() throws SQLException {
		DAOTable tmpDAOTable;
		tmpDAOTable = this.propertyMapping.get("passenger");
		
		Object key;
		Integer keyTyped;
		Object bookingID, givenName, familyName, dob, gender, address;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		bookingID = tmpDAOTable.getProperty("booking_id");
		givenName = tmpDAOTable.getProperty("given_name");
		familyName = tmpDAOTable.getProperty("family_name");
		dob = tmpDAOTable.getProperty("dob");
		gender = tmpDAOTable.getProperty("gender");
		address = tmpDAOTable.getProperty("address");
		
		
		throwErrorIfNull(Arrays.asList(bookingID, givenName, familyName, dob, gender, address));
		
		return new Passenger(keyTyped, new BookingDAO(cm, (int) (long) bookingID).getEntity(), (String) givenName, (String) familyName, (String) address, (String) gender, (Date) dob);
	}
}
