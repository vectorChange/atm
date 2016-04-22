package com.we.admin.panel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import com.we.dao.CardManager;
import com.we.dao.CardUserManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelLost extends JPanel implements ActionListener {
	public static final String TAG = "lost";
	private JTextField tf_acc;
	private JRadioButton rb_lost;
	private JRadioButton rb_unlost;
	private final ButtonGroup btn_group = new ButtonGroup();
	private JTextArea textArea;
	private JLabel label;
	private CardManager cardManager = CardManager.getInstance();
	private JButton btn_con;
	private JButton btn_reset;

	public PanelLost() {
		setLayout(null);
		JLabel lb_acc = new JLabel("卡号");
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		lb_acc.setBounds(213, 48, 54, 15);
		add(lb_acc);
		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.setBounds(277, 40, 170, 30);
		add(tf_acc);
		rb_lost = new JRadioButton("挂失");
		btn_group.add(rb_lost);
		rb_lost.setBounds(213, 102, 64, 23);
		add(rb_lost);

		rb_unlost = new JRadioButton("解挂失");
		btn_group.add(rb_unlost);
		rb_unlost.setBounds(369, 102, 78, 23);
		add(rb_unlost);

		btn_con = new JButton("确认");
		btn_con.addActionListener(this);
		btn_con.setBounds(213, 161, 90, 30);
		add(btn_con);
		btn_reset = new JButton("重置");
		btn_reset.addActionListener(this);
		btn_reset.setBounds(357, 161, 90, 30);
		add(btn_reset);

		label = new JLabel("该卡状态");
		label.setFont(new Font("幼圆", Font.BOLD, 14));
		label.setBounds(6, 229, 82, 18);
		add(label);

		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		textArea.setBounds(71, 257, 538, 153);
		add(textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton actionButton = (JButton) e.getSource();
		if (actionButton == btn_reset) {
			tf_acc.setText("");
		} else if (actionButton == btn_con) {
			textArea.setText("");
			if (!tf_acc.getText().equals("")) {
				if ( cardManager.getCardIdByCardNum(tf_acc.getText()) != CardManager.NO_EXIT) {
					CardUserManager cardUserManager = CardUserManager.getInstance();
					int flag = rb_lost.isSelected() ? 1 : 0;
					cardManager.lostCard(tf_acc.getText(), flag);
					textArea.setText(cardUserManager
							.getCardUserStatusByCardNum(tf_acc.getText())
							+ "该卡状态： "
							+ cardManager.queryCardState(tf_acc.getText()));
					JOptionPane.showMessageDialog(PanelLost.this, "操作成功");
				}else {
					JOptionPane.showMessageDialog(PanelLost.this, "没有该卡");
				}
			} else {
				JOptionPane.showMessageDialog(PanelLost.this, "不能为空");
			}
		}
	}
}
