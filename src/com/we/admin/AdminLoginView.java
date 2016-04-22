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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
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
					UIManager
							.setLookAndFeel(new HiFiLookAndFeel());
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
		setBounds(100, 100, 680, 470);
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
		lb_acc = new JLabel("帐号");
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_acc.setBounds(207, 136, 54, 15);

		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.setBounds(260, 128, 209, 30);
		tf_acc.setColumns(10);

		pf_pwd = new JPasswordField();
		pf_pwd.setFont(new Font("宋体", Font.PLAIN, 18));
		pf_pwd.setBounds(260, 191, 209, 30);
		pf_pwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					doLogin();
				}
			}
		});

		lb_pwd = new JLabel("密码");
		lb_pwd.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_pwd.setBounds(207, 199, 54, 15);

		btn_login = new JButton("登陆");
		btn_login.setBounds(207, 265, 90, 30);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});

		btn_exit = new JButton("退出");
		btn_exit.setBounds(376, 265, 90, 30);
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
			new AdminMain(admanager.getAdminType()).setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(AdminLoginView.this, "登陆失败");
		}
	}
}
