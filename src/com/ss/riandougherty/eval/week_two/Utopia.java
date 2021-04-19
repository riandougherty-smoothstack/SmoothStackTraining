package com.ss.riandougherty.eval.week_two;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.ss.riandougherty.eval.week_two.dao.AirportDAO;
import com.ss.riandougherty.eval.week_two.dao.FlightDAO;
import com.ss.riandougherty.eval.week_two.dao.RouteDAO;

public final class Utopia {
	private Utopia() {}
	
	private static ConnectionManager createConnectionManager() {
		final Properties properties = new Properties();
		
		properties.setProperty("user", Credentials.user);
		properties.setProperty("password", Credentials.password);
		
		return new ConnectionManager(Credentials.url, properties);
	}
	
	public static Menu generateMenu(final Accessor a) {
		final Menu mainMenu, employeeMenu, flightsModification;
		final MenuAction flightsList;
		
		mainMenu = new Menu(new ArrayList<>(), null);
		mainMenu.setTitle("Welcome to the Utopia Airlines Management System. Which category of a user are you?");
		
		employeeMenu = new Menu(new ArrayList<>(), mainMenu);
		
		flightsModification = new Menu(new ArrayList<>(), null);
		
		flightsList = () -> {
			final Menu flightsMenu = new Menu(new ArrayList<>(), employeeMenu);
			
			final List<MenuOption> temp = flightsMenu.getOptions();
			List<FlightDAO> flights = null;
			
			try {
				flights = a.getFlights();
			} catch(final SQLException e) {
				return;
			}
			
			for(final FlightDAO flight : flights) {
				try {
					final RouteDAO r = flight.getRoute();
					final AirportDAO orig, dest;
					
					orig = r.getOrigin();
					dest = r.getDestination();
					
					temp.add(new MenuOption(orig.getID() + "," + orig.getCity() + " -> " + dest.getID() + "," + dest.getCity(), flightsModification));
				} catch(final SQLException e) {
					e.printStackTrace();
					// continue on in the loop.
				}
			}
			
			flightsMenu.select();
		};
		
		flightsModification.setPreviousMenu(flightsList);
		employeeMenu.getOptions().add(new MenuOption("Enter Flights You Manage", flightsList));
		
		flightsModification.getOptions().addAll(Arrays.asList(
				new MenuOption("View more details about the flight", () -> {
					final StringBuilder sb = new StringBuilder();
					
					sb.append("Flight ID: ");
					
				}),
				new MenuOption("Update the details of the flight", () -> {
					
				}),
				new MenuOption("Addd seats to the flight", () -> {
					
				})
		));
		
		mainMenu.getOptions().add(new MenuOption("Employee", employeeMenu));
		// TODO .getOptions().add("Administrator", administratorMenu);
		// TODO .getOptions().add("Traveler", travelerMenu
		
		return mainMenu;
	}
	
	public static void console() {
		generateMenu(new Accessor(createConnectionManager())).select();;
	}
	
	public static void main(final String[] args) throws Throwable {
		console();
	}
}
