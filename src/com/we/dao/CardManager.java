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
	private final static int NO_EXIT= -1;
	
	private String cardNum ;
	private String cardPwd;
	private int cardId;
	
	private void testing(){
		setCardId(1);
		setCardNum("1001");
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
				setCardId(rs.getInt("cardId"));
				setCardNum(rs.getString("cardNum"));
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

	public boolean saveCash(double money) {
		double cash = dbManager.queryCash();
		System.out.println("存款: 旧款:"+cash+" 新款:"+(cash+money));
		cash += money;
		String sql = "UPDATE "+TB_CARD+" SET cash= "+ cash+" WHERE cardId = "+getCardId();
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
	public boolean saveCash(int otherCardId,double money) {
		double cash = dbManager.queryCash(otherCardId);
		cash += money;
		String sql = "UPDATE "+TB_CARD+" SET cash= "+ cash+" WHERE cardId = "+otherCardId;
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
	
	public boolean takeCash(double money) {
		double cash = dbManager.queryCash();
		System.out.println("取款: 旧款:"+cash+" 新款:"+(cash-money));
		cash -= money;
		String sql = "UPDATE "+TB_CARD+" SET cash= "+ cash+" WHERE cardId = "+getCardId();
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
	
	public double queryCash() {
		String sql = "SELECT * FROM "+TB_CARD + " WHERE cardId = "+getCardId();
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
	public double queryCash(int otherCardId) {
		String sql = "SELECT * FROM "+TB_CARD + " WHERE cardId = "+otherCardId;
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
	public int getCardIdByCardNum(String cardNum){
		String sql = "SELECT * FROM "+TB_CARD + " WHERE cardNum = "+cardNum;
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
	public boolean transfersCash(String othersCardNum,double money) {
		double cash = dbManager.queryCash();
		if( Double.compare(cash, money) < 0 || othersCardNum == null || othersCardNum.equals("")){
			return false;
		}
		int othersCardId = getCardIdByCardNum(othersCardNum);
		if(othersCardId == NO_EXIT){
			System.out.println("对方卡号不存在");
			return false;
		}
		try {
			conn.setAutoCommit(false);
			takeCash(money);
			saveCash(othersCardId,money);
			conn.commit();			//提交事务
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();	//事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
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
