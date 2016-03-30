package com.we.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import com.we.dao.CardManager;

public class CardSaveView extends JFrame implements ActionListener{

	private static final long serialVersionUID = -4046477038390427048L;
	private JPanel contentPane;
	private JTextField tf_num;
	private JButton btn_100;
	private JButton btn_300;
	private JButton btn_500; 
	private JButton btn_1000;
	
	CardManager dbManager = CardManager.getInstance();
	private JButton btn_back;
	private JButton btn_sure;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardSaveView frame = new CardSaveView();
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
	public CardSaveView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_100 = new JButton("100");
		btn_100.setBounds(10, 61, 93, 23);
		contentPane.add(btn_100);
		btn_100.addActionListener(this);
		
		btn_300 = new JButton("300");
		btn_300.setBounds(10, 121, 93, 23);
		contentPane.add(btn_300);
		btn_300.addActionListener(this);
		
		btn_500 = new JButton("500");
		btn_500.setBounds(320, 61, 93, 23);
		contentPane.add(btn_500);
		btn_500.addActionListener(this);
		
		btn_1000 = new JButton("1000");
		btn_1000.setBounds(320, 121, 93, 23);
		contentPane.add(btn_1000);
		btn_1000.addActionListener(this);

		btn_sure = new JButton("确定");
		btn_sure.setBounds(320, 204, 93, 23);
		contentPane.add(btn_sure);
		btn_sure.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(10, 204, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		tf_num = new JTextField();
		tf_num.setBounds(166, 62, 93, 22);
		contentPane.add(tf_num);
		tf_num.setColumns(10);
		tf_num.setText("123");
		
		JLabel label = new JLabel("输入存款额");
		label.setBounds(166, 25, 121, 23);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_300){
			dbManager.saveCash(300);
		}else if(e.getSource() == btn_500){
			dbManager.saveCash(500);
		}else if(e.getSource() == btn_1000){
			dbManager.saveCash(1000);
		}else if(e.getSource() == btn_100){
			dbManager.saveCash(100);
		}else if(e.getSource() == btn_sure){
			int addNum = Integer.parseInt(tf_num.getText());
			dbManager.saveCash(addNum);
		}else if(e.getSource() == btn_back){
			dispose();
		}
	}
}
