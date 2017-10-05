
public class Teleportal extends Cell {
	boolean isActivated;

	public Teleportal(Position pos) {
		super(pos);
		isActivated= false; 
	}
	
	public boolean canMoveThrough(){
			return true; 
	}
	public String toString(){
		return "T";
	}
	

}
