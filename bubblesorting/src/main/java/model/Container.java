package model;

import java.util.LinkedList;

import gui.CommonConstants;
//63x173
public class Container {

	private String id;
	private LinkedList<Ball> listBalls;
	
	public Container(String id) {
		this.id = id;
		setListBalls(new LinkedList<Ball>());
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public LinkedList<Ball> getListBalls() {
		return listBalls;
	}


	public void setListBalls(LinkedList<Ball> listaPalline) {
		this.listBalls = listaPalline;
	}
	
	//metodo che restituisce true se il contenitore è completo e colorato
	public boolean getColoured() {
		String colourTemp = listBalls.get(0).getColour();
		int cont = 0;
		for(Ball ball : listBalls) {
			if(ball.getColour().equals(colourTemp)) {
				cont++;
			}	
		}
		if (cont == CommonConstants.NUM_BALLS_FOR_COLOUR) {
			return true;
		} else {
			return false;
		}
	}
	
}
