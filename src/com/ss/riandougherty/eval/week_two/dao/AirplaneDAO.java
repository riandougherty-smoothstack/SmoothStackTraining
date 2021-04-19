package com.ss.riandougherty.eval.week_two.dao;

import java.sql.SQLException;

import com.ss.riandougherty.eval.week_two.ConnectionManager;

public final class AirplaneDAO extends BaseDAO {
	public AirplaneDAO(final ConnectionManager cm, final int id) {
		super(cm, "airplane", "id", id);
	}
	
	public int getCapacity() throws SQLException {
		int type_id = this.getRSFromParams_Easy("type_id").getInt(1);
		return this.getRSFromParams_Different("max_capacity", "airplane_type", "id", type_id).getInt(1);
	}
}
