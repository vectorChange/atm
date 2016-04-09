package com.we.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
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

@SuppressWarnings("serial")
public class UserLoginFrame extends JFrame {

	protected static final int PASSWORD_LENGTH = 6;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField tf_pwd;

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
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("卡号：");
		lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 14));
		lblNewLabel.setBounds(280, 146, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(344, 139, 177, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("1001");
		
		tf_pwd = new JPasswordField();
//		tf_pwd.addKeyListener(new KeyAdapter() {
		tf_pwd.addKeyListener(new LoginPasswordKeyListener());
		tf_pwd.setText("1");
		tf_pwd.setBounds(344, 202, 177, 30);
		contentPane.add(tf_pwd);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setFont(new Font("幼圆", Font.BOLD, 14));
		lblNewLabel_1.setBounds(280, 209, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		btnNewButton.setBounds(280, 289, 93, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(428, 289, 93, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(SystemColor.textHighlight);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(379, 12, 54, 15);
		contentPane.add(lb_rest_time);
		lb_rest_time.setVisible(false);
		
	}
	
	private void doLogin() {
		CardManager dbManager = CardManager.getInstance();
		String state = dbManager.queryCardState(textField.getText());
		if(!state.equals(CardManager.CARD_STATE_NORMAL)){
			JOptionPane.showMessageDialog(null, "该银行卡已"+state, "银行卡异常", JOptionPane.ERROR_MESSAGE); 
		}else{
			boolean loginRes = dbManager.queryLogin(textField.getText(),new String(tf_pwd.getPassword()));
			if(loginRes){
				new UserMain().setVisible(true);
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "卡号不存在或密码不正确", "登陆失败", JOptionPane.ERROR_MESSAGE); 
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
