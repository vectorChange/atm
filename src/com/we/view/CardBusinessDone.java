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
import com.we.util.BackgroundPane;
import com.we.util.MyButton;
import com.we.util.TimerUtil;


public class CardBusinessDone extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8878416190270664711L;
	private JPanel contentPane;
	private MyButton btn_showCash;
	private MyButton btn_print;
	private MyButton btn_continue;
	private MyButton btn_back;
	private MyButton btn_exit;
	private JLabel label;
	private JLabel label_1;
	private JLabel lb_money;
	private Class<?extends JFrame> preClass = null;
	private double successMoney = 0.0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardBusinessDone frame = new CardBusinessDone(null,0);
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
	public CardBusinessDone(Class<?extends JFrame> preClass, double money) {
		super("交易成功");
		this.preClass = preClass;
		this.successMoney = money;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		BackgroundPane mainImagePane = new BackgroundPane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		btn_continue = new MyButton("res\\btn_continue.png",10, 250);
		btn_continue.addActionListener(this);
		mainImagePane.add(btn_continue);
		
		btn_showCash = new MyButton("res\\btn_query_rest.png",727, 250);
		btn_showCash.addActionListener(this);
		mainImagePane.add(btn_showCash);
		
		btn_print = new MyButton("打印凭条");
		btn_print.setBounds(753, 145, 93, 23);
		mainImagePane.add(btn_print);
		btn_print.setVisible(false);
		btn_print.addActionListener(this);
		
        btn_back = new MyButton("res\\btn_back.png",727,400);
        btn_back.addActionListener(this);
		mainImagePane.add(btn_back);
		
		btn_exit = new MyButton("res\\btn_exit.png",10,400);
        btn_exit.addActionListener(this);
		mainImagePane.add(btn_exit);
		
		label = new JLabel("交易成功");
		label.setBounds(350, 98, 100, 20);
		mainImagePane.add(label);
		label.setFont(new Font("宋体", Font.PLAIN,22));
		
		label_1 = new JLabel("交易金额：");
		label_1.setBounds(300, 145, 150, 20);
		label_1.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(label_1);
		
		lb_money = new JLabel(successMoney+"");
		lb_money.setBounds(420, 149, 150, 15);
		mainImagePane.add(lb_money);
		lb_money.setFont(new Font("宋体", Font.PLAIN,22));
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(Color.RED);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(395, 10, 54, 40);
		lb_rest_time.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_rest_time);
		
		
		TimerUtil.stopTimeCount();
		TimerUtil.timeCount(lb_rest_time,this, UserMain.class);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
		if(btn == btn_exit){
			dispose();
		}else if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else if(btn == btn_continue){
			try {
				preClass.newInstance().setVisible(true);
				dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(btn == btn_showCash){
			new CardQueryView().setVisible(true);
			dispose();
		}else if(btn == btn_print){
		}
	}

}
