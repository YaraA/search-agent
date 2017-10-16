
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
	public HelpR2D2State clone(){
		return new HelpR2D2State(grid.clone());
	}


	@Override
	public String toString() {
		return grid.toString();
	}
}
