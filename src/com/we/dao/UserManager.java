package com.we.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManager {
	private static Connection conn = null;
	private static UserManager dbManager = null;
//	private final static String TB_TRADE= "tradeinfo";
	private final static String TB_USER= "userinfo";
	private final static int NO_EXIT= -1;
	
	private String cardNum ;
	private String cardPwd;
	private int cardId;
	
	private void testing(){
		cardPwd = "1";
	}
	
	private UserManager() {
		testing();
	}

	public static UserManager getInstance() {
		if(dbManager == null){
			dbManager = new UserManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	public String getUserNameByCardId(int userId){
		String sql = "SELECT userName FROM "+TB_USER + " WHERE userId = "+userId;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getString("userName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
