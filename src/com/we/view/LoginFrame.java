package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.we.admin.AdminLoginView;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {

	private MyPanel contentPane;
	private JButton btnManager;
	private JButton btnNormal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setTitle("身份选择");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 410, 270);
		contentPane = new MyPanel(new File(".\\res\\login_bg.jpg"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponent();
	}

	// 初始化组件
	private void initComponent() {
		btnManager = new JButton("管理员");
		btnNormal = new JButton("普通账号");
		btnNormal.setBounds(144, 55, 115, 37);
		contentPane.add(btnNormal);
		btnManager.setBounds(144, 147, 115, 37);
		contentPane.add(btnManager);
		btnManager.addActionListener(this);
		btnNormal.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton) e.getSource();
		if (tmp == btnNormal) {
			new UserLoginFrame().setVisible(true);
		}else if (tmp == btnManager){
			new AdminLoginView().setVisible(true);
		}
		dispose();
	}

}
