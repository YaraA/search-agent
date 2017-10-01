
public class Rocks extends Cell {

	public Rocks(Position pos) {
		super(pos);
	}
	
	public boolean canMoveThrough() {
		//TODO can R2-D2 pass through a cell containing a rock without pushing it or if it is already pushing another rock
		return true;
	}
	public String toString(){
		return "R";
	}
	

}
