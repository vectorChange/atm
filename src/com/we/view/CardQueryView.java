package com.we.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.util.BackgroundPane;
import com.we.util.MyButton;
import com.we.util.TimerUtil;


public class CardQueryView extends JFrame  implements ActionListener{

	private static final long serialVersionUID = -838514821792658268L;
	private JPanel contentPane;
	private MyButton btn_save;
	private MyButton btn_history;
	private MyButton btn_back;
	private MyButton btn_transfers;
	private MyButton btn_take;
	private JLabel lb_cash;

	private CardManager cardManager = CardManager.getInstance();;
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
		
		double cash = cardManager.queryCash();
		lb_cash.setText(cash+"");
	}

	/**
	 * Create the frame.
	 */
	public CardQueryView() {
		setTitle("查询余额");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		BackgroundPane mainImagePane = new BackgroundPane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		JLabel label = new JLabel("账户余额：");
		label.setForeground(Color.RED);
		label.setBounds(300, 138, 312, 20);
		label.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(label);
		
		lb_cash = new JLabel("9,416.62");
		lb_cash.setBounds(420, 138, 80, 20);
		lb_cash.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_cash);
		
		btn_save = new MyButton("res\\btn_save.png",720,250);
		btn_save.addActionListener(this);
		mainImagePane.add(btn_save);
		
		btn_take = new MyButton("res\\btn_take.png",720,100);
		btn_take.addActionListener(this);
		mainImagePane.add(btn_take);
		
		btn_transfers = new MyButton("res\\btn_transfers.png",10,250);
		btn_transfers.addActionListener(this);
		mainImagePane.add(btn_transfers);
		
        btn_back = new MyButton("res\\btn_back.png",720,400);
        btn_back.addActionListener(this);
		mainImagePane.add(btn_back);
		
		btn_history = new MyButton("res\\btn_detail.png",10,400);
		btn_history.addActionListener(this);
		mainImagePane.add(btn_history);
		
		//冻结的账号限制转出和取款
		if( cardManager.queryCardState(cardManager.getCardNum()).equals(CardManager.CARD_STATE_FROZEN)){
			btn_transfers.setEnabled(false);
			btn_take.setEnabled(false);
		}
		
		TimerUtil.stopTimeCount();
		initData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
		if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else if(btn == btn_save){
            new CardSaveView().setVisible(true);
            dispose();
        }else if(btn == btn_take){
            new CardTakeView().setVisible(true);
            dispose();
        }else if(btn == btn_transfers){
        	new CardTransfersView().setVisible(true);
            dispose();
        }else if(btn == btn_history){
	        new CardDetailsView().setVisible(true);
			dispose();
        }
	}
}
