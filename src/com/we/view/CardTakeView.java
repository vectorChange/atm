package com.we.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import com.we.util.IntegerLimitedKeyListener;
import com.we.util.BackgroundPane;
import com.we.util.MyButton;
import com.we.util.TextUtil;
import com.we.util.TimerUtil;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class CardTakeView extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8589646050175082423L;

	private JPanel contentPane;
	private JTextField tf_num;
	private MyButton btn_sure;
	private MyButton btn_back;
	private MyButton btn_300;
	private MyButton btn_1000;
	private MyButton btn_500;
	private TradeManager tradeManager = TradeManager.getInstance();	
	private CardManager dbManager = CardManager.getInstance();
	private MyButton btn_100;
	private JLabel lb_error;
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
		setTitle("取款");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));	
		setContentPane(contentPane);
		BackgroundPane mainImagePane = new BackgroundPane();
		contentPane.add(mainImagePane);
		mainImagePane.setLayout(null);

        btn_100 = new MyButton("res\\btn_100.png",10,100);
        btn_100.addActionListener(this);
        mainImagePane.add(btn_100);

        btn_300 = new MyButton("res\\btn_300.png",10,250);
        btn_300.addActionListener(this);
        mainImagePane.add(btn_300);
        
        btn_500 = new MyButton("res\\btn_500.png",727,100);
        btn_500.addActionListener(this);
        mainImagePane.add(btn_500);
        
        btn_1000 = new MyButton("res\\btn_1000.png",727,250);
        btn_1000.addActionListener(this);
        mainImagePane.add(btn_1000);
        
        btn_back = new MyButton("res\\btn_back.png",10,400);
        btn_back.addActionListener(this);
		mainImagePane.add(btn_back);
		
        btn_sure = new MyButton("res\\btn_sure.png",727,400);
        btn_sure.addActionListener(this);
		mainImagePane.add(btn_sure);
		
		JLabel label = new JLabel("仅支持面额为100的纸币");
		label.setBounds(300, 206, 350, 79);
		mainImagePane.add(label);
		label.setFont(new Font("宋体", Font.PLAIN,22));
		
		JLabel label_1 = new JLabel("输入取款额");
		label_1.setBounds(350, 69, 121, 23);
		mainImagePane.add(label_1);
		label_1.setFont(new Font("宋体", Font.PLAIN,22));
		
		tf_num = new JTextField();
		tf_num.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tf_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lb_error.setText("");
				lb_error.setVisible(false);
				// 使错误信息消失
			}
		});
		tf_num.addKeyListener(new IntegerLimitedKeyListener());
		tf_num.setColumns(10);
		tf_num.setBounds(350, 120, 121, 22);
		tf_num.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(tf_num);
		
		lb_error = new JLabel("错误提示");
		lb_error.setForeground(Color.RED);
		lb_error.setBackground(Color.RED);
		lb_error.setBounds(350, 295, 252, 56);
		lb_error.setFont(new Font("宋体", Font.PLAIN,22));
		lb_error.setVisible(false);
		mainImagePane.add(lb_error);
		
		JLabel lb_rest_time = new JLabel("剩余");
		lb_rest_time.setForeground(Color.RED);
		lb_rest_time.setBackground(Color.WHITE);
		lb_rest_time.setBounds(375, 10, 54, 40);
		lb_rest_time.setFont(new Font("宋体", Font.PLAIN,22));
		mainImagePane.add(lb_rest_time);
		
		//计时
		TimerUtil.stopTimeCount();
		TimerUtil.timeCount(lb_rest_time,this, UserMain.class);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MyButton btn = (MyButton)e.getSource();
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
				String verifyRes = TextUtil.verifyTextNum(tf_num.getText());
				if(!verifyRes.equals(TextUtil.TEXT_OK)){
					TextUtil.setErrorTxt(lb_error,verifyRes);
					return;
				}
				subNum = Integer.parseInt(tf_num.getText());
			}
			//上限验证
			int verify = dbManager.takeCashVerify(subNum);
			switch (verify) {
			case CardManager.ONE_TIME_LIMIT:
				JOptionPane.showMessageDialog(null, "超过单次取款上限 "+CardManager.TAKE_LIMIT_TIME, "取款异常", JOptionPane.INFORMATION_MESSAGE); 
				return;
			case CardManager.ONE_DAY_LIMIT:
				JOptionPane.showMessageDialog(null,  "超过单日取款上限 "+CardManager.TAKE_LIMIT_DAY, "取款异常", JOptionPane.INFORMATION_MESSAGE); 
				return;
			case CardManager.LIMIT_OK:
			default:
				break;
			}
			
			if(dbManager.takeCash(subNum)){
				if(tradeManager.insertTrade(TradeManager.TRADE_TYPE_TAKE, subNum, TradeManager.TARGET_NULL)){
					new CardBusinessDone(getClass(), subNum).setVisible(true);
					dispose();
				}else{
			        JOptionPane.showMessageDialog(null, "取款成功，但添加操作纪录失败", "取款异常", JOptionPane.WARNING_MESSAGE); 
			    }
			}else{
				JOptionPane.showMessageDialog(null, "余额不足", "取款失败", JOptionPane.ERROR_MESSAGE); 
			}
        }
	}
}
