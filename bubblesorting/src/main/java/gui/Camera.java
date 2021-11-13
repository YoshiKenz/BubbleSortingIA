package gui;

import javax.swing.JViewport;

public class Camera extends JViewport {
	
	private static final long serialVersionUID = 8430765621148804240L;
	
	private Window window;
	
	public Camera(Window window) {
		this.window = window;
	}
}
