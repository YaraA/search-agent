
public class State {
	
	Grid currGrid;
	int currPosRow;
	int currPosCol;

	public State(Grid currGrid, int currPosRow, int currPosCol) {
		this.currGrid= currGrid;
		this.currPosRow= currPosRow;
		this.currPosCol= currPosCol;
		
	}
	public Grid getCurrGrid() {
		return currGrid;
	}

	public int getCurrPosRow() {
		return currPosRow;
	}

	public int getCurrPosCol() {
		return currPosCol;
	}
}
