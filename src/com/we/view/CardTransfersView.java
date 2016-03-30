package com.we.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardTransfersView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3477820948172292970L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btn_exit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardTransfersView frame = new CardTransfersView();
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
	public CardTransfersView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入转入卡号");
		lblNewLabel.setBounds(268, 98, 144, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(268, 152, 144, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("小心诈骗，客服电话66666");
		lblNewLabel_1.setBounds(274, 224, 163, 73);
		contentPane.add(lblNewLabel_1);
		
		btn_exit = new JButton("退出");
		btn_exit.addActionListener(this);
		btn_exit.setBounds(10, 355, 93, 23);
		contentPane.add(btn_exit);

		
		JButton button_1 = new JButton("确定");
		button_1.setBounds(618, 355, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("更正");
		button_2.setBounds(618, 249, 93, 23);
		contentPane.add(button_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == btn_exit){
			dispose();
		}
	}

}
