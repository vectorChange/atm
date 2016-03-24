package com.we.controller.impl;

import com.we.controller.AbstractController;
import com.we.controller.CardControllerFace;

public class CardController extends AbstractController implements CardControllerFace{

	@Override
	public void setCardAccount(Integer newNumber) {
		// TODO Auto-generated method stub
		System.out.println("CardController:view层通过该control层设置model层的cardId值("+newNumber+")，该control调用抽象父类Controller的某方法（反射）设置值");
		setModelProperty(PROPERTY_CARDID, newNumber);
	}
    
}
