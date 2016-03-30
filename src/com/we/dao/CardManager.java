package com.we.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardManager {
	private static Connection conn = null;
	private static CardManager dbManager = null;
	private final static String TB_CARD = "card";
//	private final static String TB_TRADE= "tradeinfo";
//	private final static String TB_USER= "UserInfo";

	private String cardNum ;
	private String cardPwd;
	private String cardId;
	
	private void testing(){
		cardId = "1001";
		cardNum = "1001";
		cardPwd = "1";
	}
	private CardManager() {
		testing();
	}

	public static CardManager getInstance() {
		if(dbManager == null){
			dbManager = new CardManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	public boolean queryLogin(String usename, String password) {
		PreparedStatement preSt = null;
		ResultSet rs = null;
		String sql = "select * from " + TB_CARD
			+ " where cardNum = ? AND password = ?";
		try {
			preSt = conn.prepareStatement(sql);
			preSt.setString(1, usename);
			preSt.setString(2, password);
			rs = preSt.executeQuery();
			if (rs.next()) {
				cardId = rs.getString("cardID");
				cardNum = rs.getString("cardNum");
				cardPwd = rs.getString("password");
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

	public boolean saveCash(int money) {
		int cash = dbManager.queryCash();
		cash += money;
		String sql = "UPDATE "+TB_CARD+" SET cash= "+ cash+" WHERE cardID = "+cardId;
		try {
			Statement st = conn.createStatement();
			if( 0 != st.executeUpdate(sql) ){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean takeCash(int money) {
		int oldCash = dbManager.queryCash();
		money -= oldCash;

		System.out.println("旧款:"+oldCash+" 新款:"+money);
		String sql = "UPDATE "+TB_CARD+" SET cash= "+ money+" WHERE cardID = "+cardId;
		try {
			Statement st = conn.createStatement();
			if( 0 != st.executeUpdate(sql) ){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int queryCash() {
		String sql = "SELECT * FROM "+TB_CARD + " WHERE cardID = "+cardId;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt("cash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
