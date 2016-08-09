package com.we.admin.panel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.we.dao.CardManager;
import com.we.dao.CardNumManager;
import com.we.dao.UserManager;

/**
 * 开户的界面，用户输入设置的密码和自己的身份证号即可。 界面返回，卡号
 * 
 * @author xiaoyang
 *
 */
@SuppressWarnings("serial")
public class PanelCreate extends JPanel implements ActionListener {

	// --------------------main method---------------------------------
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new HiFiLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame fame = new JFrame();
		fame.setTitle("Sign in");
		Container container = fame.getContentPane();
		Rectangle rectangle = new Rectangle(680, 470);
		fame.setBounds(rectangle);
		container.add(new PanelCreate());
		fame.setVisible(true);
		fame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ---------------------------------------------------------

	/**
	 * 该页面的标志可能会删除
	 */
	public static final String TAG = "create";

	public PanelCreate() {
		setLayout(null);
		newComponent();
		ComponentSetting();
		setPosition();
	}

	/**
	 * 实例化组件
	 */
	private void newComponent() {
		tf_cardNum = new JTextField();
		tf_name = new JTextField();
		tf_name.setToolTipText("");
		tf_phone = new JTextField();
		tf_personId = new JTextField();
		pf_surePwd = new JPasswordField();
		ta_address = new JTextArea();
		pf_pwd = new JPasswordField();
		label_phone = new JLabel("绑定手机");
		label_name = new JLabel("用户姓名");
		label_personId = new JLabel("身份证号");
		label_pwd = new JLabel("密码输入");
		label_surePwd = new JLabel("确认密码");
		label_address = new JLabel("地址");
		label_cardNum = new JLabel("生成卡号");
		btn_submit = new JButton("提交注册");
		btn_clear = new JButton("清空");
		rb_male = new JRadioButton("男");
		rb_female = new JRadioButton("女");
		label_sex = new JLabel("用户性别");
		label_hint = new JLabel("");
		label_hint.setForeground(Color.RED);
	}

	/**
	 * 设置组件的位置，添加其中依赖
	 */
	private void setPosition() {
		label_name.setBounds(24, 35, 78, 31);
		add(label_name);
		tf_name.setBounds(100, 35, 170, 30);
		add(tf_name);
		label_phone.setBounds(24, 167, 78, 31);
		add(label_phone);
		btn_submit.setBounds(373, 366, 90, 30);
		add(btn_submit);
		btn_clear.setBounds(543, 366, 90, 30);
		add(btn_clear);
		tf_cardNum.setBounds(373, 276, 260, 30);
		add(tf_cardNum);
		label_cardNum.setBounds(373, 233, 78, 31);
		add(label_cardNum);
		label_address.setBounds(373, 35, 78, 31);
		add(label_address);
		ta_address.setBounds(373, 78, 260, 114);
		add(ta_address);
		pf_pwd.setBounds(100, 299, 170, 30);
		add(pf_pwd);
		pf_surePwd.setBounds(100, 365, 170, 30);
		add(pf_surePwd);
		label_sex.setBounds(24, 101, 78, 31);
		add(label_sex);
		rb_male.setBounds(100, 107, 56, 18);
		add(rb_male);
		rb_female.setBounds(181, 107, 62, 18);
		add(rb_female);
		tf_phone.setBounds(100, 167, 170, 30);
		add(tf_phone);
		label_personId.setBounds(24, 233, 78, 31);
		add(label_personId);
		tf_personId.setBounds(100, 233, 170, 30);
		add(tf_personId);
		label_pwd.setBounds(24, 299, 78, 31);
		add(label_pwd);
		label_surePwd.setBounds(24, 365, 78, 31);
		add(label_surePwd);
		label_hint.setBounds(282, 372, 79, 18);
		add(label_hint);
		buttonGroup.add(rb_male);
		buttonGroup.add(rb_female);
	}

