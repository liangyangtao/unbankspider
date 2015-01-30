package com.unbank.duplicate.util;

import java.sql.*;
import java.io.*;
import java.util.*;

import java.net.URL;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

public class PoolManager {
	private static PoolManager instance;
	private Hashtable pools = new Hashtable();
	public Statement stat = null;
	// 日志
	public static Logger logger = Logger.getLogger(ConnectionFactory.class);
	private PoolManager() {
		init();
	}

	public static synchronized PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}

	private void init() {

		Properties dbProps = new Properties();
		try {
			InputStream is =PoolManager.class.getClassLoader().getResourceAsStream("dbconfig.properties");
//			URL url = PoolManager.class.getClassLoader().getResource("service.properties");
//			String classPathName = url.getPath();
//
//			FileInputStream is = new FileInputStream(classPathName);
			dbProps.load(is);
			is.close();
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
			return;
		}
        
		createPools(dbProps);

	}

	public synchronized Connection getConnection(String name) {
		try {
			BasicDataSource bds = (BasicDataSource) pools.get(name);
			System.out.println(pools);
			if (bds == null) {
				init();
			}
			Connection conn = null;
			
			if (bds != null) {
				conn = bds.getConnection();
				if (conn == null) {
				} else {
				}
			} 
			return conn;

		} catch (Exception e) {
			publicTools.loggerException(logger, e);

			try {
				Thread.sleep(10000);
				BasicDataSource bds = (BasicDataSource) pools.get(name);
				return bds.getConnection();
			} catch (Exception e1) {
				publicTools.loggerException(logger, e1);
				return null;
			}

		}

	}

	public synchronized void release() {
		try {

			for (Enumeration e = pools.keys(); e.hasMoreElements();) {
				BasicDataSource bds = (BasicDataSource) pools.get((String) e.nextElement());
				bds.close();
				bds = null;
			}
			
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		}
	}

	private void createPools(Properties props) {
		try {
			Enumeration propNames = props.propertyNames();
			
			while (propNames.hasMoreElements()) {
				
				String name = (String) propNames.nextElement();
				if (name.endsWith(".url") && name.contains("form")) {
					String poolName = name.substring(0, name.lastIndexOf("."));
					String url = props.getProperty(poolName + ".url");
					
					if (url == null) {
						continue;
					}
					
					String drivername = props.getProperty(poolName + ".drivers");
					String user = props.getProperty(poolName + ".user");
					String password = props.getProperty(poolName + ".password");
					String maxconn = props.getProperty(poolName + ".maxconn", "0");

					BasicDataSource dataSource = null;
					Properties p = new Properties();
					p.setProperty("driverClassName", drivername);
					p.setProperty("url", url);
					p.setProperty("password", password);
					p.setProperty("username", user);
					p.setProperty("maxActive", maxconn);
					p.setProperty("InitialSize", "10");
					p.setProperty("maxIdle", "50");
					p.setProperty("minIdle", "10");
					p.setProperty("maxWait", "5000"); //超时等待时间以毫秒为单位
					p.setProperty("removeAbandoned", "true");
					p.setProperty("removeAbandonedTimeout", "60"); //以秒数为单位
					p.setProperty("testOnBorrow", "true");
					p.setProperty("testOnReturn", "true");
					p.setProperty("testWhileIdle", "true");
					p.setProperty("validationQuery", "select 1");
					p.setProperty("logAbandoned", "true");
					//logAbandoned=true的话，将会在回收事件后，在log中打印出回收Connection的错误信息，包括在哪个地方用了Connection却忘记关闭了，在调试的时候很有用
					dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
					pools.put(poolName, dataSource);
					
					if (dataSource == null) {
						init();
					} else {
					}
					
					if (dataSource == null) {
						init();
					}
					
					Connection conn = null;
					
					if (dataSource != null) {
						conn = dataSource.getConnection();
						if (conn == null) {
						} else {
						}
						conn.close();
					}

				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		} finally {

		}
	}

	public synchronized ResultSet dataQuery(String sql) throws SQLException {
		try {
			
			return stat.executeQuery(sql);
		} catch (SQLException e) {
			
			throw new SQLException("Query Error!");
		}
	}

	public synchronized String dataOperate(String sql) {
		try {
			
			stat.execute(sql);
			return "ok";
		} catch (Exception e) {
			
			return e.getMessage();
		}
	}

	public String getconnum(String name) {
		BasicDataSource bds = (BasicDataSource) pools.get(name);
		return "NumActive=" + bds.getNumActive() + "  NumIdle=" + bds.getNumIdle() + "  NumTestsPerEvictionRun=" + bds.getNumTestsPerEvictionRun();
	}

	public static void main(String args[]) throws Exception {
		System.out.println("localpath:" + ResourceBundle.getBundle("webconfig").getLocale().toString());
		Connection conn = null;
		try {
			PoolManager connMgr = PoolManager.getInstance();
			conn = PoolManager.getInstance().getConnection("form1");
			String sql = "select * from tdept";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, "tdept");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			ps.close();
			System.out.println(connMgr.getconnum("form1"));
		} catch (Exception e) {
			publicTools.loggerException(logger, e);
		} finally {
			conn.close();
		}
	}
}
