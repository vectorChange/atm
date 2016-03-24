package com.we;

import com.we.controller.impl.CardController;
import com.we.model.CardModel;
import com.we.view.CardLoginView;

public class Main {
	public static void main(String[] args) {
		CardModel cardModel = new CardModel();
		CardController cardController = new CardController();
		CardLoginView cardView = new CardLoginView();

		cardController.addView(cardView);
		cardController.addModel(cardModel);
		cardView.setVisible(true);

		try {
			Thread.sleep(500);
			cardModel.initDefaults();
//			System.out.println(cardModel+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
