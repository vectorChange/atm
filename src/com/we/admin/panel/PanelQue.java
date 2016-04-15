package com.we.admin.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelQue extends JPanel {

	public static final String TAG = "query";
	
	private JTextField tf_id;
	private JTable table;

	public PanelQue() {
		setLayout(null);

		JLabel lb_id = new JLabel("身份证号：");
		lb_id.setBounds(10, 20, 82, 15);
		add(lb_id);

		tf_id = new JTextField();
		tf_id.setBounds(75, 16, 180, 23);
		add(tf_id);
		tf_id.setColumns(10);

		JButton btn_query = new JButton("查询");
		btn_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 查询用户信息，并且返回用户持有的卡号的现状
				
			}
		});
		btn_query.setBounds(467, 16, 93, 23);
		add(btn_query);

		JScrollPane scroll_panel = new JScrollPane();
		scroll_panel.setBounds(10, 50, 550, 280);
		add(scroll_panel);

		table = new JTable();
		scroll_panel.setViewportView(table);
	}
}
