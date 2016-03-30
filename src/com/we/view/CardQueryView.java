package com.we.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;


public class CardQueryView extends JFrame  implements ActionListener{

	private static final long serialVersionUID = -838514821792658268L;
	private JPanel contentPane;
	private JButton btn_save;
	private JButton button_3;
	private JButton button_4;
	private JButton btn_back;
	private JButton btn_transfers;
	private JButton btn_take;
	private JLabel lb_cash;

	private CardManager cardManager;
	private JLabel lb_preCash;
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

	private void initData() {
		cardManager = CardManager.getInstance();
		int cash = cardManager.queryCash();
		lb_cash.setText(""+cash);
		lb_preCash.setText(""+cash);
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
		
		JLabel lblNewLabel = new JLabel("您的账户余额信息");
		lblNewLabel.setBounds(143, 38, 125, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("账户余额：");
		label.setForeground(Color.RED);
		label.setBounds(98, 63, 72, 15);
		contentPane.add(label);
		
		lb_cash = new JLabel("9,416.62");
		lb_cash.setBounds(180, 63, 54, 15);
		contentPane.add(lb_cash);
		
		JLabel label_1 = new JLabel("可用余额：");
		label_1.setForeground(Color.RED);
		label_1.setBounds(98, 88, 72, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("可取现余额：");
		label_2.setForeground(Color.RED);
		label_2.setBounds(98, 113, 72, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("最小还款额：");
		label_3.setForeground(Color.RED);
		label_3.setBounds(98, 138, 72, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("39,281.62");
		label_4.setBounds(180, 88, 54, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("21,416.62");
		label_5.setBounds(180, 113, 72, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("0.00");
		label_6.setBounds(190, 138, 54, 15);
		contentPane.add(label_6);
		
		btn_save = new JButton("存款");
		btn_save.setBounds(331, 61, 93, 23);
		contentPane.add(btn_save);
		btn_save.addActionListener(this);
		
		btn_take = new JButton("取款");
		btn_take.setBounds(331, 161, 93, 23);
		contentPane.add(btn_take);
		btn_take.addActionListener(this);

		btn_transfers = new JButton("转账");
		btn_transfers.setBounds(331, 107, 93, 23);
		contentPane.add(btn_transfers);
		btn_transfers.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(331, 210, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		button_3 = new JButton("企业年金");
		button_3.setBounds(10, 186, 93, 23);
		contentPane.add(button_3);
		button_3.addActionListener(this);
		
		button_4 = new JButton("交易明细");
		button_4.setBounds(10, 228, 93, 23);
		contentPane.add(button_4);
		button_4.addActionListener(this);
		
		JLabel label_7 = new JLabel("上期结欠金额：");
		label_7.setForeground(Color.RED);
		label_7.setBounds(98, 163, 84, 15);
		contentPane.add(label_7);
		
		lb_preCash = new JLabel("9,416.62");
		lb_preCash.setBounds(180, 165, 54, 15);
		contentPane.add(lb_preCash);
		
		
		initData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn_back){
			UserMain userMain = new UserMain();
			userMain.setVisible(true);
			dispose();
		}else if(e.getSource() == btn_save){
			CardSaveView cardSaveView = new CardSaveView();
			cardSaveView.setVisible(true);
			dispose();
		}else if(e.getSource() == btn_take){
			CardTakeView cardTakeView = new CardTakeView();
			cardTakeView.setVisible(true);
			dispose();
		}else if(e.getSource() == btn_transfers){
			CardTransfersView cardTransfersView = new CardTransfersView();
			cardTransfersView.setVisible(true);
			dispose();
		}
	}
}
