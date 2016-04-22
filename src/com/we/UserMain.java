package com.we;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.dao.CardManager;
import com.we.util.BackgroundPane;
import com.we.util.MyButton;
import com.we.util.TimerUtil;
import com.we.view.CardDetailsView;
import com.we.view.CardQueryView;
import com.we.view.CardSaveView;
import com.we.view.CardTakeView;
import com.we.view.CardTransfersView;

public class UserMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = -87501980138210364L;
	private JPanel contentPane;
	private MyButton btn_query;
	private MyButton btn_save;
	private MyButton btn_take;
	private MyButton btn_transfers;
	private MyButton btn_history;
	private MyButton btn_exit;
	private CardManager cardManager = CardManager.getInstance();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
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
	public UserMain() {
		super("主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		BackgroundPane mainImagePane = new BackgroundPane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		btn_query = new MyButton("res\\btn_query_rest.png",10, 100);
		btn_query.addActionListener(this);
		mainImagePane.add(btn_query);
		
		btn_save = new MyButton("res\\btn_save.png",720,250);
		btn_save.addActionListener(this);
		mainImagePane.add(btn_save);
		
		btn_take = new MyButton("res\\btn_take.png",720,100);
		btn_take.addActionListener(this);
		mainImagePane.add(btn_take);
		
		btn_transfers = new MyButton("res\\btn_transfers.png",720,400);
		btn_transfers.addActionListener(this);
		mainImagePane.add(btn_transfers);
		
		btn_history = new MyButton("res\\btn_history.png",10,250);
		btn_history.addActionListener(this);
		mainImagePane.add(btn_history);
		
		btn_exit = new MyButton("res\\btn_exit.png",10,400);
		btn_exit.addActionListener(this);
		mainImagePane.add(btn_exit);
		
		//冻结的账号限制转出和取款
		if( cardManager.queryCardState(cardManager.getCardNum()).equals(CardManager.CARD_STATE_FROZEN)){
			btn_transfers.setEnabled(false);
			btn_take.setEnabled(false);
		}
		TimerUtil.stopTimeCount();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
		if( btn == btn_query){
			new CardQueryView().setVisible(true);
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
		}else if(btn == btn_exit){
			dispose();
		}else if(btn == btn_history){
			new CardDetailsView().setVisible(true);
			dispose();
		}
	}
}
