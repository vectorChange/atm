package com.we.admin.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.we.dao.CardUserManager;
import com.we.dao.UserManager;
import com.we.util.IntegerLimitedKeyListener;

@SuppressWarnings("serial")
public class PanelQue extends JPanel implements ActionListener {

	public static final String TAG = "query";

	private JTextField tf_id;
	private JTable table;
	private JLabel lb_id;
	private JButton btn_query;
	private Object[] tableHeader = { "序号", "卡号", "余额", "状态" };
	private JScrollPane scroll_panel;

	private JTextArea ta_info;

	public PanelQue() {
		setLayout(null);
		newComponent();
		setPosition();
	}

	private void newComponent() {
		lb_id = new JLabel("身份证号");
		tf_id = new JTextField();
		btn_query = new JButton("查询");
		table = new JTable(new DefaultTableModel(null, tableHeader));
		table.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		scroll_panel = new JScrollPane();
		ta_info = new JTextArea();
		ta_info.setEditable(false);
		ta_info.setFont(new Font("微软雅黑", Font.PLAIN, 15));
	}

	private void setPosition() {
		lb_id.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_id.setBounds(20, 28, 66, 15);
		add(lb_id);
		tf_id.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_id.setBounds(103, 20, 180, 30);
		add(tf_id);
		tf_id.addKeyListener(new IntegerLimitedKeyListener());
		btn_query.addActionListener(this);
		btn_query.setBounds(561, 20, 90, 30);
		add(btn_query);
		scroll_panel.setBounds(20, 200, 631, 202);
		add(scroll_panel);
		scroll_panel.setViewportView(table);
		ta_info.setBounds(20, 65, 631, 125);
		add(ta_info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ta_info.setText("");
		table.setModel(new DefaultTableModel(null, tableHeader));
		UserManager userManager = UserManager.getInstance();
		if (!tf_id.getText().equals("")
				&& userManager.getUserIdByPersonId(tf_id.getText()) != -1) {
			refresh();
		}
	}

	private void refresh() {
		// TODO 查询用户信息，并且返回用户持有的卡号的现状
		CardUserManager cardUserManager = CardUserManager.getInstance();
		UserManager userManager = UserManager.getInstance();
		String[][] cardInfos = cardUserManager.getCardStatusByPersonId(tf_id
				.getText());
		table.setModel(new DefaultTableModel(cardInfos, tableHeader));
		ta_info.setText(userManager.getUserInfo(tf_id.getText()));
	}

}
