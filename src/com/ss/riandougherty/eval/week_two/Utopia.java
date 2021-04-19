package com.ss.riandougherty.eval.week_two;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.ss.riandougherty.eval.week_two.dao.AirportDAO;
import com.ss.riandougherty.eval.week_two.dao.BookingDAO;
import com.ss.riandougherty.eval.week_two.dao.FlightDAO;
import com.ss.riandougherty.eval.week_two.dao.RouteDAO;

public final class Utopia {
	private static Object menuReference;
	private static int menuOperation;
	
	private Utopia() {}
	
	private static ConnectionManager createConnectionManager() {
		final Properties properties = new Properties();
		
		properties.setProperty("user", Credentials.user);
		properties.setProperty("password", Credentials.password);
		
		return new ConnectionManager(Credentials.url, properties);
	}
	
	@SuppressWarnings("resource")
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
					
					temp.add(new MenuOption(orig.getID() + "," + orig.getCity() + " -> " + dest.getID() + "," + dest.getCity(), () -> {
						menuReference = flight;
						flightsModification.select();
					}));
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
					try {
						final FlightDAO flight = (FlightDAO) menuReference;
						final StringBuilder sb = new StringBuilder();
						
						sb.append("Flight ID: ");
						sb.append((int) flight.getID());
						sb.append("\n-- Departure -- \nAirport: ");
						
						sb.append((String) flight.getRoute().getOrigin().getID());
					
						sb.append("\nTime: ");
						sb.append(flight.getDepartureTime().toString());
						
						sb.append("\n-- Arrival --\nAirport: ");
						sb.append(flight.getRoute().getDestination().getID());
						
						sb.append("\n-- Misc --\nAvailable Seats: ");
						sb.append(flight.getRemainingSeats());
						
						System.out.println(sb.toString());
					} catch(final SQLException e) {
						e.printStackTrace();
					}
				}),
				new MenuOption("Update the details of the flight", () -> {
					try {
						final FlightDAO flight = (FlightDAO) menuReference;
						
						final Scanner sc = new Scanner(System.in);
						System.out.println("Enter new route origin:");
						
						final String s1, s2;
						final Connection con = a.getConnectionManager().getConnection();
						final PreparedStatement st;
						
						s1 = sc.nextLine();
						s2 = sc.nextLine();
						
						int route_id = (int) flight.getRoute().getID();
						
						st = con.prepareStatement("REPLACE INTO `route` (origin_id, destination_id) WHERE `route_id` = ? VALUES (?, ?)");
						st.setInt(1, route_id);
						
						st.setString(1, s1);
						st.setString(2, s2);
						st.execute();
						
						con.commit();
						con.close();
					} catch(final SQLException e) {
						e.printStackTrace();
					}
				})
		));
		
		final Menu travellerFlightsModification;
		final MenuAction flightsListTraveller;
		
		travellerFlightsModification = new Menu(new ArrayList<>(), null);
		
		flightsListTraveller = () -> {
			final Menu travellerFlightsMenu = new Menu(new ArrayList<>(), employeeMenu);
			
			final List<MenuOption> temp = travellerFlightsMenu.getOptions();
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
					
					temp.add(new MenuOption(orig.getID() + "," + orig.getCity() + " -> " + dest.getID() + "," + dest.getCity(), () -> {
						menuReference = flight;
						travellerFlightsModification.select();
					}));
				} catch(final SQLException e) {
					e.printStackTrace();
					// continue on in the loop.
				}
			}
			
			travellerFlightsMenu.select();
		};
		
		travellerFlightsModification.setPreviousMenu(flightsListTraveller);
		travellerFlightsModification.getOptions().add(new MenuOption("Book Ticket", () -> {
			try {
				final FlightDAO flight = (FlightDAO) menuReference;
				final BookingDAO new_booking = a.createBooking(flight);
				System.out.println("Created booking with ID:" + new_booking.getID());
				System.out.println("Confirmation code is: " + new_booking.getConfirmationCode());
			} catch(final SQLException e) {
				e.printStackTrace();
			}
		}));
		
		travellerFlightsModification.getOptions().add(new MenuOption("Cancel", () -> {
			try {
				System.out.println("Enter Booking ID");
				int booking_id = new Scanner(System.in).nextInt();
				
				final Connection con = a.getConnectionManager().getConnection();
				final PreparedStatement st;
				st = con.prepareStatement("UPDATE `booking` SET `is_active` = 0 WHERE ID = ?");
				st.setInt(1, booking_id);
				st.execute();
				
				System.out.println("Cancelled.");
				
				con.commit();
				con.close();
			} catch(final SQLException e) {
				e.printStackTrace();
			}
		}));
		
		final Menu administratorMenu = new Menu(new ArrayList<>(), mainMenu);
		final Menu type = new Menu(new ArrayList<>(), administratorMenu);
		
		administratorMenu.getOptions().add(new MenuOption("CREATE", () -> {
			menuOperation = 0;
			
			type.select();
		}));
		administratorMenu.getOptions().add(new MenuOption("UPDATE", () -> {
			menuOperation = 1;
			
			type.select();
		}));
		administratorMenu.getOptions().add(new MenuOption("DELETE", () -> {
			menuOperation = 2;
			
			type.select();
		}));
		administratorMenu.getOptions().add(new MenuOption("READ", () -> { 
			menuOperation = 3;
			
			type.select();
		}));
		type.getOptions().add(new MenuOption("Flights", () -> {
			try {
				final Connection con = a.getConnectionManager().getConnection();
				switch(menuOperation) {
				case 0: case 1:
					System.out.println("Enter values: ");
					Scanner sc = new Scanner(System.in);
					int route_id = sc.nextInt();
					int airplane_id = sc.nextInt();
					Time time = Time.valueOf(sc.next());
					int reserved_seats = sc.nextInt();
					float seat_price = sc.nextFloat();
					
					FlightDAO.addFlight(a.getConnectionManager(), route_id, airplane_id, time, reserved_seats, seat_price);
					
					break;
				case 2: case 3:
					System.out.println("Enter ID:");
					
					break;
				}
			} catch(final Exception e) {
				System.out.println("Incorrect arguments.");
			}
		}));
		type.getOptions().add(new MenuOption("Airports", () -> {
			switch(menuOperation) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			}
		}));
		type.getOptions().add(new MenuOption("Tickets", () -> {
			switch(menuOperation) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			}
		}));
		
		mainMenu.getOptions().add(new MenuOption("Employee", employeeMenu));
		mainMenu.getOptions().add(new MenuOption("Traveler", flightsListTraveller));
		mainMenu.getOptions().add(new MenuOption("Administrator", administratorMenu));
		
		return mainMenu;
	}
	
	public static void console() {
		generateMenu(new Accessor(createConnectionManager())).select();
	}
	
	public static void main(final String[] args) throws Throwable {
		console();
	}
}
