package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class Payment extends BaseEntity {
	private Integer id;
	private String stripe_id;
	private boolean refunded;
	
	public Payment(final String stripe_id, final boolean refunded) {
		this.stripe_id = stripe_id;
		this.refunded = refunded;
	}
	
	public Payment(final int id, final String stripe_id, final boolean refunded) {
		this(stripe_id, refunded);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
	}
	
	public String getStripeID() {
		return this.stripe_id;
	}
	
	public void setStripeID(final String value) {
		this.stripe_id = value;
	}
	
	public boolean isRefunded() {
		return this.refunded;
	}
	
	public void setRefunded(final boolean value) {
		this.refunded = value;
	}

	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("Stripe ID", stripe_id));
		ret.add(new NameValuePair<Object>("Is Refunded", refunded));
		
		return ret;
	}
}
