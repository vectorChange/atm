package com.we.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private Image backPic;
	private String path;

	public MyPanel(Image pic) {
		super();
		backPic = pic;
	}

	public MyPanel(File file) {
		super();
		try {
			backPic = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backPic, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MyPanel(new File(".\\res\\bg.jpg")));
		frame.setVisible(true);
	}
}
