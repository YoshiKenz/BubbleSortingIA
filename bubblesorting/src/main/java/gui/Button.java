package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import utils.Config;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	Button(String name, Color color) {
		super(name);
		int widthButton = (Config.SCREEN_WIDTH / 3) / 2;
		this.setMaximumSize(new Dimension(widthButton, Config.SCREEN_HEIGHT / 12));
		this.setFont(new Font("Elephant", Font.BOLD, 24));
		this.setForeground(color);
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setContentAreaFilled(false);
	}
}
