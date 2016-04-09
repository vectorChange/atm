package com.we.admin.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PanelFrozen extends JPanel{
	public static final String TAG = "frozen";
	private JTextField tf;
	private final ButtonGroup btn_group = new ButtonGroup();
	public PanelFrozen() {
		setLayout(null);
		
		JLabel lab = new JLabel("冻结|解冻");
		lab.setBounds(10, 10, 84, 15);
		add(lab);
		
		JLabel lab_num = new JLabel("卡号：");
		lab_num.setBounds(159, 126, 54, 15);
		add(lab_num);
		
		tf = new JTextField();
		tf.setBounds(223, 123, 183, 21);
		add(tf);
		tf.setColumns(10);
		
		JRadioButton rb_frozen = new JRadioButton("冻结");
		btn_group.add(rb_frozen);
		rb_frozen.setBounds(159, 180, 64, 23);
		add(rb_frozen);
		
		JRadioButton rb_unfro = new JRadioButton("解冻");
		btn_group.add(rb_unfro);
		rb_unfro.setBounds(342, 180, 64, 23);
		add(rb_unfro);
		
		JButton btn_con = new JButton("确认");
		btn_con.setBounds(159, 239, 93, 23);
		add(btn_con);
		
		JButton btn_reset = new JButton("重置");
		btn_reset.setBounds(313, 239, 93, 23);
		add(btn_reset);
	}
}
