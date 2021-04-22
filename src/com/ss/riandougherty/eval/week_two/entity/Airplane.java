package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Airplane extends BaseEntity {
	private int id;
	private int max_capacity;
	
	public Airplane(final int id, final int max_capacity) {
		this.id = id;
		this.max_capacity = max_capacity;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
	}
	
	public int getMaxCapacity() {
		return this.max_capacity;
	}
	
	public void setMaxCapacity(final int value) {
		this.max_capacity = value;
	}

	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Max Capacity", max_capacity));
		
		return ret;
	}
}
