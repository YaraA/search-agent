
public class HelpR2D2State extends State{
	private Grid grid;
	

	public HelpR2D2State(Grid currGrid) {
		this.setGrid(currGrid);
	}


	public Grid getGrid() {
		return grid;
	}


	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
