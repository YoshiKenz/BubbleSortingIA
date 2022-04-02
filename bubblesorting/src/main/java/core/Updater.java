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
					//TODO
					//interrogo l' ia per capire la mossa migliore
					//aggiorno il gioco, pop() sul container che rimuove la pallina da spostare
					//repaint() del gameControl
					//aggiorno il gioco, aggiungo nel container la pallina spostata
					//repaint() del gameControl
					//ogni quanti secondi??
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
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
