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
import com.we.dao.TradeManager;

public class CardTakeView extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8589646050175082423L;
	private JPanel contentPane;
	private JTextField tf_num;
	private JButton btn_sure;
	private JButton btn_back;
	private JButton btn_300;
	private JButton btn_1000;
	private JButton btn_500;
	TradeManager tradeManager = TradeManager.getInstance();	
	CardManager dbManager = CardManager.getInstance();
	private JButton btn_100;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardTakeView frame = new CardTakeView();
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
	public CardTakeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_100 = new JButton("100");
		btn_100.setBounds(10, 103, 93, 23);
		contentPane.add(btn_100);
		btn_100.addActionListener(this);
		
		btn_500 = new JButton("500");
        btn_500.setBounds(727, 103, 93, 23);
        contentPane.add(btn_500);
        btn_500.addActionListener(this);
        
        btn_1000 = new JButton("1000");
        btn_1000.setBounds(727, 234, 93, 23);
        contentPane.add(btn_1000);
        btn_1000.addActionListener(this);
        
        btn_300 = new JButton("300");
        btn_300.setBounds(10, 234, 93, 23);
        contentPane.add(btn_300);
        btn_300.addActionListener(this);
        
        btn_back = new JButton("回主菜单");
        btn_back.setBounds(10, 350, 93, 23);
        contentPane.add(btn_back);
        btn_back.addActionListener(this);
        
        btn_sure = new JButton("确定");
        btn_sure.setBounds(727, 350, 93, 23);
        contentPane.add(btn_sure);
        btn_sure.addActionListener(this);
        
		JLabel label = new JLabel("仅支持面额为100的纸币");
		label.setBounds(330, 206, 171, 79);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("输入取款额");
		label_1.setBounds(350, 69, 121, 23);
		contentPane.add(label_1);
		
		tf_num = new JTextField();
		tf_num.setColumns(10);
		tf_num.setBounds(350, 120, 93, 22);
		contentPane.add(tf_num);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		int subNum = 0;
		if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else{
			if(btn == btn_300){
				subNum = 300;
			}else if(btn == btn_500){
				subNum = 500;
			}else if(btn == btn_1000){
				subNum = 1000;
			}else if(btn == btn_100){
				subNum = 100;
			}else if(btn == btn_sure){
			    subNum = Integer.parseInt(tf_num.getText());
			}
			if(dbManager.takeCash(subNum)){
				if(tradeManager.insertTrade(TradeManager.TRADE_TYPE_TAKE, subNum, TradeManager.TARGET_NULL)){
					new CardBusinessDone(getClass(), subNum).setVisible(true);
					dispose();
				}else{
			        System.err.println("取款失败");
			    }
			}else{
				System.err.println("取款失败");
			}
        }
	}
}
