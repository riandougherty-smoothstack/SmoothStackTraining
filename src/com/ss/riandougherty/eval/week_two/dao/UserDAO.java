package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.User;
import com.ss.riandougherty.eval.week_two.entity.UserRole;

public final class UserDAO extends BaseDAO<User> {
	public UserDAO(final ConnectionManager cm, final User user) {
		super(cm, UserDAO.class);
		
		Map<String, Object> tmpProperties = this.propertyMapping.get("user").getProperties();
		
		tmpProperties.put("id", user.getID());
		tmpProperties.put("role_id", user.getRole().ordinal());
		tmpProperties.put("given_name", user.getFirstName());
		tmpProperties.put("family_name", user.getLastName());
		tmpProperties.put("username", user.getUsername());
		tmpProperties.put("email", user.getUsername());
		tmpProperties.put("password", user.getUsername());
		tmpProperties.put("phone", user.getUsername());
	}
	
	public UserDAO(final ConnectionManager cm, final Object keyValue) throws SQLException {
		super(cm, UserDAO.class);
		
		populateDAOTable(this.propertyMapping.get("user"), keyValue);
	}

	@Override
	public User getEntity() throws SQLException {
		DAOTable tmpDAOTable;
		tmpDAOTable = this.propertyMapping.get("user");
		
		Object key;
		Integer keyTyped;
		Object roleID, firstName, lastName, username, email, password, phone;
		
		key = tmpDAOTable.getKey();
		
		if(key == null) {
			keyTyped = null;
		} else {
			keyTyped = (int) (long) key;
		}
		
		roleID = tmpDAOTable.getProperty("role_id");
		firstName = tmpDAOTable.getProperty("given_name");
		lastName = tmpDAOTable.getProperty("family_name");
		username = tmpDAOTable.getProperty("username");
		email = tmpDAOTable.getProperty("email");
		password = tmpDAOTable.getProperty("password");
		phone = tmpDAOTable.getProperty("phone");
		
		throwErrorIfNull(Arrays.asList(roleID, firstName, lastName, username, email, password, phone));
		
		return new User(keyTyped, (String) firstName, (String) lastName, (String) username, (String) email, (String) password, (String) phone, UserRole.values()[((int) (long) roleID) - 1]);
	}
}
