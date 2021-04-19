package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionManager {
	private final String url;
	private final Properties properties;
	
	public ConnectionManager(final String url, final Properties properties) {
		this.url = url;
		this.properties = properties;
	}
	
	public Connection getConnection() throws SQLException {
		final Connection con;
		
		con = DriverManager.getConnection(url, properties);
		
		con.setAutoCommit(false);
		
		return con;
	}
}
