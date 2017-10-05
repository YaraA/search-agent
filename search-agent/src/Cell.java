
public class Cell {
	
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
	public String toString(){
		/*
		 * Returns the first letter of the enum name.
		 */
		return type.toString().charAt(0) + "";
	}
}
