package com.we.view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.controller.AbstractController;
import com.we.controller.CardControllerFace;
import com.we.controller.impl.CardController;

public class CardLoginView extends JFrame implements AbstractView{
	private JPanel contentPane;
	private JTextField tf_acc;
	private JLabel label_1;
	private JTextField tf_pwd;
	private JButton btn_login;
	private JButton btn_exit;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private static CardLoginView cardLoginView;
	
	private CardController cardController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cardLoginView = new CardLoginView();
					cardLoginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CardLoginView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 2));

		panel = new JPanel();
		contentPane.add(panel);

		JLabel label = new JLabel("账号");
		panel.add(label);

		tf_acc = new JTextField();
		panel.add(tf_acc);
		tf_acc.setColumns(20);

		panel_1 = new JPanel();
		contentPane.add(panel_1);
		label_1 = new JLabel("密码");
		panel_1.add(label_1);
		tf_pwd = new JTextField();
		tf_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() ==  KeyEvent.VK_ENTER){
				}
			}
		});
		panel_1.add(tf_pwd);
		tf_pwd.setColumns(20);

		panel_2 = new JPanel();
		contentPane.add(panel_2);

		btn_login = new JButton("登陆");
		btn_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(cardController==null){
					System.out.println("空cardController");
				}else{
					System.out.println("2.1 View->Model: [CardLoginView]点击登陆，由cardController去设置Model");
					cardController.setCardAccount(23333);
				}
			}
		});
		panel_2.add(btn_login);

		btn_exit = new JButton("退出");
		btn_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				cardLoginView.dispose();//想多了 退出得换个方法了，不能对当前对象dispose 否则在哪里new?
			}
		});
		panel_2.add(btn_exit);
		
	}

	/**
	 * model层改变时 经过Controll调用其父类的setModelProperty的反射调用model的set方法，
	 * 再由model的set方法调用父类model的firePropertyChange()来起通知，该方法通知到这个view
	 */
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equals(CardControllerFace.PROPERTY_CARDID)){
			String newVal = evt.getNewValue().toString();
			if(!newVal.equals(tf_acc.getText())){
				tf_acc.setText(newVal);
				System.out.println("1.3 Model->View: [CardLoginView]view接收到model发来的通知，新值为"+evt.getNewValue().toString());
				
				System.out.println("2.4 View->Model->View【if优化后不发生】: [CardLoginView]model值发起的通知被View接收");
			}
		}
		
	}

	/**
	 * 由AbstractController的addView调用，一般不需要手工调用
	 */
	@Override
	public void setController(AbstractController controller) {
		cardController = (CardController) controller;		
	}

}
