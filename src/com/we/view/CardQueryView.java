package com.we.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.util.MainImagePane;
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
		
		double cash = cardManager.queryCash();
		lb_cash.setText(cash+"");
//		lb_preCash.setText(cash+"");
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
		MainImagePane mainImagePane = new MainImagePane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		JLabel label = new JLabel("账户余额：");
		label.setForeground(Color.RED);
		label.setBounds(300, 138, 200, 20);
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
		
//		JLabel label_1 = new JLabel("可用余额：");
//		label_1.setForeground(Color.RED);
//		label_1.setBounds(347, 175, 72, 15);
//		contentPane.add(label_1);
//		
//		JLabel label_2 = new JLabel("可取现余额：");
//		label_2.setForeground(Color.RED);
//		label_2.setBounds(347, 216, 103, 15);
//		contentPane.add(label_2);
//		
//		JLabel label_3 = new JLabel("最小还款额：");
//		label_3.setForeground(Color.RED);
//		label_3.setBounds(347, 257, 103, 15);
//		contentPane.add(label_3);
		
//		JLabel label_4 = new JLabel("0");
//		label_4.setBounds(456, 175, 84, 15);
//		contentPane.add(label_4);
//		
//		JLabel label_5 = new JLabel("0");
//		label_5.setBounds(456, 216, 84, 15);
//		contentPane.add(label_5);
//		
//		JLabel label_6 = new JLabel("0.00");
//		label_6.setBounds(453, 257, 54, 15);
//		contentPane.add(label_6);

//		JLabel label_7 = new JLabel("上期结欠金额：");
//		label_7.setForeground(Color.RED);
//		label_7.setBounds(347, 296, 103, 15);
//		contentPane.add(label_7);
//		lb_preCash = new JLabel("9,416.62");
//		lb_preCash.setBounds(453, 296, 70, 15);
//		contentPane.add(lb_preCash);
		
		
		TimerUtil.stopTimeCount();
		initData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
		// TODO Auto-generated method stub
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
