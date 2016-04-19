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

@SuppressWarnings("serial")
public class PanelDel extends JPanel implements ActionListener {

	/**
	 * 该panel的标志
	 */
	public static final String TAG = "delete";

	private JTextField tf_acc;

	private JLabel lb_acc;

	private JButton btn_con;

	private JButton btn_reset;

	private JLabel label_status;

	private JTextArea ta_status;

	public PanelDel() {
		setLayout(null);
		newComponent();
		setPosition();
		componentSetting();
	}

	private void newComponent() {
		lb_acc = new JLabel("卡号");
		tf_acc = new JTextField();
		btn_con = new JButton("确认");
		btn_reset = new JButton("重置");
		label_status = new JLabel("该卡状态");
		ta_status = new JTextArea();
		ta_status.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ta_status.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	private void setPosition() {
		btn_con.setBounds(219, 115, 90, 30);
		add(btn_con);
		btn_reset.setBounds(363, 115, 90, 30);
		add(btn_reset);
		tf_acc.setBounds(283, 55, 170, 30);
		add(tf_acc);
		lb_acc.setBounds(219, 63, 54, 15);
		add(lb_acc);
		label_status.setFont(new Font("幼圆", Font.BOLD, 14));
		label_status.setBounds(12, 183, 82, 18);
		add(label_status);
		ta_status.setEditable(false);
		ta_status.setBounds(80, 214, 524, 187);
		add(ta_status);
	}

	private void componentSetting() {
		lb_acc.setFont(new Font("幼圆", Font.BOLD, 14));
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		btn_con.addActionListener(this);
		btn_reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button == btn_con) {
			ta_status.setText("");
			if (!tf_acc.getText().equals("")) { // textfield不能为空
				CardManager cardManager = CardManager.getInstance();
				CardUserManager cardUserManager = CardUserManager.getInstance();
				if (cardManager.getCardIdByCardNum(tf_acc.getText()) == CardManager.NO_EXIT) {
					JOptionPane.showMessageDialog(this, "卡号不存在");
				} else {
					cardManager.closeCard(tf_acc.getText());
					ta_status.setText(cardUserManager.getCardUserStatusByCardNum(tf_acc.getText()) + 
							"该卡状态： " + cardManager.queryCardState(tf_acc.getText()));
					JOptionPane.showMessageDialog(PanelDel.this, "销号成功");
				}
			} else {
				JOptionPane.showMessageDialog(this, "卡号不能为空");
			}
		} else if (button == btn_reset) {
			tf_acc.setText("");
		}
	}
}
