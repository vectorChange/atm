package com.we.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerLimitedKeyListener extends KeyAdapter  {
	private String canKey = "0123456789";
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		boolean yes = false;
		for (int i = 0; i < canKey.length(); i++) {
			if( key == canKey.charAt(i)){
				yes = true;
				return;
			}
		}
		if(!yes){
			e.consume();
		}
	}
}
