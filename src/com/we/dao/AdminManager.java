package com.we.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminManager {

	private static Connection conn = null;
	private static AdminManager dbManager = null;
	private final static String TB_ADMIN = "AdminInfo";
	
	private String adminId;
	private int adminType;

	private AdminManager() {
	}

	public static AdminManager getInstance() {
		if (dbManager == null) {
			dbManager = new AdminManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	/**
	 * 通过对数据库的查询，得知是否登陆成功
	 * @param usename 用户输入的账号
	 * @param password 用户输入的登陆密码
	 * @return [true]成功[false]失败
	 */
	public boolean queryLogin(String usename, String password) {
		PreparedStatement preSt = null;
		ResultSet rs = null;
		String sql = "select * from " + TB_ADMIN
				+ " where adminName = ? AND password = ?";
		try {
			preSt = conn.prepareStatement(sql);
			preSt.setString(1, usename);
			preSt.setString(2, password);
			rs = preSt.executeQuery();
			if (rs.next()) {
				adminId = rs.getString("adminId");
				adminType = rs.getInt("adminType");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preSt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean updatePwd(String newPwd) {

		String sql = "UPDATE " + TB_ADMIN + " SET password = " + newPwd
				+ " WHERE adminId = " + adminId;
		try {
			Statement st = conn.createStatement();
			if (0 != st.executeUpdate(sql)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getAdminType() {
		return this.adminType;
	}
	
	
}
