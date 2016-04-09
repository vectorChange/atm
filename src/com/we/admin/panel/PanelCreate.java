package com.we.admin.panel;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 开户的界面，用户输入设置的密码和自己的身份证号即可。
 * 界面返回，卡号
 * @author 梓扬
 *
 */
@SuppressWarnings("serial")
public class PanelCreate extends JPanel {
	
	public static final String TAG = "create";
	
	private JTextField tf_id;
	private JTextField tf_pwd;
	private JTextField tf_sure_pwd;
	public PanelCreate() {
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(null);
		
		JLabel lb_id = new JLabel("身份证：");
		lb_id.setBounds(170, 106, 54, 15);
		add(lb_id);
		
		JLabel lb_sure_pwd = new JLabel("确认密码：");
		lb_sure_pwd.setBounds(170, 197, 66, 15);
		add(lb_sure_pwd);
		
		JLabel lb_pwd = new JLabel("密码：");
		lb_pwd.setBounds(170, 154, 54, 15);
		add(lb_pwd);
		
		JLabel lb = new JLabel("开户界面");
		lb.setBounds(10, 10, 54, 15);
		add(lb);
		
		tf_id = new JTextField();
		tf_id.setBounds(234, 103, 162, 21);
		add(tf_id);
		tf_id.setColumns(10);
		
		tf_pwd = new JTextField();
		tf_pwd.setBounds(234, 151, 162, 21);
		add(tf_pwd);
		tf_pwd.setColumns(10);
		
		tf_sure_pwd = new JTextField();
		tf_sure_pwd.setBounds(234, 197, 162, 21);
		add(tf_sure_pwd);
		tf_sure_pwd.setColumns(10);
		
		JButton btn_con = new JButton("确认");
		btn_con.setBounds(170, 267, 93, 23);
		add(btn_con);
		
		JButton btn_reset = new JButton("重置");
		btn_reset.setBounds(307, 267, 93, 23);
		add(btn_reset);
	}
}
