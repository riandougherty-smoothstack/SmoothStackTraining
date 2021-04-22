package com.ss.riandougherty.eval.week_two.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionConsumer {
	public void accept(Connection con) throws SQLException;
}
