package com.we.admin.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import com.we.dao.CardManager;
import com.we.util.IntegerLimitedKeyListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelChange extends JPanel implements ActionListener {

	public static final String TAG = "change";
	private JTextField tf_acc;
	private JPasswordField pf;
	private JButton btn_reset;
	private JButton btn_con;
	private JLabel lb_hint;
	private JLabel lb_pwd;
	private JLabel lab_acc;

	public PanelChange() {
		setLayout(null);

		lab_acc = new JLabel("卡号");
		lab_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lab_acc.setBounds(198, 164, 54, 15);
		add(lab_acc);

		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.addKeyListener(new IntegerLimitedKeyListener());
		tf_acc.setBounds(248, 156, 224, 30);
		add(tf_acc);

		lb_pwd = new JLabel("密码");
		lb_pwd.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_pwd.setBounds(198, 227, 54, 15);
		add(lb_pwd);

		btn_con = new JButton("确认");
		btn_con.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btn_con.setBounds(198, 285, 90, 30);
		btn_con.addActionListener(this);
		add(btn_con);

		btn_reset = new JButton("重置");
		btn_reset.setFont(new Font("微软雅黑", Font.BOLD, 13));
		btn_reset.setBounds(382, 285, 90, 30);
		btn_reset.addActionListener(this);
		add(btn_reset);

		pf = new JPasswordField();
		pf.setFont(new Font("宋体", Font.BOLD, 18));
		pf.setBounds(248, 219, 224, 30);
		add(pf);

		lb_hint = new JLabel("重要操作，请小心！");
		lb_hint.setFont(new Font("幼圆", Font.BOLD, 18));
		lb_hint.setForeground(Color.RED);
		lb_hint.setBackground(Color.RED);
		lb_hint.setBounds(263, 51, 183, 36);
		add(lb_hint);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmpButton = (JButton) e.getSource();
		if (tmpButton == btn_reset) {
			tf_acc.setText("");
			pf.setText("");
		} else {
			if (tf_acc.getText().equals("")
					|| new String(pf.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "不能为空");
			} else {
				CardManager cardManager = CardManager.getInstance();
				if (cardManager.getCardIdByCardNum(tf_acc.getText()) == CardManager.NO_EXIT) {
					JOptionPane.showMessageDialog(this, "不存在");
				} else {
					cardManager.changePwd(tf_acc.getText(),
							new String(pf.getPassword()));
					JOptionPane.showMessageDialog(this, "修改成功！");
					tf_acc.setText("");
					pf.setText("");
				}
			}
		}
	}
}
