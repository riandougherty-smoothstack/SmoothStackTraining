package com.ss.riandougherty.eval.week_two.entity;

import java.util.ArrayList;
import java.util.List;

import com.ss.riandougherty.eval.week_two.util.NameValuePair;

public final class User extends BaseEntity {
	private Integer id;
	private String firstName, lastName, username, email, password, phone;
	private UserRole role;
	
	public User(final String firstName, final String lastName, final String username, final String email, final String password, final String phone, final UserRole role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}
	
	public User(final int id, final String firstName, final String lastName, final String username, final String email, final String password, final String phone, final UserRole role) {
		this(firstName, lastName, username, email, password, phone, role);
		
		this.id = id;
	}
	
	public Integer getID() {
		return this.id;
	}
	
	public void setID(final int id) {
		this.id = id;
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
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(final String value) {
		this.username = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(final String value) {
		this.email = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(final String value) {
		this.password = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(final String value) {
		this.phone = value;
	}
	
	public UserRole getRole() {
		return this.role;
	}
	
	public void setRole(final UserRole value) {
		this.role = value;
	}
	
	@Override
	public List<NameValuePair<? extends Object>> getNameValuePairs() {
		List<NameValuePair<? extends Object>> ret = new ArrayList<>();
		
		ret.add(new NameValuePair<Object>("First Name", firstName));
		ret.add(new NameValuePair<Object>("Last Name", lastName));
		ret.add(new NameValuePair<Object>("Username", username));
		ret.add(new NameValuePair<Object>("Email", email));
		ret.add(new NameValuePair<Object>("Password", password));
		ret.add(new NameValuePair<Object>("Phone", phone));
		ret.add(new NameValuePair<Object>("Role", role.toString()));
		
		return ret;
	}
}
