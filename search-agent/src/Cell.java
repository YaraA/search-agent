
public abstract class Cell {
	
	Position pos;
	

	public Cell(Position pos) {
		this.pos= pos;
	}

	public abstract boolean canMoveThrough();
	//overwritten in each of the Cell's subclasses
	//checks whether R2-D2 can pass through the cell or not

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	
	
	

}
