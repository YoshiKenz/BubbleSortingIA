package dlv.facts;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pallinaMossaPrecedentemente")
public class DLVBallMoved {
	
	@Param(0)
	private String id;
	
	public DLVBallMoved() {}
	
	public DLVBallMoved(String id) {
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
