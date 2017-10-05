
public abstract class Cell {
	
	private CellType type;
	

	public Cell(CellType type) {
		this.setType(type) ;
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public abstract boolean canMoveThrough();
	//overwritten in each of the Cell's subclasses
	//checks whether R2-D2 can pass through the cell or not

}
