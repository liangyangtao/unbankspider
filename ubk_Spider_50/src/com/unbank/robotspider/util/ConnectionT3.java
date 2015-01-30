package com.unbank.robotspider.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionT3 {
	static Connection conn = null;

	public static Connection getConn() {

		if (conn == null) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://10.0.0.50:3306/ubk_platform", "root", "123456");
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return conn;
		}

		return conn;
	}

	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
