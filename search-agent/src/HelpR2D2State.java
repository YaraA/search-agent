
public class HelpR2D2State extends State{
	Grid currGrid;
	

	public HelpR2D2State(Grid currGrid) {
		this.currGrid= currGrid;
		
		
	}
	public Grid getCurrGrid() {
		return currGrid;
	}
	public void setCurrGrid(Grid currGrid) {
		this.currGrid = currGrid;
	}

}
