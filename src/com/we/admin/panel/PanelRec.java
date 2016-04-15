package com.we.admin.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXDatePicker;

import com.mysql.fabric.xmlrpc.base.Array;
import com.we.admin.MyTableModle;
import com.we.bean.TradeInfo;
import com.we.dao.CardManager;
import com.we.dao.TradeManager;
import com.we.dao.UserManager;
import com.we.util.DateUtil;
import com.we.view.CardDetailsView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class PanelRec extends JPanel {

	public static final String TAG = "rec";

	private JTextField tf_acc;
	private JTable table;
	JXDatePicker dp_start;
	JXDatePicker dp_end;

	/**
	 * Create the panel.
	 */
	public PanelRec() {
		setLayout(null);

		JLabel lb_acc = new JLabel("卡号：");
		lb_acc.setBounds(10, 20, 46, 15);
		add(lb_acc);

		tf_acc = new JTextField();
		tf_acc.setBounds(46, 17, 129, 21);
		add(tf_acc);
		tf_acc.setColumns(10);

		dp_start = new JXDatePicker();
		dp_start.getEditor().setLocation(0, 16);
		dp_start.setBounds(239, 16, 129, 23);
		dp_start.setFormats("yyyy.MM.dd");
		add(dp_start);

		JLabel lb_start = new JLabel("开始：");
		lb_start.setBounds(200, 20, 54, 15);
		add(lb_start);

		JLabel lb_end = new JLabel("结束：");
		lb_end.setBounds(391, 20, 54, 15);
		add(lb_end);

		dp_end = new JXDatePicker();
		dp_end.setFormats("yyyy.MM.dd");
		dp_end.setBounds(431, 16, 129, 23);

		add(dp_end);

		JScrollPane scroll_panel = new JScrollPane();
		scroll_panel.setBounds(10, 50, 550, 280);
		add(scroll_panel);

		table = new JTable();
		scroll_panel.setViewportView(table);

		JButton btn_query = new JButton("查询");
		btn_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tf_acc.getText().equals("")) { // 查询账号不允许为空
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String start = sdf.format(dp_start.getDate());
					String end = sdf.format(dp_end.getDate());
					CardManager cardManager = CardManager.getInstance();
					TradeManager tradeManager = TradeManager.getInstance();
					UserManager userManager = UserManager.getInstance();
					int cardId = cardManager.getCardIdByCardNum(tf_acc
							.getText());
					ArrayList<TradeInfo> arrayList = tradeManager
							.queryRecentTradeInfos(end, start, cardId);
					String[][] rowDatas = new String[arrayList.size()][5];
					for (int i = 0; i < rowDatas.length; i++) {
						rowDatas[i][0] = String.valueOf(i+1);
						rowDatas[i][1] = arrayList.get(i).getTradeDate();
						rowDatas[i][2] = CardDetailsView.getShowType(arrayList.get(i).getTradeType());
						rowDatas[i][3] = arrayList.get(i).getTradeMoney()+" CNY";
						int targetId = arrayList.get(i).getTarget();
						if(targetId != TradeManager.TARGET_NULL){
							rowDatas[i][4] = userManager.getUserNameByCardId(targetId);
						}
					}
					table.setModel(new MyTableModle(rowDatas, rowDatas.length, 5));
				}
			}
		});
		btn_query.setBounds(467, 352, 93, 23);
		add(btn_query);

	}
}
