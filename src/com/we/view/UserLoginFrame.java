package com.we.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.util.IntegerLimitedKeyListener;
import com.we.util.BackgroundPane;
import com.we.util.MyButton;
import com.we.util.TimerUtil;

@SuppressWarnings("serial")
public class UserLoginFrame extends JFrame {

	protected static final int PASSWORD_LENGTH = 6;
	private static final int FAIL_LOGIN_COUNT = 3;
	private JPanel contentPane;
	private JTextField tf_acc;
	private JPasswordField tf_pwd;
	private int failLoginCnt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginFrame frame = new UserLoginFrame();
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
	public UserLoginFrame() {
		setResizable(false);
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		BackgroundPane mainImagePane = new BackgroundPane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("卡号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN,22));
		lblNewLabel.setBounds(250, 146, 100, 20);
		mainImagePane.add(lblNewLabel);
		
		tf_acc = new JTextField();
		tf_acc.setBorder(null);
		tf_acc.setBounds(344, 139, 177, 30);
		mainImagePane.add(tf_acc);
		tf_acc.setColumns(10);
		tf_acc.setFont(new Font("宋体", Font.PLAIN,22));
		
		tf_pwd = new JPasswordField();
		tf_pwd.setBorder(null);
		tf_pwd.addKeyListener(new LoginPasswordKeyListener());
		tf_pwd.setBounds(344, 202, 177, 30);
		mainImagePane.add(tf_pwd);
		tf_pwd.setFont(new Font("宋体", Font.PLAIN,22));
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(250, 209, 100, 20);
		mainImagePane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN,22));
		
		MyButton btn_login = new MyButton("res\\btn_login.png",280,289);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		mainImagePane.add(btn_login);
		
		MyButton btn_exit = new MyButton("res\\btn_exit.png",428,289);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mainImagePane.add(btn_exit);
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(Color.RED);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(395, 10, 54, 40);
		lb_rest_time.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_rest_time);
		
		JLabel lblNewLabel_2 = new JLabel("客服电话：95566");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_2.setBounds(695, 11, 160, 40);
		mainImagePane.add(lblNewLabel_2);
		lb_rest_time.setVisible(false);
		
		//计时
		TimerUtil.stopTimeCount();
//		TimerUtil.timeCount(lb_rest_time,this, UserMain.class);
		failLoginCnt = 0;
		
	}
	
	private void doLogin() {
		String pwd = new String(tf_pwd.getPassword());
		if(tf_acc.getText().equals("")|| pwd.equals("")){
			JOptionPane.showMessageDialog(null, "卡号或密码为空", "登陆异常",JOptionPane.ERROR_MESSAGE); 
			return;
		}
		CardManager cardManager = CardManager.getInstance();
		String state = cardManager.queryCardState(tf_acc.getText());
		if(!state.equals(CardManager.CARD_STATE_NORMAL)&&!state.equals(CardManager.CARD_STATE_FROZEN)){
			JOptionPane.showMessageDialog(null, "该银行卡已"+state, "银行卡异常", JOptionPane.ERROR_MESSAGE); 
		}else{
			boolean loginRes = cardManager.queryLogin(tf_acc.getText(),pwd);
			if(loginRes){
				new UserMain().setVisible(true);
				dispose();
			}else{
				failLoginCnt ++;
				if(failLoginCnt == FAIL_LOGIN_COUNT){
					JOptionPane.showMessageDialog(null, "已三次登陆失败，银行卡被冻结", "冻结卡号", JOptionPane.ERROR_MESSAGE); 
					cardManager.frozenCard(cardManager.getCardNum(),CardManager.CARD_FLAG_FROZEN);
				}else{
					JOptionPane.showMessageDialog(null, "卡号不存在或密码不正确", "登陆失败", JOptionPane.ERROR_MESSAGE); 
				}
			}
		}
	}
	
	
	
	private class LoginPasswordKeyListener extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
			char key = e.getKeyChar();
			boolean yes = false;
			for (int i = 0; i < IntegerLimitedKeyListener.canKey.length(); i++) {
				if( key == IntegerLimitedKeyListener.canKey.charAt(i)){
					yes = true;
					break;
				}
			}
			if(!yes){
				e.consume();
			}else{	//到指定长度登陆
				int len = new String(tf_pwd.getPassword()).length()+1;
				if(len == PASSWORD_LENGTH){
					doLogin();
				}
			}
			super.keyTyped(e);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() ==  KeyEvent.VK_ENTER){
				doLogin();
				return;
			}
			super.keyPressed(e);
		}
	}
}
