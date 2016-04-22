package com.we.admin.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.we.dao.CardManager;
import com.we.dao.CardUserManager;
import com.we.util.IntegerLimitedKeyListener;

@SuppressWarnings("serial")
public class PanelSearchUser extends JPanel implements ActionListener {
	public static final String TAG = "searchuser";

	private JTextField tf_acc;

	private JButton btn_con;

	private JLabel lb_user;

	private JTextArea textArea;

	private JLabel lb_acc;

	public PanelSearchUser() {
		setLayout(null);

		lb_acc = new JLabel("卡号");
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_acc.setBounds(127, 55, 54, 15);
		add(lb_acc);

		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.setBounds(199, 47, 209, 30);
		tf_acc.addKeyListener(new IntegerLimitedKeyListener());
		add(tf_acc);

		btn_con = new JButton("查询");
		btn_con.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btn_con.setBounds(472, 49, 80, 26);
		btn_con.addActionListener(this);
		add(btn_con);

		lb_user = new JLabel("持有者信息");
		lb_user.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_user.setBounds(45, 154, 92, 15);
		add(lb_user);

		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

		TitledBorder.TOP, null, null));
		textArea.setBounds(69, 204, 554, 191);
		add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.setText("");
		if (tf_acc.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "不能为空");
		} else {
			// 有账号的
			CardManager cardManager = CardManager.getInstance();
			if (CardManager.NO_EXIT == cardManager.getCardIdByCardNum(tf_acc
					.getText())) {
				JOptionPane.showMessageDialog(this, "不存在该卡号");
			} else {
				CardUserManager cardUserManager = CardUserManager.getInstance();
				textArea.setText(cardUserManager
						.getCardUserStatusByCardNum(tf_acc.getText())
						+ "该卡状态： "
						+ cardManager.queryCardState(tf_acc.getText()));
			}
		}
	}

}
