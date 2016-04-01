package com.we.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.we.bean.TradeInfo;
import com.we.util.DateUtil;

public class TradeManager {
	private static Connection conn = null;
	private static TradeManager dbManager = null;
	private final static String TB_TRADE = "tradeinfo";
	public final static int TARGET_NULL = -1;
	public final static int TRADE_TYPE_SAVE = 0;
	public final static int TRADE_TYPE_TAKE = 1;
	public final static int TRADE_TYPE_TRANSFERS_OUT = 2;
	public final static int TRADE_TYPE_TRANSFERS_IN = 3;
	private int cardId;
//	private int tradeId;
//	private String tradeDate;
	
	private TradeManager() {
		setCardId(CardManager.getInstance().getCardId());
	}

	public static TradeManager getInstance() {
		if(dbManager == null){
			dbManager = new TradeManager();
			conn = DbFactory.getInstance().getConnection();
		}
		return dbManager;
	}
	
	/**
	 * 添加交易记录
	 * @param tradeType 类型,TRADE_TYPE_SAVE, TRADE_TYPE_TAKE, TRADE_TYPE_TRANSFERS 可选
	 * @param tradeMoney 交易金额
	 * @param target 交易对象Id，如果是转账则填id，否则填 TARGET_NULL
	 * @return 添加成功与否
	 */
	public boolean insertTrade(int tradeType, int tradeMoney, int target) {
		String date = DateUtil.getDateTime();
		String sql = "INSERT INTO "+TB_TRADE+"(cardId,tradeDate,tradeType,tradeMoney,target) VALUES( "
				+ getCardId() +",'"+ date+"',"+tradeType+","+tradeMoney+","+target+" )";
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
	
	/**
	 * 查询指定卡号的所有交易记录
	 * @return  ArrayList<TradeInfo> or null
	 */
	public ArrayList<TradeInfo> queryTradeInfos() {
		ArrayList<TradeInfo>list = new ArrayList<TradeInfo>();
		String sql = "SELECT * FROM "+TB_TRADE + " WHERE cardId = "+getCardId();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				TradeInfo tradeInfo = new TradeInfo();
				tradeInfo.setTradeId(rs.getInt("tradeId"));
				tradeInfo.setCardId(rs.getInt("cardId"));
				tradeInfo.setTradeDate(rs.getString("tradeDate").substring(0,16));
				tradeInfo.setTradeType(rs.getInt("tradeType"));
				tradeInfo.setTradeMoney(rs.getInt("tradeMoney"));
				tradeInfo.setTarget(rs.getInt("target"));
				list.add(tradeInfo);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
}
