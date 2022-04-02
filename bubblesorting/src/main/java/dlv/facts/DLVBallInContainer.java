package dlv.facts;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("pallinaNelContenitore")
public class DLVBallInContainer {
	
	@Param(0)
	private String idBall;
	@Param(1)
	private String idContainer;
	@Param(2)
	private int position;
	
	public DLVBallInContainer() {}
	
	public DLVBallInContainer(String idBall, String idContainer, int position) {
		this.idBall = idBall;
		this.idContainer = idContainer;
		this.position = position;
	}
	
	public String getIdBall() {
		return idBall;
	}
	public void setIdBall(String idBall) {
		this.idBall = idBall;
	}
	public String getIdContainer() {
		return idContainer;
	}
	public void setIdContainer(String idContainer) {
		this.idContainer = idContainer;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
