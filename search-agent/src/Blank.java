
public class Blank extends Cell {

	public Blank(Position pos) {
		super(pos);
	}
	
	public boolean canMoveThrough(){
		return true; 
	}
	public String toString(){
		return "B"; 
	}

}
