package com.we.view;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.controller.AbstractController;
import com.we.controller.CardControllerFace;
import com.we.controller.impl.CardController;

public class CardQueryView extends JFrame implements AbstractView{

	private JPanel contentPane;
	private JTextField textField;
	private CardController cardController;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardQueryView frame = new CardQueryView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CardQueryView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(143, 62, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
	}

	public void initData() {
		textField.setText(""+cardController.getCardCash());
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
//		if (evt.getPropertyName().equals(CardControllerFace.PROPERTY_CASH)){
//			String newVal = evt.getNewValue().toString();
//			if(!newVal.equals(textField.getText())){
//				textField.setText(newVal);
//			}
//		}
	}

	@Override
	public void setController(AbstractController controller) {
		cardController = (CardController) controller;				
	}
}
