package com.we.admin.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import com.we.bean.TradeInfo;
import com.we.dao.CardManager;
import com.we.dao.CardUserManager;
import com.we.dao.TradeManager;
import com.we.util.IntegerLimitedKeyListener;
import com.we.view.CardDetailsView;

@SuppressWarnings("serial")
public class PanelRec extends JPanel implements ActionListener {

	public static final String TAG = "rec";

	private JTextField tf_acc;
	private JTable table;
	private JXDatePicker dp_start;
	private JXDatePicker dp_end;
	private JLabel lb_acc;
	private JLabel lb_start;
	private JLabel lb_end;
	private JScrollPane scroll_panel;
	private JButton btn_query;
	private Font font_content = new Font("幼圆", Font.BOLD, 14);
	
	private String[] tableHeader = { "序号", "交易时间", "交易类型", "交易金额", "转账对象" };

	/**
	 * Create the panel.
	 */
	public PanelRec() {
		setLayout(null);
		newComponent();
		setPosition();
	}

	private void newComponent() {
		lb_acc = new JLabel("卡号");
		lb_acc.setFont(font_content);
		tf_acc = new JTextField();
		tf_acc.setFont(new Font("宋体", Font.PLAIN, 18));
		dp_start = new JXDatePicker();
		dp_start.getEditor().setFont(new Font("宋体", Font.PLAIN, 18));
		dp_start.setFont(new Font("宋体", Font.PLAIN, 18));
		scroll_panel = new JScrollPane();
		table = new JTable(new DefaultTableModel(null, tableHeader));
		table.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btn_query = new JButton("查询");
		lb_start = new JLabel("开始");
		lb_start.setFont(font_content);
		lb_end = new JLabel("结束");
		lb_end.setFont(font_content);
		dp_end = new JXDatePicker();
		dp_end.getEditor().setFont(new Font("宋体", Font.PLAIN, 18));
		dp_end.setFont(new Font("宋体", Font.PLAIN, 18));
	}

	private void setPosition() {
		lb_acc.setBounds(20, 28, 46, 15);
		add(lb_acc);
		tf_acc.setBounds(56, 20, 170, 30);
		tf_acc.addKeyListener(new IntegerLimitedKeyListener());
		add(tf_acc);
		dp_start.getEditor().setLocation(0, 16);
		dp_start.setBounds(308, 20, 130, 30);
		add(dp_start);
		lb_start.setBounds(263, 28, 40, 15);
		add(lb_start);
		lb_end.setBounds(477, 28, 40, 15);
		add(lb_end);
		dp_end.setBounds(519, 20, 130, 30);
		add(dp_end);
		scroll_panel.setBounds(20, 67, 629, 319);
		add(scroll_panel);
		scroll_panel.setViewportView(table);
		btn_query.setBounds(559, 396, 90, 30);
		add(btn_query);
		dp_start.setFormats("yyyy.MM.dd");
		dp_end.setFormats("yyyy.MM.dd");
		btn_query.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		table.setModel(new DefaultTableModel(null, tableHeader));
		if (!tf_acc.getText().equals("")) { // 查询账号不允许为空
			CardManager cardManager = CardManager.getInstance();
			if ( cardManager.getCardIdByCardNum(tf_acc.getText()) == CardManager.NO_EXIT ) {
				JOptionPane.showMessageDialog(this, "用户不存在");
			}else {
				refreshTable();
			}
		}else {
			JOptionPane.showMessageDialog(this, "账号不为为空");
		}
	}

	private void refreshTable() {
		CardManager cardManager = CardManager.getInstance();
		TradeManager tradeManager = TradeManager.getInstance();
		CardUserManager cardUserManager = CardUserManager.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = sdf.format(dp_start.getDate());
		String end = sdf.format(dp_end.getDate());
		int cardId = cardManager.getCardIdByCardNum(tf_acc.getText());
		ArrayList<TradeInfo> arrayList = tradeManager.queryRecentTradeInfos(
				end, start, cardId);
		String[][] rowDatas = new String[arrayList.size()][5];
		for (int i = 0; i < rowDatas.length; i++) {
			rowDatas[i][0] = String.valueOf(i + 1);
			rowDatas[i][1] = arrayList.get(i).getTradeDate();
			rowDatas[i][2] = CardDetailsView.getShowType(arrayList.get(i)
					.getTradeType());
			rowDatas[i][3] = arrayList.get(i).getTradeMoney() + " CNY";
			int targetId = arrayList.get(i).getTarget();
			if (targetId != TradeManager.TARGET_NULL) {
				rowDatas[i][4] = cardUserManager.getUserNameByCardId(targetId);
			}
		}
		table.setModel(new DefaultTableModel(rowDatas, tableHeader));
	}

}
