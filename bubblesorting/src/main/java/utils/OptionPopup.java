package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author criso
 *	classe per visualizzare le finestre di dialogo 
 *	ogni funzione corrisponde a un popup 
 */

public class OptionPopup {

	private static String escMessage = "Are you sure you want to return to menu ?";
	private static String errorMessage = "Error with ";
	  
	public static int ESC_popup() {
		return JOptionPane.showConfirmDialog(new JFrame(),escMessage ,
				"Return to menu?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int Error_popup(String type) {
		return JOptionPane.showConfirmDialog(new JFrame(),errorMessage + type ,
				"ERROR!", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
	}
}
