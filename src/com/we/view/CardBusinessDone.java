package com.we.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardBusinessDone extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8878416190270664711L;
	private JPanel contentPane;
	private JButton btn_showCash;
	private JButton btn_print;
	private JButton btn_continue;
	private JButton btn_back;
	private JButton btn_exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardBusinessDone frame = new CardBusinessDone();
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
	public CardBusinessDone() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_continue = new JButton("继续取款");
		btn_continue.setBounds(295, 42, 93, 23);
		contentPane.add(btn_continue);
		btn_continue.addActionListener(this);
		
		btn_showCash = new JButton("显示余额");
		btn_showCash.setBounds(295, 95, 93, 23);
		contentPane.add(btn_showCash);
		btn_showCash.addActionListener(this);
		
		btn_print = new JButton("打印凭条");
		btn_print.setBounds(295, 153, 93, 23);
		contentPane.add(btn_print);
		btn_print.addActionListener(this);
		
		btn_back = new JButton("返回");
		btn_back.setBounds(295, 199, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		btn_exit = new JButton("退卡");
		btn_exit.setBounds(10, 199, 93, 23);
		contentPane.add(btn_exit);
		btn_exit.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
