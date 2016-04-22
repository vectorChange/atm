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
public class PanelFrozen extends JPanel implements ActionListener {
	private CardManager cardManager = CardManager.getInstance();
	public static final String TAG = "frozen";
	private JTextField tf_acc;
	JRadioButton rb_frozen;
	JRadioButton rb_unfro;
	private final ButtonGroup btn_group = new ButtonGroup();
	private JLabel label;
	private JTextArea textArea;
	private JLabel lab_num;
	private JButton btn_con;
	private JButton btn_reset;

	public PanelFrozen() {
		setLayout(null);

		lab_num = new JLabel("卡号");
		lab_num.setFont(new Font("幼圆", Font.BOLD, 14));
		lab_num.setBounds(213, 48, 54, 15);
		add(lab_num);

		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		tf_acc.setBounds(277, 40, 170, 30);
		add(tf_acc);

		rb_frozen = new JRadioButton("冻结");
		btn_group.add(rb_frozen);
		rb_frozen.setBounds(213, 102, 64, 23);
		add(rb_frozen);

		rb_unfro = new JRadioButton("解冻");
		btn_group.add(rb_unfro);
		rb_unfro.setBounds(383, 102, 64, 23);
		add(rb_unfro);

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
		CardUserManager cardUserManager = CardUserManager.getInstance();
		if (actionButton == btn_con) {
			textArea.setText("");
			if (!tf_acc.getText().equals("")) {
				if (cardManager.getCardIdByCardNum(tf_acc.getText()) != CardManager.NO_EXIT) {
					// 传入卡号进行冻结
					int flag = rb_frozen.isSelected() ? 1 : 0;
					cardManager.frozenCard(tf_acc.getText(), flag);
					textArea.setText(cardUserManager
							.getCardUserStatusByCardNum(tf_acc.getText())
							+ "该卡状态： "
							+ cardManager.queryCardState(tf_acc.getText()));
					JOptionPane.showMessageDialog(PanelFrozen.this, "操作成功");
				} else {
					JOptionPane.showMessageDialog(PanelFrozen.this, "卡号不存在");
				}
			} else {
				JOptionPane.showMessageDialog(this, "不能为空");
			}
		} else if (actionButton == btn_reset) {
			tf_acc.setText("");
		}

	}
}
