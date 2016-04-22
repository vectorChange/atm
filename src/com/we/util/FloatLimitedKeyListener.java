package com.we.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 只能输入正整数或者小数
 * @author xiaoguang
 */
public class FloatLimitedKeyListener  extends KeyAdapter  {
	private String canKey = "0123456789.";
	private String text = "";
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		
		boolean yes = false;
		for (int i = 0; i < canKey.length(); i++) {
			if( key == canKey.charAt(i)){
				if(key == '.'){
					if(text.indexOf(key) != -1){
						e.consume();
						return;
					}
				}
				yes = true;
			}
		}
		if((int)key == 8){
			if( text.length() >= 1){
				text = text.substring(0, text.length()-1);
			}
			return;
		}
		if(!yes){
			e.consume();
		}else if(yes){
			text += key;
		}
	}
}
