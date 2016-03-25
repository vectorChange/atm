package com.we.controller.impl;

import java.beans.PropertyChangeEvent;

import com.we.controller.AbstractController;
import com.we.controller.CardControllerFace;
import com.we.view.AbstractView;

public class CardController extends AbstractController implements CardControllerFace{

	@Override
	public void setCardAccount(Integer newNumber) {
		// TODO Auto-generated method stub
		System.out.println("1.1 Model->View: [CardController]:调用父类的方法用反射找到对应model的set方法");
		
		System.out.println("2.2 View->Model: [CardController]:view层通过该control层设置model层的cardId值("+newNumber+")，该control调用抽象父类Controller的某方法（反射）设置值");
		setModelProperty(PROPERTY_CARDID, newNumber);
	}
    
	public Integer getCardCash() {
		return 3;
	}
}
