package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Route;

public final class RouteDAO extends BaseDAO<Route> {
	public RouteDAO(final ConnectionManager cm, final Route route) {
		super(cm, RouteDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("route").getProperties();
		
		tmpProperties.put("id", route.getID());
		tmpProperties.put("origin_id", route.getOrigin());
		tmpProperties.put("destination_id", route.getDestination());
	}
	
	public RouteDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, RouteDAO.class);
		
		populateDAOTable(this.propertyMapping.get("route"), keyValue);
	}

	@Override
	public Route getEntity() throws SQLException {
		DAOTable tmpDAOTable = this.propertyMapping.get("route");
		
		Object key;
		Integer keyTyped;
		Object originID, destinationID;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		originID = tmpDAOTable.getProperty("origin_id");
		destinationID = tmpDAOTable.getProperty("destination_id");
		
		throwErrorIfNull(Arrays.asList(originID, destinationID));
		
		return new Route(keyTyped, new AirportDAO(cm, (String) originID).getEntity(), new AirportDAO(cm, (String) destinationID).getEntity());
	}
}
