package com.ss.riandougherty.eval.week_two.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public class Flight extends BaseEntity {
	private Integer id;
	private Airplane airplane;
	private Route route;
	private Timestamp departureTime;
	private int takenSeats;
	private float seatPrice;
	
	public Flight(final Airplane airplane, final Route route, final Timestamp departureTime, final int takenSeats, final float seatPrice) {
		this.airplane = airplane;
		this.route = route;
		this.departureTime = departureTime;
		this.takenSeats = takenSeats;
		this.seatPrice = seatPrice;
	}
	
	public Flight(final Integer id, final Airplane airplane, final Route route, final Timestamp departureTime, final int takenSeats, final float seatPrice) {
		this(airplane, route, departureTime, takenSeats, seatPrice);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
	}
	
	public Airplane getAirplane() {
		return this.airplane;
	}
	
	public void setAirplane(final Airplane value) {
		this.airplane = value;
	}
	
	public Route getRoute() {
		return this.route;
	}
	
	public void setRoute(final Route value) {
		this.route = value;
	}
	
	public Timestamp getDepartureTime() {
		return this.departureTime;
	}
	
	public void setDepartureTime(final Timestamp value) {
		this.departureTime = value;
	}
	
	public int getTakenSeats() {
		return this.takenSeats;
	}
	
	public void setTakenSeats(final int value) {
		this.takenSeats = value;
	}
	
	public int getRemainingSeats() {
		return airplane.getMaxCapacity() - getTakenSeats();
	}
	
	public float getSeatPrice() {
		return this.seatPrice;
	}
	
	public void setSeatPrice(final float value) {
		this.seatPrice = value;
	}
	
	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Route", route.toString()));
		ret.add(new NameValuePair<Object>("Departure Time", departureTime.toString()));
		ret.add(new NameValuePair<Object>("Remaining Seats", getRemainingSeats()));
		
		return ret;
	}
}
