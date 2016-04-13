package com.we.admin.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.we.dao.UserManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelSignUp extends JPanel {

	public static final String TAG = "sign_up";

	private JTextField tf_name;
	private JTextField tf_idcard;
	private JTextField tf_phone;

	private UserManager userManager = UserManager.getInstance();

	public PanelSignUp() {
		setLayout(null);

		JLabel lb = new JLabel("注册用户");
		lb.setBounds(10, 10, 54, 15);
		add(lb);

		JLabel lb_name = new JLabel("用户名");
		lb_name.setBounds(168, 117, 54, 15);
		add(lb_name);

		JLabel lb_id = new JLabel("身份证");
		lb_id.setBounds(168, 160, 54, 15);
		add(lb_id);

		JLabel lb_phone = new JLabel("手机号");
		lb_phone.setBounds(168, 203, 54, 15);
		add(lb_phone);

		tf_name = new JTextField();
		tf_name.setBounds(217, 114, 177, 21);
		add(tf_name);
		tf_name.setColumns(10);

		tf_idcard = new JTextField();
		tf_idcard.setBounds(217, 157, 177, 21);
		add(tf_idcard);
		tf_idcard.setColumns(10);

		tf_phone = new JTextField();
		tf_phone.setBounds(217, 200, 177, 21);
		add(tf_phone);
		tf_phone.setColumns(10);

		JButton btn_signup = new JButton("注册");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tf_name.getText().equals("")
						|| tf_idcard.getText().equals("")) {
					JOptionPane.showMessageDialog(PanelSignUp.this,
							"身份证和姓名不能为空");
				} else {
					String tmp = tf_phone.getText().equals("") ? null
							: tf_phone.getText();
					userManager.createUser(tf_name.getText(),
							tf_idcard.getText(), tmp);
					JOptionPane.showMessageDialog(PanelSignUp.this, "注册成功");
				}
			}
		});
		btn_signup.setBounds(168, 264, 93, 23);
		add(btn_signup);

		JButton btn_reset = new JButton("重置");
		btn_reset.setBounds(301, 264, 93, 23);
		add(btn_reset);
	}
}
