package com.we.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManager {
	private static Connection conn = null;
	private static UserManager dbManager = null;
	// private final static String TB_TRADE= "tradeinfo";
	private final static String TB_USER = "userinfo";
	private final static int NO_EXIT = -1;

	private String cardNum;
	private String cardPwd;
	private int cardId;

	private void testing() {
		cardPwd = "1";
	}

	private UserManager() {
		testing();
	}

	public static UserManager getInstance() {
		if (dbManager == null) {
			dbManager = new UserManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	public String getUserNameByUserId(int userId) {
		String sql = "SELECT userName FROM " + TB_USER + " WHERE userId = "
				+ userId;
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

	/**
	 * 依据personId[身份证号]查询是否有该人，有则返回用户id[userId]，无[-1]
	 * 
	 * @param personId
	 * @return
	 */
	public int getUserIdByPersonId(String personId) {
		int retVal = -1;
		String sql = "SELECT userId FROM " + TB_USER + " WHERE personID = "
				+ personId;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				retVal = rs.getInt("userId");
				return retVal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	/**
	 * phone 可以为null
	 * 
	 * @param Name
	 * @param idCard
	 * @param phone
	 */
	public void createUser(String Name, String idCard, String phone) {
		StringBuilder sb = new StringBuilder();
		String token = ", ";
		String quote = "\'";
		sb.append("INSERT INTO " + TB_USER + " ( userName, personID ");
		if (phone != null) {
			sb.append(token + " phone");
		}
		sb.append(")  values ( ");
		sb.append(quote + Name + quote + token + quote + idCard + quote);
		if (phone != null) {
			sb.append(token + quote + phone + quote);
		}
		sb.append(" )");
		String sql = sb.toString();
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
