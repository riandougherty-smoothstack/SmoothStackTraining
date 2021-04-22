package com.ss.riandougherty.eval.week_two.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ss.riandougherty.eval.week_two.ConnectionManager;
import com.ss.riandougherty.eval.week_two.entity.BaseEntity;
import com.ss.riandougherty.eval.week_two.util.ConnectionConsumer;
import com.ss.riandougherty.eval.week_two.util.NameValuePair;
import com.ss.riandougherty.eval.week_two.util.SQLUtil;
import com.ss.riandougherty.eval.week_two.util.StringUtil;

/*
 * Base DAO implementing all CRUD operations.
 */
public abstract class BaseDAO<T extends BaseEntity> {
	protected ConnectionManager cm;
	protected Map<String, DAOTable> propertyMapping;
	private Map<DAOTable, Object> newPrimaryKeyValues;
	
	public BaseDAO(final ConnectionManager cm, final Map<String, DAOTable> properties) {
		this.cm = cm;
		this.propertyMapping = properties;
	}
	
	public BaseDAO(final ConnectionManager cm, final Class<? extends BaseDAO> classObj) {
		this(cm, DAOMapping.getMapping(classObj).generateDAOPart());
	}
	
	public void populateDAOTable(final DAOTable daoTable, final Object keyValue) throws SQLException {
		final String query = SQLUtil.generateSelect(daoTable.getTableName(), null, daoTable.getPrimaryKeyName());
		
		final Map<String, Object> toPopulate = daoTable.getProperties();
		
		final ResultSet rs = SQLUtil.execute(cm.getConnection(), query, Arrays.asList(keyValue));
		final ResultSetMetaData rsMeta = rs.getMetaData();
		
		int columns = rs.getMetaData().getColumnCount();
		
		if(rs.next()) {
			int a;
			
			for(a = 1; a <= columns; a++) {
				toPopulate.put(rsMeta.getColumnLabel(a), rs.getObject(a));
			}
		}
	}
	
	public Map<String, DAOTable> getPropertyMapping() {
		return this.propertyMapping;
	}
	
	public abstract T getEntity() throws SQLException;
	
	protected static void throwErrorIfNull(final Collection<Object> objects) {
		for(final Object object : objects) {
			if(object == null) {
				throw new NullPointerException();
			}
		}
	}
	
	public void saveProperties(final Connection con) throws SQLException {
		newPrimaryKeyValues = new HashMap<>();
		
		for(final DAOTable daoTable : propertyMapping.values()) {
			String sql;
			
			// if a table has auto increment off, then we have to supply our own primary key.
			// so in the case that it doesn't exist but still has a primary key set, we don't want to remove the primary key from the list of objects to save.
			
			boolean exists;
			if(daoTable.getKey() != null) {
				// just because there is a key doesn't mean it exists.
				// so we query for the key first.
				
				sql = SQLUtil.generateSelect(daoTable.getTableName(), daoTable.getPrimaryKeyName(), daoTable.getPrimaryKeyName());
				
				exists = SQLUtil.execute(con, sql, Arrays.asList(daoTable.getKey())).next();
			} else {
				// if it doesn't have a key then it doesn't exist.
				exists = false;
			}
			
			// we need to always create a new map since if it doesn't exist, we need to modify the properties
			// and associate it with a key.
			final Map<String, Object> toSave = new LinkedHashMap<>(daoTable.getProperties());
			
			if(exists) {
				// effectively move primary key to last argument
				// also remove primary key when generating update statement
				toSave.remove(daoTable.getPrimaryKeyName());
				
				sql = SQLUtil.generateUpdate(daoTable.getTableName(), toSave.keySet(), daoTable.getPrimaryKeyName());
				
				// add it after as last argument for where
				toSave.put(daoTable.getPrimaryKeyName(), daoTable.getKey());
			} else {
				sql = SQLUtil.generateInsert(daoTable.getTableName(), toSave.keySet());
			}
			
			final ResultSet rs;
			
			rs = SQLUtil.execute(con, sql, toSave.values());
			
			if(!exists) {
				// save to class variable in case query fails
				// must call OK() or No_OK() afterwards
				
				rs.next();
				newPrimaryKeyValues.put(daoTable, rs.getObject(1));
			}
		}
	}
	
	public void delete(final Connection con) throws SQLException {
		newPrimaryKeyValues = new HashMap<>();
		
		for(final DAOTable daoTable : propertyMapping.values()) {
			if(daoTable.getKey() != null) {
				final String query = SQLUtil.generateDelete(daoTable.getTableName(), daoTable.getPrimaryKeyName());
				
				SQLUtil.execute(con, query, Arrays.asList(daoTable.getKey()));
				
				newPrimaryKeyValues.put(daoTable, null);
			}
		}
	}
	
	private void throwErrorIfNotInTransaction() throws Exception {
		if(newPrimaryKeyValues == null) {
			throw new Exception("Not in transaction");
		}
	}
	
	public void OK() throws Exception {
		throwErrorIfNotInTransaction();
		
		newPrimaryKeyValues.forEach((daoTable, newKey) -> {
			daoTable.setKey(newKey);
		});
		
		newPrimaryKeyValues = null;
	}
	
	public void No_OK() throws Exception {
		throwErrorIfNotInTransaction();
		
		newPrimaryKeyValues = null;
	}
	
	protected void doAtomic(final ConnectionConsumer toExecute) throws Exception {
		final Connection con = cm.getConnection();
		
		// even though it looks like we can throw "Not in transaction" exception, it will never get triggered since we
		// create a new transaction.
		
		try {
			toExecute.accept(con);
			con.commit();
		} catch(final SQLException e) {
			No_OK();
			
			throw e;
		} finally {
			con.close();
		}
		
		// will not execute if there is an exception.
		OK();
	}
	
	public void savePropertiesOK() throws Exception {
		doAtomic(con -> saveProperties(con));
	}
	
	public void deleteOK() throws Exception {
		doAtomic(con -> delete(con));
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		
		final List<NameValuePair<? extends Object>> nvps;
		nvps = propertyMapping.entrySet().stream().map(entry -> {
			return entry.getKey() != null ? new NameValuePair<Object>(entry.getKey(), entry.getValue()) : null;
		}).collect(Collectors.toUnmodifiableList());
		
		StringUtil.getFormattedStringFromNVPS(nvps);
		
		return sb.toString();
	}
}
