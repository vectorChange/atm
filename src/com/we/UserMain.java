package com.we;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.util.DateUtil;
import com.we.util.MainImagePane;
import com.we.view.CardDetailsView;
import com.we.view.CardQueryView;
import com.we.view.CardSaveView;
import com.we.view.CardTakeView;
import com.we.view.CardTransfersView;

public class UserMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = -87501980138210364L;
	private JPanel contentPane;
	private JButton btn_query;
	private JButton btn_save;
	private JButton btn_take;
	private JButton btn_transfers;
	private JButton btn_history;
	private JButton btn_exit;

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
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		MainImagePane mainImagePane = new MainImagePane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		btn_query = new JButton("查询");
		btn_query.setBounds(10, 121, 93, 23);
		mainImagePane.add(btn_query);
		btn_query.addActionListener(this);
		
		btn_save = new JButton("存款");
		btn_save.setBounds(749, 230, 93, 23);
		mainImagePane.add(btn_save);
		btn_save.addActionListener(this);
		
		btn_take = new JButton("取款");
		btn_take.setBounds(749, 121, 93, 23);
		mainImagePane.add(btn_take);
		btn_take.addActionListener(this);
		
		btn_transfers = new JButton("转账");
		btn_transfers.setBounds(749, 345, 93, 23);
		btn_transfers.addActionListener(this);
		mainImagePane.add(btn_transfers);
		
		btn_history = new JButton("记录查询");
		btn_history.setBounds(10, 230, 93, 23);
		mainImagePane.add(btn_history);
		btn_history.addActionListener(this);

		btn_exit = new JButton("退出");
		btn_exit.addActionListener(this);
		btn_exit.setBounds(10, 345, 93, 23);
		mainImagePane.add(btn_exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if( btn == btn_query){
			new CardQueryView().setVisible(true);
			dispose();
		}else if(btn == btn_save){
			new CardSaveView().setVisible(true);
			dispose();
		}else if(btn == btn_take){
			new CardTakeView().setVisible(true);
			dispose();
		}else if(btn == btn_transfers){
			new CardTransfersView().setVisible(true);;
			dispose();
		}else if(btn == btn_exit){
			dispose();
		}else if(btn == btn_history){
			new CardDetailsView().setVisible(true);
			dispose();
		}
	}
}
