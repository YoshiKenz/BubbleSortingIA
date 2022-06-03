package gui;
import java.awt.Component;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	public Window() {
		initFrame();
	}

	private void initFrame() {
		this.add(new Menu(this));
		//TODO commentato per fase di sviluppo
		setFullScreen();
		this.setVisible(true);
		this.pack();
		//al click quit della finestra chiude il processo
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	System.exit(0);   
		    }
		});
	}

	public void changeScreen(Component component) {
		this.getContentPane().removeAll();
		this.getContentPane().add(component);
		component.requestFocus();
		this.revalidate();
		this.repaint();
	}

	public void setFullScreen() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}