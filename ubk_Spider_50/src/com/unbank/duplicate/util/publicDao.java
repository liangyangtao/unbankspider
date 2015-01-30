package com.unbank.duplicate.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class publicDao {
	private Connection conn;

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	private ConnectionFactory dbConFactory = new ConnectionFactory();
	private PoolManager dmgr = PoolManager.getInstance();
	private Statement qtstmt = null;
	public publicDao() {
	}
	// 日志
	public static Logger logger = Logger.getLogger(publicDao.class);

	public ArrayList getRsList(String dbName, String sql) throws Exception {
		ArrayList rtlist = new ArrayList();
		HashMap dmap = null;
		try {
			conn = dbConFactory.getConnection(dbName);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int fields = rsmd.getColumnCount();
			while (rs.next()) {
				dmap = new HashMap();
				for (int is = 1; is < fields + 1; is++) {
					dmap.put(rsmd.getColumnName(is).toLowerCase(), rs.getString(rsmd.getColumnName(is)));
				}
				rtlist.add(dmap);
			}
			ps.close();
//			if (ResourceBundle.getBundle("dbconfig").getString("debugprint") != null && ResourceBundle.getBundle("dbconfig").getString("debugprint").trim().equals("true")) {
//				System.out.println(sql);
//			}
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			conn.close();
			return rtlist;
		}
	}


	public HashMap getRsMap(String dbName, String sql) throws Exception {
		HashMap dmap = null;
		try {
			conn = dbConFactory.getConnection(dbName);
			conn.setAutoCommit(true);
			qtstmt = conn.createStatement();
			ResultSet qtrs = qtstmt.executeQuery(sql);
			ResultSetMetaData rsmd = qtrs.getMetaData();
			int fields = rsmd.getColumnCount();
			//qtrs.close();
			qtstmt = conn.createStatement();

			rs = qtstmt.executeQuery(sql);

			if (rs.next()) {
				dmap = new HashMap();
				for (int is = 1; is < fields + 1; is++) {
					dmap.put(rsmd.getColumnName(is).toLowerCase(), rs.getString(rsmd.getColumnName(is)));
				}
			}
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			//rs.close();
			qtstmt.close();
			conn.close();
			return dmap;
		}
	}
	public String getCusRsFirst(String dbName, String sql) throws Exception {
		//dbName = "form1";
		HashMap dmap = null;
		String typename = "";
		try {
			conn = dbConFactory.getConnection(dbName);
			conn.setAutoCommit(true);
			qtstmt = conn.createStatement();

			rs = qtstmt.executeQuery(sql);

			if (rs.next()) {
				typename = rs.getString(1);
			}
			qtstmt.close();
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			//rs.close();
			conn.close();
			return typename;
		}
	}

	public void doSql(String dbName, String sql) throws Exception { //
		//System.out.println(sql);
		//dbName = "form1"; //
		HashMap dmap = null;
		try {
			conn = dbConFactory.getConnection(dbName);
			conn.setAutoCommit(true);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			conn.close();
		}
	}
	




	public String getMaxId(String dbName, String sql) throws Exception {
		String id = null;
		//dbName = dbName.toUpperCase();
		try {
			conn = dbConFactory.getConnection(dbName);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
			}
			ps.close();
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			conn.close();
			return id;
		}

	}
	
}
