package com.ss.riandougherty.eval.week_two.dao.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.ss.riandougherty.eval.week_two.Accessor;
import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.dao.AirportDAO;
import com.ss.riandougherty.eval.week_two.dao.FlightDAO;
import com.ss.riandougherty.eval.week_two.dao.RouteDAO;
import com.ss.riandougherty.eval.week_two.entity.Airport;
import com.ss.riandougherty.eval.week_two.entity.Booking;
import com.ss.riandougherty.eval.week_two.entity.Flight;
import com.ss.riandougherty.eval.week_two.entity.Passenger;
import com.ss.riandougherty.eval.week_two.entity.Route;
import com.ss.riandougherty.eval.week_two.entity.User;

public final class Service {
	private final ConnectionManager cm;
	private final Accessor acc;
	private final LinkedList<MenuItem> menuStack;
	private final LinkedList<Object> objectStack;
	
	public Service(final ConnectionManager cm) {
		this.cm = cm;
		this.acc = new Accessor(cm);
		menuStack = new LinkedList<>();
		objectStack = new LinkedList<>();
	}
	
	private static void printOptions(final Collection<String> options) {
		int a = 1;
		
		for(final String option : options) {
			System.out.println(a + ") " + option);
			
			a++;
		}
	}
	
	private static List<String> rebuildWithCancel(final Collection<String> items) {
		List<String> newItems = new ArrayList<>(items);
		
		newItems.add("Return to previous menu.");
		
		return newItems;
	}
	
	private static int getIntegerLine() {
		for(;;) {
			try {
				final Scanner sc = new Scanner(System.in);
				
				int read = Integer.parseInt(sc.nextLine());
				
				return read;
			} catch(final NumberFormatException e) {
				System.out.println("Not a valid integer! Try again.");
			}
		}
	}
	
	private static int getIntInRange(final int lowerBounds, final int upperBounds) {
		for(;;) {
			try {
				int read = getIntegerLine();
				
				if(read >= lowerBounds && read <= upperBounds) {
					return read;
				}
				
				throw new NumberFormatException();
			} catch(final NumberFormatException e) {
				System.out.println("You must enter an integer within range!");
			}
		}
	}
	
	private static int getIndex(final Collection<String> items) {
		printOptions(items);
		
		return getIntInRange(1, items.size());
	}
	
	private static <T> T getFromMapping(final Collection<String> items, final List<T> retItems) {
		return retItems.get(getIndex(items) - 1);
	}
	
	private static <T> T getFromMappingAppendCancel(final Collection<String> items, final List<T> retItems) {
		List<String> temp = rebuildWithCancel(items);
		List<T> tempRet = new ArrayList<>(retItems);
		
		tempRet.add(null);
		
		return getFromMapping(temp, tempRet);
	}
	
	public static void retryOnExceptions(final Consumer<Void> method) {
		for(;;) {
			try {
				method.accept(null);
				break;
			} catch(final IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid input. Try again.");
			}
		}
	}
	
	private static <T> T getFromListAppendCancelToString(final List<T> items) {
		final List<String> descriptions = new ArrayList<>();
		
		for(final Object o : items) {
			descriptions.add(items.toString());
		}
		
		return getFromMappingAppendCancel(descriptions, items);
	}
	
