package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import core.Game;
import core.Updater;
import model.Ball;
import model.Container;
import utils.Config;
import utils.MediaProvider;
import utils.OptionPopup;

public class GameControl extends JPanel {
	
	
	private static final long serialVersionUID = 8430765621148804240L;
	
	private Window window;
	
	private Game game;
	
	private Updater updater;


	public GameControl(Window window) {
		this.window = window;
		this.game = new Game();
		this.updater = new Updater(this);
		this.setBackground(Color.white);
		addKeyboard();
		this.updater.start();
		
		
	}
	public Game getGame() {
		return game;
	}
	
	private void addKeyboard() {
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					updater.pauseGame();
					if (OptionPopup.esc_popup() == JOptionPane.YES_OPTION) {
						updater.killGame();
						window.changeScreen(new Menu(window));
					} else
						updater.resumeGame();
				}
			}

		});
		
	}
	
	public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        draw(graphic);
    }
	
	private void draw(Graphics graphic) {
		Graphics2D g2d = (Graphics2D)graphic;
		drawContainerAndBalls(g2d);
		
    }
	
	private void drawContainerAndBalls(Graphics2D g2d) {
		
		LinkedHashMap<String, Container> containers = this.game.getMapContainers();
		int startX = Config.SCREEN_WIDTH / 2 - (CommonConstants.CONTAINER_WIDTH * (CommonConstants.LIMIT_CONTAINER_IN_ROW/2 + 1));
		int startY = 0;
		if (this.game.getnContainers() <= CommonConstants.LIMIT_CONTAINER_IN_ROW) {
			startY = (Config.SCREEN_HEIGHT / (CommonConstants.LIMIT_CONTAINER_IN_ROW/2)) - (CommonConstants.CONTAINER_HEIGHT/2);
		} else {
			startY = (Config.SCREEN_HEIGHT / (CommonConstants.LIMIT_CONTAINER_IN_ROW/2)) - (CommonConstants.CONTAINER_HEIGHT * 2);
		}
		int currentX = startX;
		int currentY = startY;
		int contContainerInRow = 0;
		int changeRowAt = containers.size();
		if (containers.size() > CommonConstants.LIMIT_CONTAINER_IN_ROW) {
			changeRowAt = containers.size()/2 + 1;
		} 
		for (String key : containers.keySet()) {
			Container container = containers.get(key);
			g2d.drawImage(MediaProvider.get().getContainer(), currentX, currentY, this);
			drawBalls(g2d, container.getListBalls(), currentX, currentY);
			if (game.getContainerChoosedFrom() != null && key.equals(game.getContainerChoosedFrom())) {
				drawBallChoosed(g2d, this.game.getBallChoosed(), currentX, currentY);
			}
			contContainerInRow++;
			currentX = currentX + (CommonConstants.CONTAINER_WIDTH + (CommonConstants.CONTAINER_WIDTH/2));
			if (contContainerInRow >= changeRowAt) {
				currentY = currentY + (CommonConstants.CONTAINER_HEIGHT * 2);
				contContainerInRow = 0;
				currentX = startX + CommonConstants.CONTAINER_WIDTH - (CommonConstants.BALL_IN_CONTAINER_OFF_SET_X * (CommonConstants.LIMIT_CONTAINER_IN_ROW - (this.game.getnContainers() - changeRowAt)) );
			}
		}
		
	}
	
	private void drawBalls(Graphics2D g2d, LinkedList<Ball> balls, int xContainer, int yContainer) {
		int currentY = (yContainer + CommonConstants.CONTAINER_HEIGHT) - CommonConstants.BALL_IN_CONTAINER_OFF_SET_Y;
		for (Ball ball : balls) {
			currentY = currentY - (CommonConstants.BALL_HEIGHT);
			MediaProvider.get().drawCircleColored(ball.getColour(), g2d, xContainer + CommonConstants.BALL_IN_CONTAINER_OFF_SET_X, currentY);
		}
	}
	
	private void drawBallChoosed(Graphics2D g2d, Ball ball, int xContainer, int yContainer) {
		int currentY = yContainer - (CommonConstants.BALL_HEIGHT + 5);
		MediaProvider.get().drawCircleColored(ball.getColour(), g2d, xContainer + CommonConstants.BALL_IN_CONTAINER_OFF_SET_X, currentY);
	}
	
}