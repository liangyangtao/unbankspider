package com.unbank.robotspider.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;

public class ConnectionTools {
	
	static Connection conn = null;
	public static Map<String, String> jdbcMap = XmlUtil.getJdbcMap();

	public static Connection getConn() {

		if (conn == null) {

			try {
				System.out.println("开始连接数据库......");
				Class.forName(jdbcMap.get(FinalWord.DRIVERCLASSNAME));
				conn = DriverManager.getConnection(
						jdbcMap.get(FinalWord.JDBCURL),
						jdbcMap.get(FinalWord.USERNAME),
						jdbcMap.get(FinalWord.PASSWORD));
				System.out.println("数据库连接成功......");
				return conn;
			} catch (Exception e) {
				System.out.println("数据库连接失败!!!!");
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
