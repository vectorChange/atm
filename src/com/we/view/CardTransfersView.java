package com.we.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.we.UserMain;
import com.we.dao.CardManager;
import com.we.dao.TradeManager;
import com.we.util.FloatLimitedKeyListener;
import com.we.util.MainImagePane;
import com.we.util.MyButton;
import com.we.util.TextUtil;
import com.we.util.TimerUtil;

public class CardTransfersView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3477820948172292970L;
	private JPanel contentPane;
	private JTextField tf_cardNum;
	private MyButton btn_back;
	private MyButton btn_sure;
	private CardManager cardManager = CardManager.getInstance();
	private TradeManager tradeManager = TradeManager.getInstance();
	private MyButton btn_clear;
	private JTextField tf_num;
	private JLabel lb_error;
	private FloatLimitedKeyListener floatLimitedKeyListener;
	
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
		super("转账");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		MainImagePane mainImagePane = new MainImagePane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入转入卡号");
		lblNewLabel.setBounds(180, 160, 200, 20);
		mainImagePane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN,22));
		
		tf_cardNum = new JTextField();
		tf_cardNum.setFont(new Font("宋体", Font.PLAIN,22));
		tf_cardNum.setText("1002");
		tf_cardNum.setBounds(400, 157, 144, 25);
		mainImagePane.add(tf_cardNum);
		tf_cardNum.setColumns(10);
		tf_cardNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lb_error.setText("");
				lb_error.setVisible(false);
				// 使错误信息消失
			}
		});
		JLabel lblNewLabel_1 = new JLabel("小心诈骗，客服电话66666");
		lblNewLabel_1.setBounds(300, 306, 400, 73);
		mainImagePane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN,22));
		
        btn_back = new MyButton("res\\btn_back.png",10,400);
        btn_back.addActionListener(this);
		mainImagePane.add(btn_back);
		
        btn_sure = new MyButton("res\\btn_sure.png",720,400);
        btn_sure.addActionListener(this);
		mainImagePane.add(btn_sure);
		
		btn_clear = new MyButton("res\\btn_clear.png",720,250);
		btn_clear.addActionListener(this);
		mainImagePane.add(btn_clear);
		
		JLabel label = new JLabel("请输入转入金额");
		label.setBounds(180, 251, 200, 20);
		mainImagePane.add(label);
		label.setFont(new Font("宋体", Font.PLAIN,22));
		
		tf_num = new JTextField();
		tf_num.setText("1");
		tf_num.setColumns(10);
		tf_num.setBounds(400, 248, 144, 25);
		tf_num.addKeyListener(floatLimitedKeyListener = new FloatLimitedKeyListener());
		mainImagePane.add(tf_num);
		tf_num.setFont(new Font("宋体", Font.PLAIN,22));
		tf_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lb_error.setText("");
				lb_error.setVisible(false);
				// 使错误信息消失
			}
		});
		
		lb_error = new JLabel("错误提示");
		lb_error.setForeground(Color.RED);
		lb_error.setBounds(306, 401, 211, 127);
		lb_error.setVisible(false);
		lb_error.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_error);
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(Color.RED);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(395, 10, 54, 40);
		lb_rest_time.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_rest_time);
		
		TimerUtil.stopTimeCount();
		TimerUtil.timeCount(lb_rest_time,this, UserMain.class);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
		if(btn == btn_back){
			new UserMain().setVisible(true);
			dispose();
		}else if(btn==btn_sure){
			//卡号验证
			String targetCardNum = tf_cardNum.getText();
			if(targetCardNum.equals("")){
				TextUtil.setErrorTxt(lb_error,"卡号必填");
				return;
			}
			if(cardManager.getCardNum().equals(targetCardNum)){
				TextUtil.setErrorTxt(lb_error,"不能给自己转账");
				return;
			}
			
			//金额验证
			String verifyRes = TextUtil.verifyTransfersTextNum(tf_num.getText());
			if(!verifyRes.equals(TextUtil.TEXT_OK)){
				TextUtil.setErrorTxt(lb_error,verifyRes);
				return;
			}
			double tradeCash = Double.parseDouble(tf_num.getText());
			if(cardManager.transfersCash(targetCardNum,tradeCash)){
				int targetCardId = cardManager.getCardIdByCardNum(targetCardNum);
				if(tradeManager.insertTrade(TradeManager.TRADE_TYPE_TRANSFERS_OUT, tradeCash, targetCardId)){
//					System.out.println("插入转账记录成功");
				}else{
					JOptionPane.showMessageDialog(null, "转账成功，但添加交易记录失败", "转账异常", JOptionPane.WARNING_MESSAGE ); 
				}
				new CardBusinessDone(getClass(), tradeCash).setVisible(true);
			}else{
				TextUtil.setErrorTxt(lb_error,"转账失败");
			}
		}else if(btn==btn_clear){
			tf_cardNum.setText("");
			tf_num.setText("");
			tf_num.removeKeyListener(floatLimitedKeyListener);	//解除只能输入一次小数点，即使删除了内容再来的限制
			floatLimitedKeyListener = new FloatLimitedKeyListener();
			tf_num.addKeyListener(floatLimitedKeyListener);
		}
	}
}
