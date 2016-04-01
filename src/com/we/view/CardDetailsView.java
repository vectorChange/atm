package com.we.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.bean.TradeInfo;
import com.we.dao.CardManager;
import com.we.dao.TradeManager;

@SuppressWarnings("serial")
public class CardDetailsView extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	CardManager cardManager = CardManager.getInstance();
	private JButton btn_back;
	private JButton btn_exit;
	private JTable table;
	TradeManager tradeManager = TradeManager.getInstance();
	
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
		btn_exit.setBounds(29, 468, 93, 23);
		contentPane.add(btn_exit);
		btn_exit.addActionListener(this);
		
		btn_back = new JButton("回主菜单");
		btn_back.setBounds(767, 468, 93, 23);
		contentPane.add(btn_back);
		btn_back.addActionListener(this);
		
		JLabel label = new JLabel("历史交易明细");
		label.setBounds(383, 77, 121, 23);
		contentPane.add(label);
		
		
		ArrayList<TradeInfo>dataList = tradeManager.queryTradeInfos();
		String rowData[][] = new String[100][5];
		Object columnNames[] = { "序号", "交易时间", "交易类型", "交易金额","转账对象"};
		for (int i = 0; i < dataList.size(); i++) {
			rowData[i][0] = String.valueOf(i+1);
			rowData[i][1] = dataList.get(i).getTradeDate();
			rowData[i][2] = getShowType(dataList.get(i).getTradeType());
			rowData[i][3] = dataList.get(i).getTradeMoney()+" CNY";
			int targetId = dataList.get(i).getTarget();
			if(targetId != TradeManager.TARGET_NULL){
				rowData[i][4] = getShowTarget(targetId);
			}
		}
		JScrollPane scroll = new JScrollPane();  
		scroll.setLocation(143, 125);
        scroll.setSize(578, 366);  
		table = new JTable(rowData,columnNames);
		scroll.setViewportView(table);
		contentPane.add(scroll);
		table.setBounds(168, 161, 372, 1600);
		
//		dataList.
	}
	
	private String getShowTarget(int targetId) {
		return "用户"+targetId;
	}

	private String getShowType(int tradeType) {
		switch (tradeType) {
		case TradeManager.TRADE_TYPE_SAVE:
			return "存款";
		case TradeManager.TRADE_TYPE_TAKE:
			return "取款";
		case TradeManager.TRADE_TYPE_TRANSFERS_OUT:
			return "转出";
		case TradeManager.TRADE_TYPE_TRANSFERS_IN:
			return "转入";
		}
		return "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn == btn_exit){
			dispose();
		}else if(btn == btn_back){
			 new UserMain().setVisible(true);
	         dispose();
		}
	}
}
