package com.we;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.util.MainImagePane;
import com.we.util.VFlowLayout;

public class MainControlView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainControlView frame = new MainControlView();
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
	public MainControlView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		MainImagePane mainImagePane = new MainImagePane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(10, 66, 93, 23);
		mainImagePane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("存款");
		btnNewButton_2.setBounds(10, 130, 93, 23);
		mainImagePane.add(btnNewButton_2);
		
		JButton button_1 = new JButton("取款");
		button_1.setBounds(10, 188, 93, 23);
		mainImagePane.add(button_1);
		
		JButton button_2 = new JButton("转账");
		button_2.setBounds(10, 253, 93, 23);
		mainImagePane.add(button_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
