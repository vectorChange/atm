package com.we.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 集成图片背景和按钮效果的自定义按钮
 * @author xiaoguang
 */
@SuppressWarnings("serial")
public class MyButton extends JButton {
	private MyButton myButton;
	
	public MyButton(String url){
//		url = url.replace("res\\", "/");
//		url = url.replace("res\\", "/");
		ImageIcon icon = new ImageIcon(url);
		super.setIcon(icon);
		myButton = this;
		this.addMouseListener(new ImageMouseAdapter());
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
	
	public MyButton(String url,int locX,int locY){
//		url = url.replace("res\\", "/");
//		url = url.replace("\\", "/");
//		System.out.println(url);
		ImageIcon icon = new ImageIcon(url);
		super.setIcon(icon);
		myButton = this;
		this.addMouseListener(new ImageMouseAdapter());
		this.setBounds(locX, locY,icon.getIconWidth(), icon.getIconHeight());
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
	
	//图片点击效果
	private class ImageMouseAdapter extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			String newIcon = myButton.getIcon().toString().split("\\.")[0]+"_pressed.png";
			((JButton)e.getSource()).setIcon(new ImageIcon(newIcon));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			String newIcon = myButton.getIcon().toString().split("_pressed")[0]+".png";
			((JButton)e.getSource()).setIcon(new ImageIcon(newIcon));
		}
	}

}
