package com.ss.riandougherty.eval.week_two;

import java.util.Properties;

import com.ss.riandougherty.eval.week_two.dao.service.Service;

public final class Utopia {
	private Utopia() {}
	
	public static ConnectionManager createConnectionManager() {
		final Properties properties = new Properties();
		
		properties.setProperty("user", Credentials.user);
		properties.setProperty("password", Credentials.password);
		
		return new ConnectionManager(Credentials.url, properties);
	}
	
	public static void main(final String[] args) throws Throwable {
		final ConnectionManager cm = createConnectionManager();
		
		new Service(cm).prompt();
	}
}
