package com.we.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFactory {
	private static Connection conn = null;
	private static DbFactory dbFactory = null;
	private final static String DBNAME = "atm";
	public final static int TYPE_ADMIN= 1;
	public final static int TYPE_USER= 0;
	
	private DbFactory() {
	}

	public static DbFactory getInstance() {
		if(dbFactory == null){
			dbFactory = new DbFactory();
		}
		return dbFactory;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://172.25.155.22:3307/"+DBNAME, "root", "test");
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return conn;
	}

}
