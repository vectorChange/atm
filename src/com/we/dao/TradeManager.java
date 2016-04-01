package com.we.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TradeManager {
	private static Connection conn = null;
	private static TradeManager dbManager = null;
	private final static String TB_TRADE = "tradeinfo";

	private String cardId;
	private String transId;
	
	private void testing(){
		cardId = "1001";
	}
	private TradeManager() {
		testing();
	}

	public static TradeManager getInstance() {
		if(dbManager == null){
			dbManager = new TradeManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}


	public boolean insertTrade(String transDate, String transType, String transMoney, String target) {
//		String sql = "INSERT INTO "+TB_TRADE+"(transId,cardID,transdate,transType,transMoney,target) VALUES( "
//				+ tradeDate+","+tradeType+","+","+
		String sql = "INSERT INTO "+TB_TRADE+"(cardID,transdate,transType,transMoney,target) VALUES( "
				+ cardId +","+ transDate+","+transType+","+transMoney+","+target+" )";
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
	
	//TODO 返回值类型？
	public int queryTradeInfo() {
		String sql = "SELECT * FROM "+TB_TRADE + " WHERE cardID = "+cardId;
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
