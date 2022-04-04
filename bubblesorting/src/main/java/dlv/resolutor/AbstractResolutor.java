package dlv.resolutor;

import java.util.ArrayList;
import java.util.List;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;

public abstract class AbstractResolutor {

	private final String service = "lib/dlv.mingw.exe";
	
	Handler handler;
	InputProgram facts;
	InputProgram encoding;
		
	
	public AbstractResolutor() {
		handler = new DesktopHandler(new DLVDesktopService(service));
		facts = new ASPInputProgram();
		encoding = new ASPInputProgram();
	}
	
	void encodeProgram(String encode) {
		encoding.addFilesPath(encode);
		handler.addProgram(encoding);
	}
	
	List<String> parseAtom(String atom, String type) {
		
		List<String> arguments = new ArrayList<>();
		String[] split = atom.split(type);
		split = split[1].substring(1, split[1].length()-1).split(",");
		
		for (String string : split) 
			arguments.add(string);
		
		return arguments;
	}
	
}
