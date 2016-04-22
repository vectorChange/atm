package com.we.util;

//import java.util.TimerTask;
import javax.swing.JLabel;

public class TextUtil {
	public static final String TEXT_OK = "TEXT_OK";	
	
	public static String verifyTextNum(String txt) {
		if(txt.equals("")){
			return "金额必填";
		}else if(Integer.parseInt(txt)==0){
			return "金额必须有效";
		}
		else if(Integer.parseInt(txt)%100!=0){
			return "金额必须为100的整数倍";
		}
		return TEXT_OK;
	}
	public static String verifyTransfersTextNum(String txt) {
		if(txt.equals("")){
			return "金额必填";
		}else if(0 == Double.valueOf(txt).compareTo(new Double("0.000"))){
			return "金额必须有效";
		}
		return TEXT_OK;
	}
	public static void setErrorTxt(JLabel lb_error,String msg) {
		lb_error.setText(msg);
		lb_error.setVisible(true);
	}
}
