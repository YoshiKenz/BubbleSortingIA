package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.swing.JOptionPane;

import model.Ball;
import model.Container;
import utils.OptionPopup;

public class Game {

	private int nBalls;
	private int nContainers;
	private int level;
	private LinkedHashMap<String, Container> mapContainers;
	private HashMap<String, Integer> mapColourBallsCreated;
	
	public Game() {
		mapContainers = new LinkedHashMap<String, Container>();
		mapColourBallsCreated = new HashMap<>();
		readFromProperties();	
	}
	
	public int getnBalls() {
		return nBalls;
	}

	public int getnContainers() {
		return nContainers;
	}
	
	public LinkedHashMap<String, Container> getMapContainers() {
		return mapContainers;
	}

	public HashMap<String, Integer> getMapColourBallsCreated() {
		return mapColourBallsCreated;
	}

	private void readFromProperties() {
		InputStream input;
		try {
			input = new FileInputStream("other/easy.properties");
			Properties prop = new Properties();
	        prop.load(input);
			nBalls = Integer.valueOf(prop.getProperty("number.ball"));
			nContainers = Integer.valueOf(prop.getProperty("number.container"));
			createContainers();
			prop.forEach((k, v) -> {
				String[] splittedKey = ((String)k).split("\\.");
				if (splittedKey[0].equals("container")) {
					Container container = mapContainers.get(splittedKey[1]);
					String[] ballsFromProperties = ((String)v).split(",");
					fillContainer(container, ballsFromProperties);
				}
			});
			
			
		} catch (IOException e) {
			if (OptionPopup.error_popup("errore nel caricamento delle proprietà") == JOptionPane.OK_OPTION)
				System.exit(0);
			e.printStackTrace();
		}
	}
	
	private void createContainers() {
		Character c = new Character('a');
		for (int i = 0; i < nContainers; i++) {
			Container container = new Container(String.valueOf(c));
			mapContainers.put(container.getId(), container);
			++c;
		}
	}
	
	private void fillContainer(Container container, String[] ballsFromProperties) {
		Integer firstBall = 1;
		Integer nBallsCreated;
		for (String ballColour : ballsFromProperties) {
			Ball ball;
			if (mapColourBallsCreated.containsKey(ballColour) ) {
				nBallsCreated = mapColourBallsCreated.get(ballColour);
				nBallsCreated = nBallsCreated + 1;
				mapColourBallsCreated.put(ballColour, nBallsCreated);
				ball = new Ball(ballColour, nBallsCreated);
			} else {
				ball = new Ball(ballColour, firstBall);
				mapColourBallsCreated.put(ballColour, firstBall);
			}
			container.getListBalls().push(ball);
		}
		
	}

}
