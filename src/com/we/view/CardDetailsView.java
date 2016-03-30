package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import javax.swing.JTable;

public class CardDetailsView extends JFrame implements ActionListener{

	private static final long serialVersionUID = -4046477038390427048L;
	private JPanel contentPane;
	
	CardManager dbManager = CardManager.getInstance();
	private JButton btn_back;
	private JButton btn_exit;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardDetailsView frame = new CardDetailsView();
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
	public CardDetailsView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btn_exit = new JButton("退出");
		btn_exit.setBounds(31, 427, 93, 23);
		contentPane.add(btn_exit);
		btn_exit.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(629, 427, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		JLabel label = new JLabel("历史交易明细");
		label.setBounds(166, 25, 121, 23);
		contentPane.add(label);
		
		table = new JTable();
		table.setBounds(124, 178, 163, -106);
		contentPane.add(table);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
