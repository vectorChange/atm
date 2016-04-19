package com.we.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Card表和User表合并查询等 两表沟通渠道
 * 
 * @author xiaoguang
 *
 */
public class CardUserManager {

	private static UserManager userManager;
	private static CardManager cardManager;
	private static CardUserManager cardUserManager;
	public static String CARDUSER_ERROR = null;
	private static Connection connection;

	private CardUserManager() {
	}

	public static CardUserManager getInstance() {
		if (cardUserManager == null) {
			cardUserManager = new CardUserManager();
			cardManager = CardManager.getInstance();
			userManager = UserManager.getInstance();
			connection = DbFactory.getInstance().getConnection();
		}
		return cardUserManager;
	}

	public String getUserNameByCardId(int cardId) {
		int userId = cardManager.getUserIdByCardId(cardId);
		if (userId == CardManager.NO_EXIT) {
			return CARDUSER_ERROR;
		} else {
			return userManager.getUserNameByUserId(userId);
		}
	}

	public String getUserNameByCardNum(String cardNum) {
		int userId = cardManager.getUserIdByCardNum(cardNum);
		if (userId == CardManager.NO_EXIT) {
			return CARDUSER_ERROR;
		} else {
			return userManager.getUserNameByUserId(userId);
		}
	}

	/**
	 * 返回UserState的ArrayList
	 * 
	 * @param personId
	 * @return
	 */
	public String[][] getCardStatusByPersonId(String personId) {
		String[][] strs = null;
		String sql = "select "
				+ " card.cardNum, card.cash, card.frozen, card.closed, card.loss"
				+ " from userinfo inner join card on userinfo.userId = card.userId"
				+ " where userinfo.personId = '" + personId + "'";

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			strs = new String[rs.getRow()][4];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				strs[i][0] = String.valueOf(i + 1);
				strs[i][1] = rs.getString("cardNum");
				strs[i][2] = String.valueOf(rs.getDouble("cash"));
				int frozen = rs.getInt("frozen");
				int closed = rs.getInt("closed");
				int loss = rs.getInt("loss");
				String status;
				if (closed == 1) {
					status = "卡号注销";
				} else if (frozen == 1) {
					status = "卡号冻结";
				} else if (loss == 1) {
					status = "卡号挂失";
				} else {
					status = "正常";
				}
				strs[i][3] = status;
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strs;
	}

	public String getCardUserStatusByCardNum(String cardNum) {
		StringBuilder sb = new StringBuilder();
		String sql = "SELECT * from "
				+ "userinfo INNER JOIN card ON userinfo.userId = card.userId"
				+ " WHERE card.CardNum = '" + cardNum + "'";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			sb.append("用户姓名：" + rs.getString("userName") + "\n");
			sb.append("身份证号：" + rs.getString("personID") + "\n");
			sb.append("绑定手机：" + rs.getString("phone") + "\n");
			sb.append("剩余金额：" + rs.getDouble("cash") + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
