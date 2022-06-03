package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import utils.Config;
import utils.MediaProvider;


public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Button play;
	private Button quit;
	
	private static String textButtonPlay = "Gioca adesso";
	private static String textButtonQuit = "Ciau";

	public Menu(Window window) {
		
		this.setVisible(true);
		this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
		this.setLayout(new BorderLayout());

		play = new Button(textButtonPlay, new Color(255, 255, 255));
		quit = new Button(textButtonQuit, new Color(255, 255, 255));
		
		initLayoutPanels();
		buttonCommand(window);

	}

	private void initLayoutPanels() {

		JPanel [] panels = new JPanel[3];
		for(int i = 0; i < 3; i++) {
			panels[i] = new JPanel();
			panels[i].setPreferredSize(new Dimension(Config.SCREEN_WIDTH / 3, Config.SCREEN_HEIGHT));
			panels[i].setOpaque(false);
		}

		panels[1].setLayout(new BoxLayout(panels[1], BoxLayout.Y_AXIS));
		panels[1].add(Box.createRigidArea(new Dimension(Config.SCREEN_WIDTH / 3, Config.SCREEN_HEIGHT / 2)));
		
		panels[1].add(play);
		panels[1].add(Box.createRigidArea(new Dimension(Config.SCREEN_WIDTH / 16, Config.SCREEN_HEIGHT / 21)));
		panels[1].add(quit);

		this.add(panels[0], BorderLayout.EAST);
		this.add(panels[1], BorderLayout.CENTER);
		this.add(panels[2], BorderLayout.WEST);
	}

	private void buttonCommand(Window window) {
		
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.changeScreen(new GameControl(window));
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(MediaProvider.get().Menu(), 0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, null);
	}

}
