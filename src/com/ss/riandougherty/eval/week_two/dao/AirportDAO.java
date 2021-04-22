package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Airport;

public final class AirportDAO extends BaseDAO<Airport> {
	public AirportDAO(final ConnectionManager cm, final Airport airport) {
		super(cm, AirportDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("airport").getProperties();
		
		tmpProperties.put("iata_id", airport.getIata_id());
		tmpProperties.put("city", airport.getCity());
	}
	
	public AirportDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, AirportDAO.class);
		
		populateDAOTable(this.propertyMapping.get("airport"), keyValue);
	}

	@Override
	public Airport getEntity() {
		DAOTable tmpDAOTable = this.propertyMapping.get("airport");
		
		final Object iataID, city;
		
		iataID = tmpDAOTable.getKey();
		city = tmpDAOTable.getProperty("city");
		
		throwErrorIfNull(Arrays.asList(iataID, city));
		
		return new Airport((String) iataID, (String) city);
	}
}
