package com.we.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

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
	public void createUser(String Name, String idCard, String phone,
			String sex, String address) {
		String sql = " INSERT INTO "
				+ " userinfo (userName, personID, phone, sex, address) "
				+ " VALUES( '" + Name + "', '" + idCard + "', '" + phone
				+ "', '" + sex + "', '" + address + "')";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUserInfo(String personId) {
		StringBuilder sb = new StringBuilder();
		String sql = "select * from userinfo where personId = '" + personId
				+ "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			sb.append("用户姓名：" + rs.getString("userName") + "\n");
			sb.append("用户性别：" + rs.getString("sex") + "\n");
			sb.append("绑定手机：" + rs.getString("phone") + "\n");
			sb.append("用户地址：" + rs.getString("address") + "\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void updateUserInfoByPersonId(String personId, String name,
			String phone, String sex, String address) {
		String sql = "UPDATE userinfo SET userName = '" + name + "'"
				+ " , phone = '" + phone + "'" + " , sex = '" + sex + "'"
				+ " , address = '" + address + "' WHERE personID = '"
				+ personId + "'";
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