	/**
	 * 设置组件的属性
	 */
	private void ComponentSetting() {
		label_name.setFont(font_label);
		tf_name.setFont(font_content);
		label_phone.setFont(font_label);
		tf_phone.setFont(font_content);
		label_personId.setFont(font_label);
		tf_personId.setFont(font_content);
		label_pwd.setFont(font_label);
		label_surePwd.setFont(font_label);
		pf_pwd.setFont(font_content);
		pf_surePwd.setFont(font_content);
		label_sex.setFont(font_label);
		ta_address.setLineWrap(true);
		ta_address.setFont(font_content);
		label_address.setFont(font_label);
		label_cardNum.setFont(font_label);
		tf_cardNum.setEditable(false);
		rb_male.setSelected(true);
		btn_clear.addActionListener(this);
		btn_submit.addActionListener(this);
		pf_surePwd.addFocusListener(new CheckPwdEqueal());
		tf_cardNum.setFont(font_content);
		tf_cardNum.setText(cardNumManager.getNextNum());
	}

	/**
	 * 该页面所用到的字体，和变量索引
	 */
	Font font_label = new Font("幼圆", Font.BOLD, 14);
	Font font_content = new Font("宋体", Font.PLAIN, 18);
	private JTextField tf_name;
	private JTextField tf_phone;
	private JTextField tf_personId;
	private JTextField tf_cardNum;
	private JPasswordField pf_pwd;
	private JPasswordField pf_surePwd;
	private JLabel label_name;
	private JLabel label_phone;
	private JLabel label_personId;
	private JLabel label_pwd;
	private JLabel label_surePwd;
	private JLabel label_sex;
	private JLabel label_address;
	private JLabel label_cardNum;
	private JRadioButton rb_male;
	private JTextArea ta_address;
	private JButton btn_submit;
	private JButton btn_clear;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rb_female;
	private JLabel label_hint;
	private CardNumManager cardNumManager = CardNumManager.getInstance();

	/**
	 * 清空组件的中的内容
	 */
	private void clearComponent() {
		tf_name.setText("");
		tf_phone.setText("");
		tf_personId.setText("");
		pf_pwd.setText("");
		pf_surePwd.setText("");
		ta_address.setText("");
	}

	/**
	 * 新建一个用户 <br/>
	 * important
	 */
	private void newUser() {
		// TODO
		// 依据personId 检查是否有该userinfo
		UserManager userManager = UserManager.getInstance();
		CardManager cardManager = CardManager.getInstance();
		int userId = userManager.getUserIdByPersonId(tf_personId.getText());
		if (userId == -1) {
			userManager.createUser(tf_name.getText(), tf_personId.getText(),
					tf_phone.getText(), rb_male.isSelected() ? "男" : "女",
					ta_address.getText());
			userId = userManager.getUserIdByPersonId(tf_personId.getText());
		} else {
			userManager.updateUserInfoByPersonId(tf_personId.getText(), tf_name
					.getText(), tf_phone.getText(), rb_male.isSelected() ? "男"
					: "女", ta_address.getText());
		}
		// 最后进行card create
		cardManager.newCardForUser(userId, new String(pf_pwd.getPassword()),
				tf_cardNum.getText());
		// 更新 cardNum表
		cardNumManager.setNum(tf_cardNum.getText());
		tf_cardNum.setText(cardNumManager.getNextNum());
	}

	/**
	 * 检查组件内容是否为空
	 * 
	 * @return
	 */
	private boolean checkEmpty() {
		if (tf_name.getText().equals(""))
			return true;
		if (tf_personId.getText().equals(""))
			return true;
		if (tf_phone.getText().equals(""))
			return true;
		if (pf_pwd.getPassword().equals(""))
			return true;
		if (pf_surePwd.getPassword().equals(""))
			return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton actionButton = (JButton) e.getSource();
		if (actionButton == btn_clear) {
			clearComponent();
		} else if (actionButton == btn_submit) {
			if (checkEmpty()) {
				JOptionPane.showMessageDialog(this, "填写信息不能为空");
			} else {
				// TODO 创建用户
				newUser();
				JOptionPane.showMessageDialog(this, "注册成功");
			}
		}
		// newUser();
	}

	/**
	 * 检查密码是否一致
	 * 
	 * @author xiaoyang
	 *
	 */
	class CheckPwdEqueal implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			label_hint.setText("");
			btn_submit.setEnabled(true);
		}

		@Override
		public void focusLost(FocusEvent e) {
			String pwd = new String(pf_pwd.getPassword());
			String surePwd = new String(pf_surePwd.getPassword());
			if (!pwd.equals(surePwd)) {
				label_hint.setText("密码不正确");
				btn_submit.setEnabled(false);
			}
		}
	}
}
