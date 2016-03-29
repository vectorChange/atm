package com.we;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.util.MainImagePane;
import com.we.view.CardQueryView;
import com.we.view.CardSaveView;
import com.we.view.CardTransfersView;

public class UserMain extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btn_query;
	private JButton btn_save;
	private JButton btn_take;
	private JButton btn_transfers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
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
	public UserMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		MainImagePane mainImagePane = new MainImagePane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		btn_query = new JButton("查询");
		btn_query.setBounds(10, 66, 93, 23);
		mainImagePane.add(btn_query);
		btn_query.addActionListener(this);
		
		btn_save = new JButton("存款");
		btn_save.setBounds(10, 130, 93, 23);
		mainImagePane.add(btn_save);
		btn_save.addActionListener(this);
		
		btn_take = new JButton("取款");
		btn_take.setBounds(10, 188, 93, 23);
		mainImagePane.add(btn_take);
		btn_take.addActionListener(this);
		
		btn_transfers = new JButton("转账");
		btn_transfers.setBounds(10, 253, 93, 23);
		btn_transfers.addActionListener(this);
		mainImagePane.add(btn_transfers);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_query){
			CardQueryView cardQueryView = new CardQueryView();
			cardQueryView.setVisible(true);
			dispose();
		}else if(e.getSource() == btn_save){
			CardSaveView cardSaveView = new CardSaveView();
			cardSaveView.setVisible(true);
			dispose();
		}else if(e.getSource() == btn_take){
//			new CardT().show();
			dispose();
		}else if(e.getSource() == btn_transfers){
			CardTransfersView cardTransfersView = new CardTransfersView();
			cardTransfersView.setVisible(true);
			dispose();
		}
	}
}
