package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.dao.TradeManager;

public class CardSaveView extends JFrame implements ActionListener{

	private static final long serialVersionUID = -4046477038390427048L;
	private JPanel contentPane;
	private JTextField tf_num;
	private JButton btn_100;
	private JButton btn_300;
	private JButton btn_500; 
	private JButton btn_1000;
	TradeManager tradeManager = TradeManager.getInstance();	
	CardManager dbManager = CardManager.getInstance();
	private JButton btn_back;
	private JButton btn_sure;
	private JLabel label_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardSaveView frame = new CardSaveView();
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
	public CardSaveView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_100 = new JButton("100");
		btn_100.setBounds(10, 98, 93, 23);
		contentPane.add(btn_100);
		btn_100.addActionListener(this);
		

		btn_300 = new JButton(new ImageIcon("res\\btn_300.png"));
		btn_300.setBounds(20, 211, 104, 36);
		btn_300.setContentAreaFilled(false);
		btn_300.setBorderPainted(false);
		contentPane.add(btn_300);
		btn_300.addActionListener(this);
		
		
		btn_500 = new JButton(new ImageIcon("res\\btn_500.png"));
		btn_500.setBounds(738, 85, 104, 36);
		btn_500.setContentAreaFilled(false);
		btn_500.setBorderPainted(false);
		contentPane.add(btn_500);
		btn_500.addActionListener(this);
		
		
//		JButton button = new JButton(new ImageIcon("res\\btn_500.png"));
//		button.setBounds(10, 210, 127, 41);
//		button.setContentAreaFilled(false);
//		button.setBorderPainted(false);
//		contentPane.add(button);
		
		btn_1000 = new JButton("1000");
		btn_1000.setBounds(749, 211, 93, 23);
		contentPane.add(btn_1000);
		btn_1000.addActionListener(this);

		btn_sure = new JButton("确定");
		btn_sure.setBounds(749, 356, 93, 23);
		contentPane.add(btn_sure);
		btn_sure.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(10, 356, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		tf_num = new JTextField();
		tf_num.setBounds(330, 99, 93, 22);
		contentPane.add(tf_num);
		tf_num.setColumns(10);
		tf_num.setText("123");
		
		JLabel label = new JLabel("输入存款额");
		label.setBounds(330, 61, 121, 23);
		contentPane.add(label);
		
		label_1 = new JLabel("仅支持面额为100的纸币");
		label_1.setBounds(304, 183, 171, 79);
		contentPane.add(label_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int addNum = 0;
		JButton btn = (JButton)e.getSource();
		if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else{
			if(btn == btn_300){
				addNum = 300;
			}else if(btn == btn_500){
				addNum = 500;
			}else if(btn == btn_1000){
				addNum = 1000;
			}else if(btn == btn_100){
				addNum = 100;
			}else if(btn == btn_sure){
			    addNum = Integer.parseInt(tf_num.getText());
			}
			if(dbManager.saveCash(addNum)){
				if(tradeManager.insertTrade(TradeManager.TRADE_TYPE_SAVE, addNum, TradeManager.TARGET_NULL)){
					new CardBusinessDone(getClass(), addNum).setVisible(true);
					dispose();
				}else{
			        System.err.println("存款失败");
			    }
			}else{
				System.err.println("存款失败");
			}
        }
	}
}
