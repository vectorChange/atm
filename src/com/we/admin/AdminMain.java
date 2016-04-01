package com.we.admin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class AdminMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					AdminMain frame = new AdminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMain() {
		setTitle("管理界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 427);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("用户管理");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("开户功能");
		menu.add(menuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("销户功能");
		menu.add(mntmNewMenuItem);
		
		JMenu menu_1 = new JMenu("账号活动");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("挂失\\解挂失");
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("冻结\\解冻");
		menu_1.add(menuItem_2);
		
		JMenu menu_2 = new JMenu("信息查询");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("交易记录查询");
		menu_2.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("账号相关");
		menu_2.add(menuItem_4);
		
		JMenu mnRight = new JMenu("权限");
		menuBar.add(mnRight);
		
		JMenuItem menuItem_5 = new JMenuItem("更改密码");
		mnRight.add(menuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
