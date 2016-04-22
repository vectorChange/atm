package com.we.admin;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.we.admin.panel.PanelChange;
import com.we.admin.panel.PanelCreate;
import com.we.admin.panel.PanelDel;
import com.we.admin.panel.PanelFrozen;
import com.we.admin.panel.PanelLost;
import com.we.admin.panel.PanelQue;
import com.we.admin.panel.PanelRec;
import com.we.admin.panel.PanelSearchUser;

/**
 * 管理界面的Frame finish
 * @author 梓扬
 *
 */
@SuppressWarnings("serial")
public class AdminMain extends JFrame {

	private int type;
	
	private JPanel contentPane;
	private JMenuBar menu_bar;
	private JMenu item_account;
	private JMenu item_activity;
	private JMenu item_query;
	private JMenu item_right;
	private JMenuItem sel_create;
	private JMenuItem sel_del;
	private JMenuItem sel_lost;
	private JMenuItem sel_frozen;
	private JMenuItem sel_scan;
	private JMenuItem sel_acc;
	private JMenuItem sel_change;
	private JMenuItem sel_card_for_user;
	private CardLayout cl;
	private Font fontItem = new Font("Microsoft YaHei UI", Font.PLAIN, 14);
	private Font fontselect = new Font("Microsoft YaHei UI", Font.PLAIN, 13);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel(new HiFiLookAndFeel());
					AdminMain frame = new AdminMain(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMain(int type) {
		setResizable(false);
		setFont(new Font("宋体", Font.PLAIN, 14));
		setTitle("管理界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 490);
		contentPane = new JPanel();
		cl = new CardLayout();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.type = type;
		contentPane.setLayout(cl);
		initView();
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		addMenu();
		addPanel();
		if ( type == 0 ) {
			sel_change.setEnabled(false);
		}
	}

	/**
	 * 添加panel到当前Frame中
	 */
	private void addPanel() {

		contentPane.add(new PanelCreate(), PanelCreate.TAG);
		sel_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelCreate.TAG);
			}
		});
		contentPane.add(new PanelDel(), PanelDel.TAG);
		sel_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelDel.TAG);
			}
		});
		contentPane.add(new PanelLost(), PanelLost.TAG);
		sel_lost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelLost.TAG);
			}
		});
		contentPane.add(new PanelFrozen(), PanelFrozen.TAG);
		sel_frozen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelFrozen.TAG);
			}
		});
		
		contentPane.add(new PanelRec(), PanelRec.TAG);
		sel_scan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelRec.TAG);
			}
		});
		
		contentPane.add(new PanelQue(), PanelQue.TAG);
		sel_acc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelQue.TAG);
			}
		});
		
		contentPane.add(new PanelSearchUser(), PanelSearchUser.TAG);
		sel_card_for_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelSearchUser.TAG);
			}
		});
		
		contentPane.add(new PanelChange(), PanelChange.TAG);
		sel_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contentPane, PanelChange.TAG);
			}
		});
	}
	
	/**
	 * 添加Menu到Frame中
	 */
	private void addMenu() {
		menu_bar = new JMenuBar();
		setJMenuBar(menu_bar);

		item_account = new JMenu("用户管理");
		item_account.setFont(fontItem);
		menu_bar.add(item_account);
		item_activity = new JMenu("账号活动");
		item_activity.setFont(fontItem);
		menu_bar.add(item_activity);
		item_query = new JMenu("信息查询");
		item_query.setFont(fontItem);
		menu_bar.add(item_query);
		item_right = new JMenu("权限");
		item_right.setFont(fontItem);
		menu_bar.add(item_right);

		sel_create = new JMenuItem("开户功能");
		sel_create.setFont(fontselect);
		item_account.add(sel_create);
		sel_del = new JMenuItem("销户功能");
		sel_del.setFont(fontselect);
		item_account.add(sel_del);
		
		sel_lost = new JMenuItem("挂失\\解挂失");
		sel_lost.setFont(fontselect);
		item_activity.add(sel_lost);
		sel_frozen = new JMenuItem("冻结\\解冻");
		sel_frozen.setFont(fontselect);
		item_activity.add(sel_frozen);


		sel_scan = new JMenuItem("交易记录查询");
		sel_scan.setFont(fontselect);
		item_query.add(sel_scan);
		sel_acc = new JMenuItem("用户信息查询");
		sel_acc.setFont(fontselect);
		item_query.add(sel_acc);

		sel_change = new JMenuItem("更改密码");
		sel_change.setFont(fontselect);
		item_right.add(sel_change);
		
		sel_card_for_user = new JMenuItem("查询用户信息");
		sel_card_for_user.setFont(fontselect);
		item_query.add(sel_card_for_user);
	}
}
