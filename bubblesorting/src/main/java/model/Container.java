package model;

import java.util.LinkedList;
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
	
}
