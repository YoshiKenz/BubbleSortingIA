package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

import utils.Config;
import utils.MediaProvider;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	Button(String name, Color color) {
		super(name);
		int widthButton = (Config.SCREEN_WIDTH / 3) / 2;
		this.setMaximumSize(new Dimension(widthButton, Config.SCREEN_HEIGHT / 12));
		this.setFont(MediaProvider.get().Font());
		this.setForeground(color);
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setContentAreaFilled(false);
	}
}
