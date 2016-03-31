package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.dao.TradeManager;

public class CardTransfersView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3477820948172292970L;
	private JPanel contentPane;
	private JTextField tf_cardNum;
	private JButton btn_back;
	private JButton btn_sure;
	CardManager cardManager = CardManager.getInstance();
	TradeManager tradeManager = TradeManager.getInstance();
	private JButton btn_clear;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardTransfersView frame = new CardTransfersView();
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
	public CardTransfersView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入转入卡号");
		lblNewLabel.setBounds(310, 100, 144, 15);
		contentPane.add(lblNewLabel);
		
		tf_cardNum = new JTextField();
		tf_cardNum.setText("1002");
		tf_cardNum.setBounds(310, 157, 144, 21);
		contentPane.add(tf_cardNum);
		tf_cardNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("小心诈骗，客服电话66666");
		lblNewLabel_1.setBounds(310, 306, 163, 73);
		contentPane.add(lblNewLabel_1);
		
		btn_back = new JButton("返回主菜单");
		btn_back.addActionListener(this);
		btn_back.setBounds(33, 492, 117, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);

		
		btn_sure = new JButton("确定");
		btn_sure.setBounds(725, 492, 93, 23);
		contentPane.add(btn_sure);
		btn_sure.addActionListener(this);
		
		btn_clear = new JButton("更正");
		btn_clear.setBounds(725, 386, 93, 23);
		contentPane.add(btn_clear);
		btn_clear.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else if(btn==btn_sure){
			String targetCardNum = tf_cardNum.getText();
			if(cardManager.getCardNum().equals(targetCardNum)){
				System.out.println("不能给自己转账");
				return;
			}
			int transCash = 1;
			if(cardManager.transfersCash(targetCardNum,transCash)){
				int targetCardId = cardManager.getCardIdByCardNum(targetCardNum);
				if(tradeManager.insertTrade(TradeManager.TRADE_TYPE_TRANSFERS_OUT, transCash, targetCardId)){
					System.out.println("插入转账记录成功");
				}else{
					System.out.println("插入转账记录失败");
				}
				System.out.println("转账成功");
			}else{
				System.out.println("转账失败");
			}
		}else if(btn==btn_clear){
			tf_cardNum.setText("");
		}
	}

}
