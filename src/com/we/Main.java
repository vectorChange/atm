package com.we;

import com.we.controller.impl.CardController;
import com.we.model.CardModel;
import com.we.view.CardQueryView;

public class Main {
	public static void main(String[] args) {
		CardModel cardModel = new CardModel();
		CardController cardController = new CardController();
//		CardLoginView cardView = new CardLoginView();
//		cardController.addView(cardView);
//		cardView.setVisible(true);
		cardController.addModel(cardModel);

		CardQueryView cardQueryView = new CardQueryView();
		cardController.addView(cardQueryView);
		cardQueryView.initData();
		cardQueryView.setVisible(true);
		
		try {
//			Thread.sleep(500);
//			new Test(cardController );
			
//			cardModel.initDefaults();
//			System.out.println(1.0 Model->View: [Main] 直接调用model层设置值);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
class Test{
	Test(CardController cardController ){
		System.out.println("1.0 Model->View: [Main] 调用cardController去设置model值");
		cardController.setCardAccount(123);
	}
}
