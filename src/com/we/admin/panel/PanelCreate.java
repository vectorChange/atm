package com.we.admin.panel;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.we.dao.CardManager;
import com.we.dao.UserManager;
import com.we.util.IntegerLimitedKeyListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.JPasswordField;

/**
 * 开户的界面，用户输入设置的密码和自己的身份证号即可。 界面返回，卡号
 * 
 * @author 梓扬
 *
 */
@SuppressWarnings("serial")
public class PanelCreate extends JPanel {

	public static final String TAG = "create";

	private JTextField tf_id;
	JLabel lb_hint;
	JLabel lb_hint_pwd;
	JButton btn_con;
	private UserManager userManager = UserManager.getInstance();
	private CardManager cardManager = CardManager.getInstance();
	private JPasswordField pf_pwd;
	private JPasswordField pf_sure_pwd;

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
		tf_id.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (tf_id.getText().equals("")) {
					lb_hint.setText("该用户不存在，请先注册用户");
					btn_con.setEnabled(false);
				} else {
					if (userManager.getUserIdByPersonId(tf_id.getText()) == -1) {
						lb_hint.setText("该用户不存在，请先注册用户");
						btn_con.setEnabled(false);
					} else {
						lb_hint.setText("");
						btn_con.setEnabled(true);
					}
				}
			}
		});
		tf_id.addKeyListener(new IntegerLimitedKeyListener());
		tf_id.setBounds(234, 103, 162, 21);
		tf_id.setText("");
		add(tf_id);
		tf_id.setColumns(16);

		btn_con = new JButton("确认");
		btn_con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd = new String(pf_pwd.getPassword());
				String sure_pwd = new String(pf_sure_pwd.getPassword());
				if (pwd.equals(sure_pwd)) {
					String cardNumString;
					cardNumString = cardManager.newCardForUser(
							userManager.getUserIdByPersonId(tf_id.getText()), sure_pwd);
					JOptionPane.showMessageDialog(PanelCreate.this, "开户成功卡号为:"
							+ cardNumString);
					tf_id.setText("");
					pf_pwd.setText("");
					pf_sure_pwd.setText("");
				} else {
					lb_hint_pwd.setText("密码不同");
				}
			}
		});
		btn_con.setBounds(170, 267, 93, 23);
		add(btn_con);

		JButton btn_reset = new JButton("重置");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_id.setText("");
				pf_pwd.setText("");
				pf_sure_pwd.setText("");
			}
		});
		btn_reset.setBounds(307, 267, 93, 23);
		add(btn_reset);

		lb_hint = new JLabel("");
		lb_hint.setForeground(Color.RED);
		lb_hint.setBounds(406, 106, 162, 15);
		add(lb_hint);

		pf_pwd = new JPasswordField();
		pf_pwd.setBounds(234, 151, 162, 21);
		add(pf_pwd);
		
		pf_sure_pwd = new JPasswordField();
		pf_sure_pwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lb_hint_pwd.setText("");
			}
		});
		pf_sure_pwd.setBounds(234, 197, 162, 21);
		add(pf_sure_pwd);

		lb_hint_pwd = new JLabel("");
		lb_hint_pwd.setForeground(Color.RED);
		lb_hint_pwd.setBounds(406, 197, 114, 15);
		add(lb_hint_pwd);
	}
}
