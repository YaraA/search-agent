
public class Obstacles extends Cell {

	public Obstacles(Position pos) {
		super(pos);
	}

	
	public boolean canMoveThrough() {
		return false;
	}
	public String toString(){
		return "O";
	}
	

}
