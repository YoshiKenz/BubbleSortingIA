package model;

public class Ball {

	
	private String colour;
	private String id;

	
	public Ball(String colore, int numPallina) {
		this.colour = colore;
		this.id = this.colour + "_" + numPallina;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colore) {
		this.colour = colore;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
