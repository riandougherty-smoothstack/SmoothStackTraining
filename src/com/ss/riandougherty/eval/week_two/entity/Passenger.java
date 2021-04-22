package com.ss.riandougherty.eval.week_two.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Passenger extends BaseEntity {
	private Integer id;
	private Booking booking;
	private String firstName, lastName, address, gender;
	public Date dateOfBirth;
	
	public Passenger(final Booking booking, final String firstName, final String lastName, final String address, final String gender, final Date dateOfBirth) {
		this.booking = booking;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Passenger(final Integer id, final Booking booking, final String firstName, final String lastName, final String address, final String gender, final Date dateOfBirth) {
		this(booking, firstName, lastName, address, gender, dateOfBirth);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public Booking getBooking() {
		return this.booking;
	}
	
	public void setBookingID(final Booking value) {
		this.booking = value;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(final String value) {
		this.firstName = value;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(final String value) {
		this.lastName = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(final String value) {
		this.address = value;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(final String value) {
		this.gender = value;
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(final Date value) {
		this.dateOfBirth = value;
	}

	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Booking", booking.getID()));
		ret.add(new NameValuePair<Object>("First Name", firstName));
		ret.add(new NameValuePair<Object>("Last Name", lastName));
		ret.add(new NameValuePair<Object>("Address", address));
		ret.add(new NameValuePair<Object>("Gender", gender));
		ret.add(new NameValuePair<Object>("Date of Birth", dateOfBirth.toString()));
		
		return ret;
	}
}
