package com.we.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.util.TimerUtil;


public class CardQueryView extends JFrame  implements ActionListener{

	private static final long serialVersionUID = -838514821792658268L;
	private JPanel contentPane;
	private JButton btn_save;
	private JButton btn_history;
	private JButton btn_back;
	private JButton btn_transfers;
	private JButton btn_take;
	private JLabel lb_cash;

	private CardManager cardManager = CardManager.getInstance();;
	private JLabel lb_preCash;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardQueryView frame = new CardQueryView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initData() {
		
		double cash = cardManager.queryCash();
		lb_cash.setText(cash+"");
		lb_preCash.setText(cash+"");
	}

	/**
	 * Create the frame.
	 */
	public CardQueryView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("您的账户余额信息");
		lblNewLabel.setBounds(347, 88, 125, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("账户余额：");
		label.setForeground(Color.RED);
		label.setBounds(347, 138, 72, 15);
		contentPane.add(label);
		
		lb_cash = new JLabel("9,416.62");
		lb_cash.setBounds(453, 138, 54, 15);
		contentPane.add(lb_cash);
		
		JLabel label_1 = new JLabel("可用余额：");
		label_1.setForeground(Color.RED);
		label_1.setBounds(347, 175, 72, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("可取现余额：");
		label_2.setForeground(Color.RED);
		label_2.setBounds(347, 216, 103, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("最小还款额：");
		label_3.setForeground(Color.RED);
		label_3.setBounds(347, 257, 103, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("0");
		label_4.setBounds(456, 175, 84, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("0");
		label_5.setBounds(456, 216, 84, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("0.00");
		label_6.setBounds(453, 257, 54, 15);
		contentPane.add(label_6);
		
		btn_save = new JButton("存款");
		btn_save.setBounds(742, 167, 93, 23);
		contentPane.add(btn_save);
		btn_save.addActionListener(this);
		
		btn_take = new JButton("取款");
		btn_take.setBounds(742, 370, 93, 23);
		contentPane.add(btn_take);
		btn_take.addActionListener(this);

		btn_transfers = new JButton("转账");
		btn_transfers.setBounds(742, 264, 93, 23);
		contentPane.add(btn_transfers);
		btn_transfers.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(742, 481, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		btn_history = new JButton("交易明细");
		btn_history.setBounds(10, 481, 93, 23);
		contentPane.add(btn_history);
		btn_history.addActionListener(this);
		
		JLabel label_7 = new JLabel("上期结欠金额：");
		label_7.setForeground(Color.RED);
		label_7.setBounds(347, 296, 103, 15);
		contentPane.add(label_7);
		
		lb_preCash = new JLabel("9,416.62");
		lb_preCash.setBounds(453, 296, 70, 15);
		contentPane.add(lb_preCash);
		
		TimerUtil.stopTimeCount();
		initData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		// TODO Auto-generated method stub
		if(btn == btn_back){
			UserMain userMain = new UserMain();
			userMain.setVisible(true);
			dispose();
		}else if(btn == btn_save){
            CardSaveView cardSaveView = new CardSaveView();
            cardSaveView.setVisible(true);
            dispose();
        }else if(btn == btn_take){
            CardTakeView cardTakeView = new CardTakeView();
            cardTakeView.setVisible(true);
            dispose();
        }else if(btn == btn_transfers){
            CardTransfersView cardTransfersView = new CardTransfersView();
            cardTransfersView.setVisible(true);
            dispose();
        }else if(btn == btn_history){
	        new CardDetailsView().setVisible(true);
			dispose();
        }
	}
}
