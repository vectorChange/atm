package com.we.util;

import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimerUtil {
	private static final int OP_TIME_LIMIT = 60;
	private static final long DELAY_INTERVAL = 1000;
	public static TimerTask task ;
	
	/**
	 * 暂停计时
	 */
	public static void stopTimeCount(){
		if(task != null){
			task.cancel();
		}
	}
	
	/**
	 * 自动更新JLabel数字
	 * @param label_time
	 */
	public static void timeCount(final JLabel label_time) {
		task = new TimerTask() {
			int cnt = OP_TIME_LIMIT;
			@Override
			public void run() {
				cnt--;
				label_time.setText(cnt+"");
				if(cnt==0){
					cancel();
				}
			}
		};
		new java.util.Timer(false).schedule(task, 0, DELAY_INTERVAL);
	}
	
	/**
	 * 自动更新JLabel数字，倒计时结束后结束窗体
	 * @param label_time
	 * @param from
	 */
	public static void timeCount(final JLabel label_time,final JFrame from) {
		 task = new TimerTask() {
			int cnt = OP_TIME_LIMIT;
			@Override
			public void run() {
				cnt--;
				label_time.setText(cnt+"");
				if(cnt==0){
					cancel();
					from.dispose();
				}
			}
		};
		new java.util.Timer(false).schedule(task, 0, DELAY_INTERVAL);
	}
	/**
	 * 自动更新JLabel数字，倒计时结束后结束当前窗体，启动新窗体
	 * @param label_time
	 * @param from
	 * @param toFrameClass
	 */
	public static void timeCount(final JLabel label_time,final JFrame from,final  Class<?extends JFrame> toFrameClass) {
		task = new TimerTask() {
			int cnt = OP_TIME_LIMIT;
			@Override
			public void run() {
				cnt--;
				label_time.setText(cnt+"");
				if(cnt==0){
					cancel();
					from.dispose();
					try {
						toFrameClass.newInstance().setVisible(true);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new java.util.Timer(false).schedule(task, 0, DELAY_INTERVAL);
	}
	
}
