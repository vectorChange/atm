package com.we.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.we.util.DateUtil;

public class CardManager {
	private static Connection conn = null;
	private static CardManager dbManager = null;
	private final static String TB_CARD = "card";
	public final static String CARD_STATE_NORMAL = "正常";
	public final static String CARD_STATE_FROZEN = "冻结";
	public final static String CARD_STATE_LOSS = "挂失";
	public final static String CARD_STATE_CLOSED = "注销";
	public final static int CARD_FLAG_FROZEN = 1;
	public final static int CARD_FLAG_UNFROZEN = 0;
	public final static int NO_EXIT = -1;

	private String cardNum;
	private int cardId;

	// 测试用
	private void testing() {
		setCardId(1);
		setCardNum("1001");
	}

	private CardManager() {
		testing();
	}

	public static CardManager getInstance() {
		if (dbManager == null) {
			dbManager = new CardManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}

	/**
	 * 返回用户持有卡的状态
	 * 
	 * @param cardNum
	 *            卡号
	 * @return 卡的状态 [状态优先 冻结状态>挂失状态]
	 */
	public String queryCardState(String cardNum) {
		ResultSet rs = null;
		String sql = "select * from " + TB_CARD + " where cardNum = " + cardNum;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				if (rs.getBoolean("closed")) {
					return CARD_STATE_CLOSED;
				} else if (rs.getBoolean("frozen")) {
					return CARD_STATE_FROZEN;
				} else if (rs.getBoolean("loss")) {
					return CARD_STATE_LOSS;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return CARD_STATE_NORMAL;
	}

	/**
	 * 查询是否登陆成功
	 * @param cardNum
	 * @param password
	 * @return
	 */
	public boolean queryLogin(String cardNum, String password) {
		PreparedStatement preSt = null;
		ResultSet rs = null;
		String sql = "select * from " + TB_CARD
				+ " where cardNum = ? AND password = ?";
		try {
			preSt = conn.prepareStatement(sql);
			preSt.setString(1, cardNum);
			preSt.setString(2, password);
			rs = preSt.executeQuery();
			if (rs.next()) {
				setCardId(rs.getInt("cardId"));
				setCardNum(rs.getString("cardNum"));
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

	/**
	 * 对当前[CardID]指定的卡进行存款
	 * 
	 * @param money
	 * @return
	 */
	public boolean saveCash(double money) {
		double cash = dbManager.queryCash();
		System.out.println("存款: 旧款:" + cash + " 新款:" + (cash + money));
		cash += money;
		String sql = "UPDATE " + TB_CARD + " SET cash= " + cash
				+ " WHERE cardId = " + getCardId();
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

	/**
	 * 指定[OtherCardID]进行存款 [在转账中用到]
	 * 
	 * @param otherCardId
	 * @param money
	 * @return
	 */
	public boolean saveCash(int otherCardId, double money) {
		double cash = dbManager.queryCash(otherCardId);
		cash += money;
		String sql = "UPDATE " + TB_CARD + " SET cash= " + cash
				+ " WHERE cardId = " + otherCardId;
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

	/**
	 * 对当前指定的[CradID]进行取款
	 * 
	 * @param money
	 * @return
	 */
	public boolean takeCash(double money) {
		double cash = dbManager.queryCash();
		if (cash - money < 0.0) {
			return false;
		}
		cash -= money;
		String sql = "UPDATE " + TB_CARD + " SET cash= " + cash
				+ " WHERE cardId = " + getCardId();
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

	/**
	 * 查询当前[CardID]的与余额
	 * 
	 * @return
	 */
	public double queryCash() {
		String sql = "SELECT * FROM " + TB_CARD + " WHERE cardId = "
				+ getCardId();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getDouble("cash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NO_EXIT;
	}

	/**
	 * 查询指定[CardID]的余额
	 * 
	 * @param specifyCardId
	 * @return
	 */
	public double queryCash(int specifyCardId) {
		String sql = "SELECT * FROM " + TB_CARD + " WHERE cardId = "
				+ specifyCardId;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getDouble("cash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NO_EXIT;
	}

	public int getCardIdByCardNum(String cardNum) {
		String sql = "SELECT * FROM " + TB_CARD + " WHERE cardNum = " + cardNum;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt("cardId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NO_EXIT;
	}
	
	public int getUserIdByCardId(int cardId) {
		String sql = "SELECT userId FROM " + TB_CARD + " WHERE cardId = " + cardId;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt("userId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NO_EXIT;
	}
	public int getUserIdByCardNum(String cardNum) {
		String sql = "SELECT userId FROM " + TB_CARD + " WHERE cardNum = " + cardNum;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt("userId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NO_EXIT;
	}
	public boolean transfersCash(String othersCardNum, double money) {
		double cash = dbManager.queryCash();
		if (Double.compare(cash, money) < 0 || othersCardNum == null
				|| othersCardNum.equals("")) {
			return false;
		}
		int othersCardId = getCardIdByCardNum(othersCardNum);
		if (othersCardId == NO_EXIT) {
			System.out.println("对方卡号不存在");
			return false;
		}
		try {
			conn.setAutoCommit(false);
			takeCash(money);
			saveCash(othersCardId, money);
			conn.commit(); // 提交事务
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 依据用户id和密码创建新的卡号
	 * 
	 * @param userId
	 * @param pwd
	 */
	public void newCardForUser(int userId, String pwd, String cardNum) {
		
		String openDate = DateUtil.getDateTime();
		
		String sql = "INSERT INTO " + TB_CARD
				+ " (userId, password, openDate, cardNum) " + " VALUES ("
				+ userId + ", " + pwd + ", '" + openDate + "', '" + cardNum
				+ "');";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注销卡号
	 * 
	 * @param cardId
	 */
	public void closeCard(String cardNum) {
		String sql = "UPDATE " + TB_CARD + " SET closed = 1 WHERE cardNum = '"
				+ cardNum + "'";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 冻结卡号
	 * 
	 * @param cardNum
	 * @param flag
	 */
	public void frozenCard(String cardNum, int flag) {
		String sql = "UPDATE " + TB_CARD + " SET frozen = " + flag
				+ " WHERE cardNum = '" + cardNum + "'";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 挂失卡号
	 * 
	 * @param cardNum
	 * @param flag
	 */
	public void lostCard(String cardNum, int flag) {
		String sql = "UPDATE " + TB_CARD + " SET loss = " + flag
				+ " WHERE cardNum = '" + cardNum + "'";
		try {
			Statement st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}
