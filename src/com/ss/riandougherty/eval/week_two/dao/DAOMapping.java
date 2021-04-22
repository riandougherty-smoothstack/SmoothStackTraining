package com.ss.riandougherty.eval.week_two.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DAOMapping {
	public static final Map<Class<? extends BaseDAO>, DAOMappingEntry> mapping;
	
	static {
		Map<String, String> tmpTableMapping;
		Map<Class<? extends BaseDAO>, DAOMappingEntry> tmp_mapping = new HashMap<>();
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("airplane", "id");
		tmpTableMapping.put("airplane_type", "id");
		tmp_mapping.put(AirplaneDAO.class, new DAOMappingEntry("airplane", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("airport", "iata_id");
		tmp_mapping.put(AirportDAO.class, new DAOMappingEntry("airport", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("booking", "id");
		tmpTableMapping.put("flight_bookings", "booking_id");
		tmpTableMapping.put("booking_agent", "booking_id");
		tmpTableMapping.put("booking_user", "booking_id");
		tmp_mapping.put(BookingDAO.class, new DAOMappingEntry("booking", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("booking_payment", "booking_id");
		tmp_mapping.put(PaymentDAO.class, new DAOMappingEntry("booking_payment", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("flight", "id");
		tmp_mapping.put(FlightDAO.class, new DAOMappingEntry("flight", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("passenger", "id");
		tmp_mapping.put(PassengerDAO.class, new DAOMappingEntry("passenger", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("route", "id");
		tmp_mapping.put(RouteDAO.class, new DAOMappingEntry("route", tmpTableMapping));
		
		tmpTableMapping = new HashMap<>();
		tmpTableMapping.put("user", "id");
		tmp_mapping.put(UserDAO.class, new DAOMappingEntry("user", tmpTableMapping));
		
		mapping = Collections.unmodifiableMap(tmp_mapping);
	}
	
	private DAOMapping() {}
	
	public static DAOMappingEntry getMapping(final Class<? extends BaseDAO> c) {
		return mapping.get(c);
	}
}
