package com.we.dao;

import java.sql.Connection;

/**
 * Card表和User表合并查询等 两表沟通渠道
 * @author xiaoguang
 *
 */
public class CardUserManager {
	private static UserManager userManager;
	private static CardManager cardManager;
	private static CardUserManager cardUserManager;
	public static String CARDUSER_ERROR = null;
	
	private CardUserManager() {
	}

	public static CardUserManager getInstance() {
		if (cardUserManager == null) {
			cardUserManager = new CardUserManager();
			cardManager = CardManager.getInstance();
			userManager = UserManager.getInstance();
		}
		return cardUserManager;
	}
	public String getUserNameByCardId(int cardId) {
		int userId = cardManager.getUserIdByCardId(cardId);
		if(userId == CardManager.NO_EXIT){
			return CARDUSER_ERROR;
		}else{
			return userManager.getUserNameByUserId(userId);
		}
	}
	public String getUserNameByCardNum(String cardNum) {
		int userId = cardManager.getUserIdByCardNum(cardNum);
		if(userId == CardManager.NO_EXIT){
			return CARDUSER_ERROR;
		}else{
			return userManager.getUserNameByUserId(userId);
		}
	}
}
