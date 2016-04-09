package com.we.admin.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelDel extends JPanel{
	
	public static final String TAG = "delete";
	
	private JTextField tf_acc;
	private JTextField tf_id;
	public PanelDel() {
		setLayout(null);
		
		JLabel lb = new JLabel("销户界面");
		lb.setBounds(10, 10, 54, 15);
		add(lb);
		
		tf_acc = new JTextField();
		tf_acc.setBounds(227, 131, 158, 21);
		add(tf_acc);
		tf_acc.setColumns(10);
		
		JLabel lb_acc = new JLabel("卡号：");
		lb_acc.setBounds(163, 134, 54, 15);
		add(lb_acc);
		
		JLabel lb_id = new JLabel("身份证号：");
		lb_id.setBounds(163, 172, 69, 15);
		add(lb_id);
		
		tf_id = new JTextField();
		tf_id.setBounds(227, 169, 158, 21);
		add(tf_id);
		tf_id.setColumns(10);
		
		JButton btn_con = new JButton("确认");
		btn_con.setBounds(163, 228, 93, 23);
		add(btn_con);
		
		JButton btn_reset = new JButton("重置");
		btn_reset.setBounds(292, 228, 93, 23);
		add(btn_reset);
	}
	
	
}
