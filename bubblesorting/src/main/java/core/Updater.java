package core;

import gui.GameControl;

public class Updater extends Thread {
	
	private long lastTime;
	private final double amountOfTicks;
	private double ns;
	private double delta;

	private boolean running;
	private boolean play;

	private Game game;
	private GameControl gameControl;
	
	public Updater(GameControl gameControl) {
		this.gameControl = gameControl;
		this.game = gameControl.getGame();
		amountOfTicks = 60.0;
		ns = 1000000000 / amountOfTicks;
		delta = 0;
		lastTime = System.nanoTime();
		running = true;
		play = true;
	}
	
	@Override
	public void run() {
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				if (play) {
					//game.update();
					//control.update();
					
				}
				delta--;
			}
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public boolean isPlay() {
		return play;
	}
	
	public void killGame() {
		running = false;
	}

	public void pauseGame() {
		play = false;
	}

	public void resumeGame() {
		play = true;
	}
}
