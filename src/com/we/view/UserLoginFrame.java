package com.we.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;

@SuppressWarnings("serial")
public class UserLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

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
		setTitle("普通用户登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("密码：");
		lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 84, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(189, 77, 177, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(189, 140, 177, 30);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("账号：");
		lblNewLabel_1.setFont(new Font("幼圆", Font.BOLD, 14));
		lblNewLabel_1.setBounds(125, 147, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.setBounds(125, 227, 93, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setBounds(273, 227, 93, 30);
		contentPane.add(btnNewButton_1);
	}

}
