package com.we.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FloatLimitedKeyListener  extends KeyAdapter  {
	private String canKey = "0123456789.";
	private int dotLoc = -1;
	
//	@Override
//	public void keyPressed(KeyEvent e) {
//		int code=e.getKeyCode();
//		if(code==KeyEvent.VK_DELETE && ){
//			dotLoc = -1;
//		}
//		super.keyPressed(e);
//	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		boolean yes = false;
		for (int i = 0; i < canKey.length(); i++) {
			if( key == canKey.charAt(i)){
				if(key == '.'){
					if(dotLoc == -1){
						dotLoc = i;
					}else{
						break;
					}
				}
				yes = true;
				break;
			}
		}
		if(!yes){
			e.consume();
		}
	}

}
