
public class Obstacles extends Cell {

	public Obstacles(int positionRow,int positionCol) {
		super(positionRow,positionCol);
	}

	
	public boolean canMoveThrough() {
		return false;
	}
	public String toString(){
		return "O";
	}
	

}
