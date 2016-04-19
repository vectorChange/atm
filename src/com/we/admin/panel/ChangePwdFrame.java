package com.we.admin.panel;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class ChangePwdFrame extends JFrame {
	private JTextField tf_acc;
	private JPasswordField pf;
	private JLabel lb_acc;
	private JLabel lb_pwd;
	private JButton btn_con;
	private JButton btn_reset;

	public ChangePwdFrame() {
		setTitle("修改密码");
		getContentPane().setLayout(null);

		lb_acc = new JLabel("卡号");
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_acc.setBounds(106, 68, 54, 15);
		getContentPane().add(lb_acc);

		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.setBounds(156, 60, 170, 30);
		getContentPane().add(tf_acc);
		tf_acc.setColumns(10);

		lb_pwd = new JLabel("密码");
		lb_pwd.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_pwd.setBounds(106, 131, 54, 15);
		getContentPane().add(lb_pwd);

		btn_con = new JButton("确认");
		btn_con.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btn_con.setBounds(106, 189, 90, 30);
		getContentPane().add(btn_con);

		btn_reset = new JButton("重置");
		btn_reset.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btn_reset.setBounds(236, 189, 90, 30);
		getContentPane().add(btn_reset);

		pf = new JPasswordField();
		pf.setFont(new Font("宋体", Font.BOLD, 18));
		pf.setBounds(156, 123, 170, 30);
		getContentPane().add(pf);
	}
}