	public void prompt() {
		
		/*
		 * Employee
		 */
		
		MenuItem flightVerboseInfo = flight -> {
			System.out.println(flight.toString());
			
			return null;
		};
		
		MenuItem flightsModification = flight -> {
			final Flight f = (Flight) flight;
			
			System.out.println("You have chosen to update the flight with flight id: " + f.getID() + " origin: " + f.getRoute().getOrigin().getCity() + " destination: " + f.getRoute().getDestination().getCity() + ".");
			
			final Scanner sc = new Scanner(System.in);
			
			retryOnExceptions(nothing -> {
				Airport origin, destination;
				
				String originStr, destinationStr, originSplit[], destinationSplit[];
				
				System.out.println("Enter origin airport and city:");
				originStr = sc.nextLine();
				originSplit = originStr.split(" ", 2);
				
				origin = new Airport(originSplit[0], originSplit[1]);
				System.out.println("Enter destination airport and city:");
				destinationStr = sc.nextLine();
				destinationSplit = destinationStr.split(" ", 2);
				
				destination = new Airport(destinationSplit[0], destinationSplit[1]);
				
				System.out.println("Departure Date: ");
				
				Timestamp time = Timestamp.valueOf(sc.nextLine());
				try {
					final Connection transaction = cm.getConnection();
					
					AirportDAO ad1, ad2;
					ad1 = new AirportDAO(cm, origin);
					ad2 = new AirportDAO(cm, destination);
					
					ad1.saveProperties(transaction);
					ad2.saveProperties(transaction);
					
					origin = ad1.getEntity();
					destination = ad2.getEntity();
					
					Route r;
					RouteDAO rd;
					
					r =  new Route(origin, destination);
					rd = new RouteDAO(cm, r);
					
					rd.saveProperties(transaction);
					r = rd.getEntity();
					
					f.setRoute(r);
					f.setDepartureTime(time);
					
					new FlightDAO(cm, f).saveProperties(transaction);
					
					transaction.commit();
					transaction.close();
				} catch(final SQLException e) {
					e.printStackTrace();
				}
			});
			
			return null;
		};
		
		MenuItem flightAddSeats = flight -> {
			final Flight f = (Flight) flight;
			
			System.out.println("Enter the number of seats you would like to add.\nCurrent: " + f.getRemainingSeats());
			
			int num = getIntInRange(0 - f.getTakenSeats(), Integer.MAX_VALUE);
			
			f.setTakenSeats(f.getTakenSeats() + num);
			
			try {
				new FlightDAO(cm, f).savePropertiesOK();
			} catch (final Exception e) {
				e.printStackTrace();
			};
			
			System.out.println("Added " + num + " seats.");
			
			return null;
		};
		
		MenuItem flightAction = flight -> {
			MenuItem nextMenu = getFromMappingAppendCancel(Arrays.asList("View more details about the flight", "Update the details of the flight", "Add seats to flight"), Arrays.asList(flightVerboseInfo, flightsModification, flightAddSeats));
			
			return new MenuReturn(flight, nextMenu);
		};
		
		MenuItem flightsList = nextMenu -> {
			List<Flight> flights = null;
			try {
				flights = acc.getAllFlights();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			
			Flight ret = getFromMappingAppendCancel(flights.stream().map(f -> f.getRoute().toString()).collect(Collectors.toList()), flights);
			
			if(ret == null) {
				nextMenu = null;
			}
			
			return new MenuReturn(ret, (MenuItem) nextMenu);
		};
		
		
		/*
		 * Administrator
		 */
		
		MenuItem adminModifyAirports = operation -> {
			final int op = (int) operation;
			
			Airport ent = null;
			
			if (op != 0) {
				List<Airport> ents = null;
				
				try {
					ents = acc.getAllAirports();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
				
				ent = getFromListAppendCancelToString(ents);
				
				if(ent == null) {
					return null;
				}
			}
			
			if(op == 1) {
				System.out.println(ent.toString());
				
				return null;
			}
			
			try {
				final Connection con = cm.getConnection();
				if(op == 2) {
					final AirportDAO entD = new AirportDAO(cm, ent);
					
					entD.delete(con);
				} else {
					final Scanner sc = new Scanner(System.in);
					String iataCode, cityName;
					
					iataCode = null;
					
					if(op == 0) {
						System.out.println("Enter airport IATA:");
						
						iataCode = sc.nextLine();
					}
					
					System.out.println("Enter airport city:");
					
					cityName = sc.nextLine();
					
					new AirportDAO(cm, new Airport(iataCode, cityName)).saveProperties(con);
				}
				
				con.commit();
				con.close();
			} catch(final Exception e) {
				e.printStackTrace();
				System.out.println("Invalid operation");
			}
			
			return null;
		};
		
		MenuItem adminModifyRoutes = operation -> {
			final int op = (int) operation;
			List<Route> ents = null;
			
			try {
				ents = acc.getAllRoutes();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			
			Route ent = getFromListAppendCancelToString(ents);
			
			return null;
		};
		
		MenuItem adminModifyFlights = operation -> {
			final int op = (int) operation;
			List<Flight> ents = null;
			
			try {
				ents = acc.getAllFlights();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			
			Flight ent = getFromListAppendCancelToString(ents);
			
			return null;
		};
		
		MenuItem adminModifyBookings = operation -> {
			final int op = (int) operation;
			List<Booking> ents = null;
			
			try {
				ents = acc.getAllBookings();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			
			Booking ent = getFromListAppendCancelToString(ents);
			
			return null;
		};
		
		MenuItem adminModifyPassengers = operation -> {
			final int op = (int) operation;
			List<Passenger> ents = null;
			
			try {
				ents = acc.getAllPassengers();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
			
			Passenger ent = getFromListAppendCancelToString(ents);
			
			return null;
		};
		
		MenuItem adminModifyEmployees = operation -> {
			final int op = (int) operation;
			List<User> ents = null;
			
			try {
				ents = acc.getAllEmployees();
			} catch(final SQLException e) {
				e.printStackTrace();
			}

			User ent = getFromListAppendCancelToString(ents);
			
			return null;
		};
		
		MenuItem adminModificationSelection = operation -> {
			MenuItem nextNextMenu = getFromMappingAppendCancel(Arrays.asList("Airports", "Routes", "Flights", "Bookings", "Passengers", "Employees"), Arrays.asList(adminModifyAirports, adminModifyRoutes, adminModifyFlights, adminModifyBookings, adminModifyPassengers, adminModifyEmployees));
			
			return new MenuReturn(operation, nextNextMenu);
		};
		
		MenuItem adminCrudSelection = nothing -> {
			final MenuReturn ret = new MenuReturn();
			
			retryOnExceptions(nothing1 -> {
				System.out.println("[(C)REATE | (R)EAD | (U)PDATE | (D)ELETE] ?");
				
				final Scanner sc = new Scanner(System.in);
				
				String line = sc.nextLine().toLowerCase();
				if(line.startsWith("c")) {
					ret.setReturnValue(0);
				} else if(line.startsWith("r")) {
					ret.setReturnValue(1);
				} else if(line.startsWith("d")) {
					ret.setReturnValue(2);
				} else if(line.startsWith("u")) {
					ret.setReturnValue(3);
				} else {
					throw new IllegalArgumentException("Invalid Operation.");
				}
			});
			
			ret.setMenuItem(adminModificationSelection);
			
			return ret;
		};
		
		
		/*
		 * Let's get started
		 */
		
		
		MenuItem main = ref -> {
			System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you?");
			
			MenuItem employee = ref1 -> {
				MenuItem nextMenu = getFromMappingAppendCancel(Arrays.asList("Enter Flights You Manage"), Arrays.asList(flightsList));
				
				return new MenuReturn(flightAction, nextMenu);
			};
			
			MenuItem ret = getFromMapping(Arrays.asList("Employee", "Administrator", "Traveller"), Arrays.asList(employee, adminCrudSelection));
			
			return new MenuReturn(null, ret);
		};
		
		MenuItem currentMenu;
		Object currentRef;
		
		currentMenu = main;
		currentRef = null;
		
		for(;;) {
			MenuReturn returnValue;
			MenuItem returnedMenu;
			Object returnedRef;
			
			returnValue = currentMenu.select(currentRef);
			
			if(returnValue != null && returnValue.getNewMenuItem() != null) {
				returnedMenu = returnValue.getNewMenuItem();
				returnedRef = returnValue.getReturnValue();
				
				menuStack.push(currentMenu);
				objectStack.push(returnedRef);
				
				currentRef = returnedRef;
				currentMenu = returnedMenu;
			} else {
				currentRef = objectStack.pop();
				currentMenu = menuStack.pop();
			}
		}
	}
}
