package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.swing.JOptionPane;

import dlv.resolutor.Resolutor;
import model.Ball;
import model.Container;
import utils.OptionPopup;

public class Game {

	private int nBalls;
	private int nContainers;
	private LinkedHashMap<String, Container> mapContainers;
	private HashMap<String, Integer> mapColourBallsCreated;
	private String containerChoosedFrom;
	private String containerChoosedTo; 
	private Ball ballChoosed;
	private String ballMovedLast;
	private int numMosse;

	
	
	
	public Game() {
		mapContainers = new LinkedHashMap<String, Container>();
		mapColourBallsCreated = new HashMap<>();
		readFromProperties();
		//test code
		if (containerChoosedFrom != null) {
			ballChoosed = mapContainers.get(containerChoosedFrom).getListBalls().pop();
		}
		numMosse = 0;
	}
	
	public int getNumMosse() {
		return numMosse;
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

	public String getContainerChoosedFrom() {
		return containerChoosedFrom;
	}

	public Ball getBallChoosed() {
		return ballChoosed;
	}
	
	public String getContainerChoosedTo() {
		return containerChoosedTo;
	}
	

	public void play() {
		//interrogo l' ia, faccio il pop sul container della pallina scelta e la setto nei campi choosed
		Resolutor resolutor = new Resolutor();
		
		List<String> solution = resolutor.solve(mapContainers, ballMovedLast);
		
		
		processSolution(solution);
	}
	
	public boolean getWin() {
		Set<String> keys = mapContainers.keySet();
        
		for (String key : keys) {
            Container container = mapContainers.get(key);
            if(!container.getListBalls().isEmpty() && !container.getColoured()) {
            	return false;
            }
        }
        return true;
	}
	
	public void doMove() {
		//inserisco la pallina nel contenitore scelto dall' ia
		
		mapContainers.get(containerChoosedTo).getListBalls().add(ballChoosed);
		System.out.println(mapContainers.get("e").getListBalls());
		ballMovedLast = ballChoosed.getId();
		ballChoosed = null;
		containerChoosedFrom = null;
		containerChoosedTo = null;
		numMosse++;
		System.out.println("Numero Mosse :" + numMosse);
	}
	
	
	
	private void processSolution(List<String> solution) {
		if (!solution.isEmpty()) {
			String ballChoosedId = solution.get(0).replace("\"", "");
			containerChoosedTo = solution.get(1).replace("\"", "");
			
			for (String key : mapContainers.keySet()) {
				
				LinkedList<Ball> listBalls = mapContainers.get(key).getListBalls();
				
				if (!(listBalls.isEmpty()) && ballChoosedId.equals(listBalls.peekLast().getId())) {
					containerChoosedFrom = key;
					System.out.println("VOGLIO MUOVERE LA PALLINA DAL CONTENITORE " + containerChoosedFrom + " AL " + containerChoosedTo);
	
					ballChoosed = listBalls.removeLast();
					break;
				}
				
				/*for (Ball ball : mapContainers.get(key).getListBalls()) {
					if (ball.getId().equals(ballChoosedId)) {
						ballChoosed = ball;
						containerChoosedFrom = key;
						mapContainers.get(containerChoosedFrom).getListBalls().pop();
					}
				}*/
			}
		}
		
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
			if (validateContainers() == false) {
				erroreProprieta();
			};
			
		} catch (IOException e) {
			erroreProprieta();
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
			container.getListBalls().add(ball);
		}
		
	}
	
	private boolean validateContainers() {
		boolean validate = true;
		int nStart = 0;
		int i = 0;
		for(Entry<String, Integer> entry: mapColourBallsCreated.entrySet()) {
			if (i == 0) {
				nStart = entry.getValue();
			} else if (nStart != entry.getValue()) {
				validate = false;
				break;
			}
			i++;
	    }
		
		return validate;
	}
	
	private void erroreProprieta() {
		if (OptionPopup.error_popup("errore nel caricamento delle proprietÓ") == JOptionPane.OK_OPTION)
			System.exit(0);
	}

}
