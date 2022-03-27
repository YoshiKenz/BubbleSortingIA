package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import gui.CommonConstants;

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
	private BufferedImage container;
	private HashMap<Color, Ellipse2D.Double> mapColoredBallCreated = new HashMap<>();
	
	
	private MediaProvider() {
		menu = loadImage("menu");
		win = loadImage("win");
		over = loadImage("gameOver");
		container = loadImage("contenitore");
		//container = loadImage("container");
	}
	
	private BufferedImage loadImage(String nameImage) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/" + nameImage + ".png"));
		} catch (IOException e) {
			if (OptionPopup.error_popup("errore nel caricamento delle immagini") == JOptionPane.OK_OPTION)
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
	
	public BufferedImage getContainer() {
		return container;
	}

	public void drawCircleColored(String colorString, Graphics2D graphics, int x, int y) {
		 Color color;
		 try {
		      Field field = Color.class.getField(colorString);
		      color = (Color)field.get(null);
		} catch (Exception e) {
		      color = null; 
		}
		graphics.setColor(color);
	    Ellipse2D.Double ball; 
		if (mapColoredBallCreated.containsKey(color)) {
			ball = mapColoredBallCreated.get(color);
			ball.x = x;
			ball.y = y;
			
		} else {
			ball = new Ellipse2D.Double(x, y, CommonConstants.BALL_WIDTH, CommonConstants.BALL_HEIGHT);
			mapColoredBallCreated.put(color, ball);
		}	
		graphics.fill(ball);

	}
	
	
}
