package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.dao.AirportDAO;
import com.ss.riandougherty.eval.week_two.dao.BaseDAO;
import com.ss.riandougherty.eval.week_two.dao.DAOMapping;
import com.ss.riandougherty.eval.week_two.dao.DAOMappingEntry;
import com.ss.riandougherty.eval.week_two.dao.FlightDAO;
import com.ss.riandougherty.eval.week_two.entity.Airport;
import com.ss.riandougherty.eval.week_two.entity.BaseEntity;
import com.ss.riandougherty.eval.week_two.entity.Flight;
import com.ss.riandougherty.eval.week_two.util.SQLUtil;

public final class Accessor {
	private final ConnectionManager cm;
	
	public Accessor(final ConnectionManager cm) {
		this.cm = cm;
	}
	
	public ConnectionManager getConnectionManager() {
		return this.cm;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends BaseDAO, E extends BaseEntity> List<E> getAllByClass(Class<? extends BaseDAO> t) throws SQLException {
		final Connection con = cm.getConnection();
		final DAOMappingEntry daoMappingEntry;
		
		daoMappingEntry = DAOMapping.getMapping(t);
		
		final List<E> items = new ArrayList<>();
		
		final String query = SQLUtil.generateSelect(daoMappingEntry.getPrimaryTable(), daoMappingEntry.getPrimarKeyNameFromTableName(daoMappingEntry.getPrimaryTable()), null);
		
		final ResultSet rs = SQLUtil.execute(con, query);
		
		while(rs.next()) {
			try {
				items.add((E) ((T) t.getConstructor(ConnectionManager.class, Object.class).newInstance(cm, rs.getObject(1))).getEntity());
			} catch (Throwable t1) {
				t1.printStackTrace();
			}
		}
		
		con.commit();
		con.close();
		
		return items;
	}
	
	public List<Airport> getAllAirports() throws SQLException {
		return getAllByClass(AirportDAO.class);
	}
	
	public List<Flight> getAllFlights() throws SQLException {
		return getAllByClass(FlightDAO.class);
	}
}
