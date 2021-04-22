package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Booking extends BaseEntity {
	private Integer id;
	private boolean isActive;
	private String confirmationCode;
	private User agent, user;
	private Flight flight;
	
	public Booking(final boolean isActive, final String confirmationCode, final User agent, final User user, final Flight flight) {
		this.isActive = isActive;
		this.confirmationCode = confirmationCode;
		this.flight = flight;
		this.agent = agent;
		this.user = user;
	}
	
	public Booking(final Integer id, final boolean isActive, final String confirmationCode, final User agent, final User user, final Flight flight) {
		this(isActive, confirmationCode, agent, user, flight);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	public void setActive(final boolean value) {
		this.isActive = value;
	}
	
	public String getConfirmationCode() {
		return this.confirmationCode;
	}
	
	public void setConfirmationCode(final String value) {
		this.confirmationCode = value;
	}
	
	public User getAgent() {
		return this.agent;
	}
	
	public void setAgent(final User value) {
		this.agent = value;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(final User value) {
		this.user = value;
	}
	
	public Flight getFlight() {
		return this.flight;
	}
	
	public void setFlight(final Flight value) {
		this.flight = value;
	}

	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Active", isActive));
		ret.add(new NameValuePair<Object>("Confirmation Code", confirmationCode));
		ret.add(new NameValuePair<Object>("Flight", flight.toString()));
		
		return ret;
	}
}
