package com.ss.riandougherty.eval.week_one;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// since only one instance can exist, make final
// also remove 'static' keyword since it make no sense here
public final class FixedSingleton {
	private static FixedSingleton instance = null;
	
	// connection is related to the singleton instance, so make it non-static
	private final Connection conn;
	
	private FixedSingleton() throws SQLException {
		// also add connection initializer to constructor
		conn = DriverManager.getConnection("MYDBURL");
	}
	
	public static FixedSingleton getInstance() throws SQLException {
		if(instance == null) {
			instance = new FixedSingleton();
		}
		// in this case, if it fails instance still remains null.
		
		return instance;
	}
	
	public static BigDecimal databaseQuery(BigDecimal input) throws SQLException {
		final FixedSingleton instance = getInstance();
		
		final Statement st = instance.conn.createStatement();
		
		final ResultSet rs = st.executeQuery("SELECT `id` FROM `table`");
		
		/*
		 * The following assumes the intent was to sum all results.
		 */
		
		BigDecimal result = new BigDecimal(0);
		
		while(rs.next()) {
			/*
			 *  You cannot natively multiply an int and a BigDecimal, so create a new one from the value of int.
			 *  
			 *  Alternatively, if the wrong method from rs was called, we could call rs#getBigDecimal
			 *  if that was the intended operation.
			 */
			
			result = result.add(input.multiply(new BigDecimal(rs.getInt(1))));
		}
		
		return result;
	}
}
