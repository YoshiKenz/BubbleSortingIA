package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class MediaProvider {

	private static MediaProvider instance = null; 
	
	public static MediaProvider get() {
		if(instance == null)
			instance = new MediaProvider(); 
		return instance;
	}
	
	private BufferedImage menu;
	private BufferedImage win;
	private BufferedImage over;
	
	private MediaProvider() {
		menu = loadImage("menu");
		win = loadImage("win");
		over = loadImage("gameOver");
	}
	
	private BufferedImage loadImage(String nameImage) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/" + nameImage + ".png"));
		} catch (IOException e) {
			if (OptionPopup.Error_popup("errore nel caricamento delle immagini") == JOptionPane.OK_OPTION)
				System.exit(0);
			e.printStackTrace();
		}
		return img;
	}
	
	public BufferedImage Menu() {
		return menu;
	}
	
	public BufferedImage Win() {
		return win;
	}
	
	public BufferedImage Over() {
		return over;
	}
	
}
