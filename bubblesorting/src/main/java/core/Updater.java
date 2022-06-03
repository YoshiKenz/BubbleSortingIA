package core;

import java.awt.Graphics;

import javax.swing.JPanel;

import gui.GameControl;
import utils.Config;
import utils.MediaProvider;

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
				if (play) {
					this.game.play();
					if (this.game.getBallChoosed() == null) {
						if (this.game.getWin() == true) {
							//abbiamo vinto
							this.gameControl.setGameWin();
							sleepFinal();
							System.exit(0);
						} else {
							//abbiamo perso
							this.gameControl.setGameOver();
							sleepFinal();
							System.exit(0);
						}
					} else {
						this.gameControl.repaint();
						System.out.println("*** AGGIORNO LA GRAFICA CON LA PALLINA SELEZIONATA");
						
						sleep();
						
						this.game.doMove();
						this.gameControl.repaint();
						System.out.println("*** AGGIORNO LA GRAFICA MUOVENDO LA PALLINA SELEZIONATA");
						
						sleep();
					}
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void sleepFinal() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
