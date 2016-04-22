package com.we.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPane extends JPanel {
	private Image image;

	public BackgroundPane() {
		try {
//			image = ImageIO.read(new File("\\bg.png"));
			image = ImageIO.read(new File("res\\bg.png"));
			this.setOpaque(false);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public BackgroundPane(String path) {
		try {
			image = ImageIO.read(new File(path));
			this.setOpaque(false);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BackgroundPane());
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
