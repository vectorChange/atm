package com.we.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
import com.we.util.IntegerLimitedKeyListener;
import com.we.util.TextUtil;
import com.we.util.TimerUtil;

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
	private JLabel lb_error;
	
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
		
		ImageIcon icon ;
		icon = new ImageIcon("res\\btn_100.png");
		btn_100 = new JButton(icon);
		btn_100.setBounds(10, 103, icon.getIconWidth(), icon.getIconHeight());
		contentPane.add(btn_100);
		btn_100.setContentAreaFilled(false);
		btn_100.setBorderPainted(false);
		btn_100.addActionListener(this);
		
		icon = new ImageIcon("res\\btn_300.png");
		btn_300 = new JButton(new ImageIcon("res\\btn_300.png"));
		btn_300.setBounds(5, 211, icon.getIconWidth(), icon.getIconHeight());
		btn_300.setContentAreaFilled(false);
		btn_300.setBorderPainted(false);
		contentPane.add(btn_300);
		btn_300.addActionListener(this);
		
		icon = new ImageIcon("res\\btn_500.png");
		btn_500 = new JButton(icon);
		btn_500.setBounds(738, 85, icon.getIconWidth(), icon.getIconHeight());
		btn_500.setContentAreaFilled(false);
		btn_500.setBorderPainted(false);
		contentPane.add(btn_500);
		btn_500.addActionListener(this);
		
		icon = new ImageIcon("res\\btn_1000.png");
		btn_1000 = new JButton(icon);
		btn_1000.setBounds(749, 211,icon.getIconWidth(), icon.getIconHeight());
		contentPane.add(btn_1000);
		btn_1000.setContentAreaFilled(false);
		btn_1000.setBorderPainted(false);
		btn_1000.addActionListener(this);

		btn_sure = new JButton("确定");
		btn_sure.setBounds(749, 350, 93, 23);
		contentPane.add(btn_sure);
		btn_sure.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(10, 350, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		tf_num = new JTextField();
		tf_num.addKeyListener(new IntegerLimitedKeyListener());
		tf_num.setBounds(350, 120, 93, 22);
		contentPane.add(tf_num);
		tf_num.setColumns(10);
		tf_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lb_error.setText("");
				lb_error.setVisible(false);
				// 使错误信息消失
			}
		});
		JLabel label = new JLabel("输入存款额");
		label.setBounds(350, 69, 121, 23);
		contentPane.add(label);
		
		label_1 = new JLabel("仅支持面额为100的纸币");
		label_1.setBounds(330, 206, 171, 79);
		contentPane.add(label_1);
		
		lb_error = new JLabel("错误提示");
		lb_error.setForeground(Color.RED);
		lb_error.setBounds(340, 295, 252, 56);
		lb_error.setVisible(false);
		contentPane.add(lb_error);
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(SystemColor.textHighlight);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(365, 10, 54, 15);
		contentPane.add(lb_rest_time);
		
		TimerUtil.stopTimeCount();
		TimerUtil.timeCount(lb_rest_time,this, UserMain.class);
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
				String verifyRes = TextUtil.verifyTextNum(tf_num.getText());
				if(!verifyRes.equals(TextUtil.TEXT_OK)){
					TextUtil.setErrorTxt(lb_error,verifyRes);
					return;
				}
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
