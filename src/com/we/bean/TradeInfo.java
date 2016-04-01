package com.we.bean;

public class TradeInfo {
	private int tradeId;
	private int cardId;
	private String tradeDate;
	private int tradeType;
	private int tradeMoney;
	private int target;
	
	public TradeInfo(){}
	public TradeInfo(int tradeId, int cardId, String tradeDate, int tradeType,
			int tradeMoney, int target) {
		this.tradeId = tradeId;
		this.cardId = cardId;
		this.tradeDate = tradeDate;
		this.tradeType = tradeType;
		this.tradeMoney = tradeMoney;
		this.target = target;
	}
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public int getTradeType() {
		return tradeType;
	}
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}
	public int getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(int tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
}
