package com.we.admin.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

@SuppressWarnings("serial")
public class PanelRec extends JPanel {
	
	public static final String TAG = "rec";
	
	private JTextField tf_acc;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelRec() {
		setLayout(null);
		
		JLabel lb_acc = new JLabel("卡号：");
		lb_acc.setBounds(10, 30, 46, 15);
		add(lb_acc);
		
		tf_acc = new JTextField();
		tf_acc.setBounds(46, 27, 129, 21);
		add(tf_acc);
		tf_acc.setColumns(10);
		
		JXDatePicker dp_start = new JXDatePicker();
		dp_start.setBounds(239, 26, 129, 23);
		dp_start.setFormats("yyyy.MM.dd");
		add(dp_start);
		
		JLabel lb_start = new JLabel("开始：");
		lb_start.setBounds(200, 30, 54, 15);
		add(lb_start);
		
		JLabel lb_end = new JLabel("结束：");
		lb_end.setBounds(378, 30, 54, 15);
		add(lb_end);
		
		JXDatePicker dp_end = new JXDatePicker();
		dp_end.setFormats("yyyy.MM.dd");
		dp_end.setBounds(421, 26, 129, 23);

		add(dp_end);
		
		JScrollPane scroll_panel = new JScrollPane();
		scroll_panel.setBounds(10, 69, 540, 273);
		add(scroll_panel);
		
		table = new JTable();
		scroll_panel.setViewportView(table);
		
		JButton btn_query = new JButton("查询");
		btn_query.setBounds(457, 355, 93, 23);
		add(btn_query);

	}
}
