package com.we;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.we.admin.AdminLoginView;
import com.we.view.MyPanel;
import com.we.view.UserLoginFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener {

	private MyPanel contentPane;
	private JButton btn_manager;
	private JButton btn_normal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setTitle("身份选择");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 568, 379);
		contentPane = new MyPanel(new File("\\res\\select_bg.png"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponent();
	}

	// 初始化组件
	private void initComponent() {
		Icon icon = new ImageIcon("res\\acc_btn.png");
		btn_manager = new JButton(icon);
		btn_manager.setContentAreaFilled(false);
		btn_manager.setBorderPainted(false);
		btn_manager.setBounds(218, 205, icon.getIconWidth(), icon.getIconHeight());
		
		btn_normal = new JButton("用户");
		
		btn_normal.setBounds(218, 78, 115, 37);
		contentPane.add(btn_normal);
		contentPane.add(btn_manager);
		btn_manager.addActionListener(this);
		btn_normal.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tmp = (JButton) e.getSource();
		if (tmp == btn_normal) {
			new UserLoginFrame().setVisible(true);
		}else if (tmp == btn_manager){
			new AdminLoginView().setVisible(true);
		}
		dispose();
	}

}
