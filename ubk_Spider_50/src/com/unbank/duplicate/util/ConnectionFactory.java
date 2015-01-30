package com.unbank.duplicate.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public final class ConnectionFactory {
	private static PoolManager dmgr = PoolManager.getInstance();
	private static Connection conn;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private static ConnectionFactory dbConFactory = null;
	// 日志
	public static Logger logger = Logger.getLogger(ConnectionFactory.class);
	public static synchronized ConnectionFactory getInstance() {
		if (dbConFactory == null) {
			dbConFactory = new ConnectionFactory();
		}
		return dbConFactory;
	}

	public static Connection getConnection(boolean commit, String id) throws SQLException {
		try {
			conn = dmgr.getConnection(id);
			conn.setAutoCommit(commit);
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		}

		return conn;

	}

	public static Connection getConnection(boolean commit) throws SQLException {
		try {
			conn = dmgr.getConnection("form1");
			conn.setAutoCommit(commit);
		} catch (SQLException e) {
			publicTools.loggerException(logger, e);
		}
		return conn;
	}

	public static Connection getConnection() throws SQLException {
		try {
			conn = dmgr.getConnection("form1");
			conn.setAutoCommit(true);
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		}
		return conn;
	}

	public static Connection getConnection(String dbname) throws SQLException {
		try {
			conn = dmgr.getConnection(dbname);
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		}
		return conn;
	}

	public static void main(String args[]) throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		try {
			String sql = "select * from tdept";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("aaaaa");
			}
			ConnectionFactory conf = ConnectionFactory.getInstance();
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			conn.close();
		}
	}

}
