package com.we.admin.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.we.dao.CardManager;
import com.we.dao.UserManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelDel extends JPanel{
	
	public static final String TAG = "delete";
	private CardManager cardManager = CardManager.getInstance();
	private JTextField tf_acc;
	public PanelDel() {
		setLayout(null);
		
		JLabel lb = new JLabel("销户界面");
		lb.setBounds(10, 10, 54, 15);
		add(lb);
		
		tf_acc = new JTextField();
		tf_acc.setBounds(227, 141, 158, 21);
		add(tf_acc);
		tf_acc.setColumns(10);
		
		JLabel lb_acc = new JLabel("卡号：");
		lb_acc.setBounds(163, 144, 54, 15);
		add(lb_acc);
		
		JButton btn_con = new JButton("确认");
		btn_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardManager.closeCard(tf_acc.getText());
			}
		});
		btn_con.setBounds(163, 196, 93, 23);
		add(btn_con);
		
		JButton btn_reset = new JButton("重置");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_acc.setText("");
			}
		});
		btn_reset.setBounds(292, 196, 93, 23);
		add(btn_reset);
	}
	
	
}
