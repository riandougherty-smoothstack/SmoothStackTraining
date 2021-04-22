package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.Airplane;

public final class AirplaneDAO extends BaseDAO<Airplane> {
	public AirplaneDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, AirplaneDAO.class);
		
		populateDAOTable(this.propertyMapping.get("airplane"), keyValue);
		populateDAOTable(this.propertyMapping.get("airplane_type"), this.propertyMapping.get("airplane").getProperty("type_id"));
	}

	@Override
	public Airplane getEntity() throws SQLException {
		final Object key, maxCapacity;
		
		key = this.propertyMapping.get("airplane").getKey();
		
		maxCapacity = this.propertyMapping.get("airplane_type").getProperty("max_capacity");
		
		throwErrorIfNull(Arrays.asList(key, maxCapacity));
		
		return new Airplane((int) (long) key, (int) (long) maxCapacity);
	}
}
