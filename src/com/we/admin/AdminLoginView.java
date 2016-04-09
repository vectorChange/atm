package com.we.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.we.dao.AdminManager;

@SuppressWarnings("serial")
public class AdminLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField tf_acc;
	private JPasswordField pf_pwd;
	private JLabel lb_acc;
	private JLabel lb_pwd;
	private JButton btn_login;
	private JButton btn_exit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					AdminLoginView frame = new AdminLoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLoginView() {
		setResizable(false);
		setTitle("管理用户登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponent();
	}

	/*
	 * 初始化组件
	 */
	private void initComponent() {
		lb_acc = new JLabel("帐号：");
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_acc.setBounds(125, 84, 54, 15);

		tf_acc = new JTextField();
		tf_acc.setBounds(189, 77, 177, 30);
		tf_acc.setColumns(10);
		tf_acc.setText("1");

		pf_pwd = new JPasswordField();
		pf_pwd.setText("1");
		pf_pwd.setBounds(189, 140, 177, 30);
		pf_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					doLogin();
				}
			}
		});

		lb_pwd = new JLabel("密码：");
		lb_pwd.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_pwd.setBounds(125, 147, 54, 15);

		btn_login = new JButton("登陆");
		btn_login.setBounds(125, 227, 93, 30);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});

		btn_exit = new JButton("退出");
		btn_exit.setBounds(273, 227, 93, 30);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(lb_acc);
		contentPane.add(tf_acc);
		contentPane.add(pf_pwd);
		contentPane.add(lb_pwd);
		contentPane.add(btn_login);
		contentPane.add(btn_exit);
	}

	private void doLogin() {
		AdminManager admanager = AdminManager.getInstance();
		// 查询数据库是否成功登陆
		boolean loginRes = admanager.queryLogin(tf_acc.getText(), new String(
				pf_pwd.getPassword()));
		
		if (loginRes) {
			System.out.println("登陆成功");
//			UserMain userMain = new UserMain();
//			userMain.setVisible(true);
			new AdminMain().setVisible(true);
			dispose();
		} else {
			System.out.println("登陆失败");
		}
	}
}
