package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnManager;
	private JButton btnNormal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setResizable(false);
		setTitle("身份选择");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponent();
	}

	// 初始化组件
	private void initComponent() {
		btnManager = new JButton("管理员");
		btnNormal = new JButton("普通账号");
		btnNormal.setBounds(164, 83, 115, 37);
		contentPane.add(btnNormal);
		btnManager.setBounds(164, 167, 115, 37);
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
			// TODO
		}
		dispose();
	}

}
