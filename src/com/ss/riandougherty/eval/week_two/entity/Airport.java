package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Airport extends BaseEntity {
	private String iata_id, city;

	public Airport(final String iata_id, final String city) {
		this.iata_id = iata_id;
		this.city = city;
	}
	
	public String getIata_id() {
		return iata_id;
	}

	public void setIata_id(final String iata_id) {
		this.iata_id = iata_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("IATA", iata_id));
		ret.add(new NameValuePair<Object>("City", city));
		
		return ret;
	}
}
