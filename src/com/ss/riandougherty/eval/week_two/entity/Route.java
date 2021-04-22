package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Route extends BaseEntity {
	private Integer id;
	private Airport origin, destination;
	
	public Route(final Airport origin, final Airport destination) {
		this.origin = origin;
		this.destination = destination;
	}
	
	public Route(final int id, final Airport origin, final Airport destination) {
		this(origin, destination);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
	}
	
	public Airport getOrigin() {
		return this.origin;
	}
	
	public void setOrigin(final Airport value) {
		this.origin = value;
	}
	
	public Airport getDestination() {
		return this.destination;
	}
	
	public void setDestination(final Airport value) {
		this.destination = value;
	}
	
	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Origin Airport", origin.getIata_id()));
		ret.add(new NameValuePair<Object>("Destination Airport", destination.getIata_id()));
		
		return ret;
	}
	
	@Override
	public String toString() {
		return origin.getIata_id() + " -> " + destination.getIata_id();
	}
}
