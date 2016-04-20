package com.we.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CardNumManager {

	private static CardNumManager dbManager;

	private static Connection conn;
	
	private static final String TABLE_NAME = "cardnum";

	private CardNumManager() {
	}

	public static CardNumManager getInstance() {
		if (dbManager == null) {
			dbManager = new CardNumManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	/**
	 * 获取下一个卡号
	 * @return
	 */
	public String getNextNum() {
		String retVal = null;
		String sql = "SELECT * FROM " + TABLE_NAME ;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			retVal = rs.getString("maxNum");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(new BigInteger(retVal).add(BigInteger.ONE));
	}

	/**
	 * 更新数据库中的表maxNum
	 * @param num
	 */
	public void setNum(String num) {
		String sql = "UPDATE " + TABLE_NAME + " SET maxNum = \'" + num + "\'";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
