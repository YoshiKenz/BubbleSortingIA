package core;

import gui.GameControl;

public class Updater extends Thread {
	private boolean running;
	private boolean play;

	private Game game;
	private GameControl gameControl;

	
	public Updater(GameControl gameControl) {
		this.gameControl = gameControl;
		this.game = gameControl.getGame();
	
		running = true;
		play = true;
	}
	
	@Override
	public void run() {
		while (running) {
			long now = System.nanoTime();
			
				if (play) {
					this.game.play();
					System.out.println("*** AGGIORNO LA GRAFICA");
					
					
					this.gameControl.repaint();
					
					sleep();
					
					this.game.doMove();
					this.gameControl.repaint();
					
					sleep();
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
	
	private void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
