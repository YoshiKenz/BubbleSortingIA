package dlv.facts;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pallina")
public class DLVBall {

	@Param(0)
	private String colour;
	@Param(1)
	private String id;

	public DLVBall() {}
	
	public DLVBall(String colore, String id) {
		super();
		this.colour = colore;
		this.id = id;
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
