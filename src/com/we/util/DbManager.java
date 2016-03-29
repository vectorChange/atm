package com.we.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbManager {
	private static Connection conn = null;
	private static DbManager dbManager = null;
	private final static String DBNAME = "atm";
	private final static String TB_CARD = "card";
	private final static String TB_TRADE= "tradeinfo";
	private final static String TB_USER= "UserInfo";
	private final static String TB_ADMIN= "AdminInfo";
	public final static int TYPE_ADMIN= 1;
	public final static int TYPE_USER= 0;

	private DbManager() {
	}

	public static DbManager getInstance() {
		if(dbManager == null){
			dbManager = new DbManager();
			conn = getConnection();
		}
		return dbManager;
	}

	private static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3307/"+DBNAME, "root", "test");
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return conn;
	}

	public boolean queryLogin(int type,String usename, String password) {
		PreparedStatement preSt = null;
		ResultSet rs = null;
		String sql ="";
		switch (type) {
		case 0:
			sql = "select * from " + TB_CARD
			+ " where cardNum = ? AND password = ?";
			break;
		case 1:
			sql = "select * from " + TB_ADMIN
			+ " where adminName = ? AND password = ?";
			break;
		default:
			break;
		}
		
		try {
			preSt = conn.prepareStatement(sql);
			preSt.setString(1, usename);
			preSt.setString(2, password);
			rs = preSt.executeQuery();
			if (rs.next()) {
//				System.out.println("登陆成功");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				preSt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
