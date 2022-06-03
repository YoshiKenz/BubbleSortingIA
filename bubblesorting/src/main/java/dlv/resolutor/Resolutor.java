package dlv.resolutor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JOptionPane;

import dlv.facts.DLVBallInContainer;
import dlv.facts.DLVBallMoved;
import dlv.facts.DLVContainer;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import model.Ball;
import model.Container;
import utils.Config;
import utils.OptionPopup;

public class Resolutor extends AbstractResolutor {

	private final String encodingResource = Config.PATH_LOGIC_DISJ_PROGRAM;
	
	public Resolutor() {
		super();
	}
	
	// restituisce due stringhe, la pallina da spostare e il contenitore
	public List<String> solve(LinkedHashMap<String, Container> mapContainers, String ballMovedLast) {
		addFacts(mapContainers, ballMovedLast);
		encodeProgram(encodingResource);
		List<String> solution = new ArrayList<String>();
		AnswerSets answers = (AnswerSets) handler.startSync();
		
		try {
			for (AnswerSet a : answers.getAnswersets()) {
				
				for(String atom : a.getAnswerSet()) {
					if (atom.matches(Config.GUESS_ATOM + "(.*)")) {
						List<String> termsAtom = parseAtom(atom, Config.GUESS_ATOM);
						solution.add(termsAtom.get(0));
						solution.add(termsAtom.get(1));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (OptionPopup.error_popup("ANSWERSET") == JOptionPane.OK_OPTION)
				System.exit(0);
		}
		return solution;
	}
	
	private void addFacts(LinkedHashMap<String, Container> mapContainers, String ballMovedLast) {
		try {
			for (String key : mapContainers.keySet()) {
				
					facts.addObjectInput(new DLVContainer(key));
					int position = 1;
					Container container = mapContainers.get(key);
					for(Ball ball : container.getListBalls()) {
						facts.addObjectInput(new DLVBallInContainer(ball.getId(), key, position, ball.getColour()));
						if (ballMovedLast != null) {
							facts.addObjectInput(new DLVBallMoved(ballMovedLast));
						}
						position++;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (OptionPopup.error_popup("ADD FACTS") == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}
		handler.addProgram(facts);
		System.out.println(facts.getPrograms());
	}
	
	
}
