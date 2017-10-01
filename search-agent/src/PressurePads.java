
public class PressurePads extends Cell {
	
	boolean isActivated;
	//Cell rockOnTop;
	
	public PressurePads(Position pos) {
		super(pos);
		this.isActivated= false;			
	}

	public boolean canMoveThrough(){
		if(isActivated)
			return false;
		else
			return true; 
	}
	

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	public String toString(){
		return "P";
	}
	


}
