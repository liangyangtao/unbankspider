package com.unbank.robotspider.dao;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	public static SqlSessionFactory sessionFactory;

	public static SqlSessionFactory getInstanceSessionFactory() {
		if (sessionFactory == null) {
			String resource = "mybatis.xml";
			try {
				sessionFactory = new SqlSessionFactoryBuilder().build(Resources
						.getResourceAsReader(resource));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
