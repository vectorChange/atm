package com.we.model;

import java.util.Date;

import com.we.controller.CardControllerFace;

public class CardModel extends AbstractModel {
	
	private Integer cardId;
	private Integer userId;
	private String password;
	private Boolean frozen;
	private Boolean loss;
	private Boolean closed;
	private Integer cash;
	private Date openDate;
	private String cardNum;
	
	public void initDefaults() {
		setCardId(123);
	}
	
	public Integer getCardId() {
		return cardId;
	}
	
	public void setCardId(Integer cardId) {
		Integer oldCardId = this.cardId;
		this.cardId = cardId;
		firePropertyChange(CardControllerFace.PROPERTY_CARDID,oldCardId,cardId);
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isFrozen() {
		return frozen;
	}
	
	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}
	
	public Boolean isLoss() {
		return loss;
	}
	
	public void setLoss(Boolean loss) {
		this.loss = loss;
	}
	
	public Boolean isClosed() {
		return closed;
	}
	
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	public Integer getCash() {
		return cash;
	}
	
	public void setCash(Integer cash) {
		this.cash = cash;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	@Override
	public String toString() {
		return "CardModel [cardId=" + cardId + ", userId=" + userId
				+ ", password=" + password + ", frozen=" + frozen + ", loss="
				+ loss + ", closed=" + closed + ", cash=" + cash
				+ ", openDate=" + openDate + ", cardNum=" + cardNum + "]";
	}
}
