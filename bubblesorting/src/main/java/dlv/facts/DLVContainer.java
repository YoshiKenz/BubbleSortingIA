package dlv.facts;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("contenitore")
public class DLVContainer {
	
	@Param(0)
	private String id;
	
	public DLVContainer() {}
	
	public DLVContainer(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
