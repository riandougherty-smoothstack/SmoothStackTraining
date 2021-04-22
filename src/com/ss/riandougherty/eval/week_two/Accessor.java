package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.dao.AirportDAO;
import com.ss.riandougherty.eval.week_two.dao.BaseDAO;
import com.ss.riandougherty.eval.week_two.dao.BookingDAO;
import com.ss.riandougherty.eval.week_two.dao.DAOMapping;
import com.ss.riandougherty.eval.week_two.dao.DAOMappingEntry;
import com.ss.riandougherty.eval.week_two.dao.FlightDAO;
import com.ss.riandougherty.eval.week_two.dao.PassengerDAO;
import com.ss.riandougherty.eval.week_two.dao.RouteDAO;
import com.ss.riandougherty.eval.week_two.dao.UserDAO;
import com.ss.riandougherty.eval.week_two.entity.Airport;
import com.ss.riandougherty.eval.week_two.entity.BaseEntity;
import com.ss.riandougherty.eval.week_two.entity.Booking;
import com.ss.riandougherty.eval.week_two.entity.Flight;
import com.ss.riandougherty.eval.week_two.entity.Passenger;
import com.ss.riandougherty.eval.week_two.entity.Route;
import com.ss.riandougherty.eval.week_two.entity.User;
import com.ss.riandougherty.eval.week_two.entity.UserRole;
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
	public <T extends BaseDAO, E extends BaseEntity> List<E> getAllByClass(Class<? extends BaseDAO> t) throws SQLException {
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
	
	public List<Route> getAllRoutes() throws SQLException {
		return getAllByClass(RouteDAO.class);
	}
	
	public List<Flight> getAllFlights() throws SQLException {
		return getAllByClass(FlightDAO.class);
	}
	
	public List<Booking> getAllBookings() throws SQLException {
		return getAllByClass(BookingDAO.class);
	}
	
	public List<Passenger> getAllPassengers() throws SQLException {
		return getAllByClass(PassengerDAO.class);
	}
	
	public List<User> getAllEmployees() throws SQLException {
		final List<User> ret = new ArrayList<>();
		
		for(final BaseEntity b : getAllByClass(UserDAO.class)) {
			final User u = (User) b;
			
			if(u.getRole().equals(UserRole.EMPLOYEE)) {
				ret.add(u);
			}
		}
		
		return ret;
	}
}
