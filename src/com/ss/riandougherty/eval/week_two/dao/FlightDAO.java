package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Flight;

public final class FlightDAO extends BaseDAO<Flight> {
	public FlightDAO(final ConnectionManager cm, final Flight flight) {
		super(cm, FlightDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("flight").getProperties();
		
		tmpProperties.put("id", flight.getID());
		tmpProperties.put("route_id", flight.getRoute().getID());
		tmpProperties.put("airplane_id", flight.getAirplane().getID());
		tmpProperties.put("departure_time", flight.getDepartureTime());
		tmpProperties.put("reserved_seats", flight.getTakenSeats());
		tmpProperties.put("seat_price", flight.getSeatPrice());
	}
	
	public FlightDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, FlightDAO.class);
		
		populateDAOTable(this.propertyMapping.get("flight"), keyValue);
	}
	
	@Override
	public Flight getEntity() throws SQLException {
		DAOTable tmpDAOTable = this.propertyMapping.get("flight");
		
		Object key;
		Integer keyTyped;
		Object routeID, airplaneID, departureTime, reservedSeats, seatPrice;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		key = tmpDAOTable.getKey();
		
		routeID = tmpDAOTable.getProperty("route_id");
		airplaneID = tmpDAOTable.getProperty("airplane_id");
		departureTime = tmpDAOTable.getProperty("departure_time");
		reservedSeats = tmpDAOTable.getProperty("reserved_seats");
		seatPrice = tmpDAOTable.getProperty("seat_price");
		
		throwErrorIfNull(Arrays.asList(routeID, airplaneID, departureTime, reservedSeats, seatPrice));
		
		return new Flight(keyTyped, new AirplaneDAO(cm, (int) (long) airplaneID).getEntity(), new RouteDAO(cm, (int) (long) routeID).getEntity(), (Timestamp) departureTime, (int) (long) reservedSeats, (float) seatPrice);
	}
}
