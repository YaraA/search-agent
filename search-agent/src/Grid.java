import java.util.Random;


public class Grid {
	private Cell[][] grid;
	private int m;
	private int n;
	private int padsCount;
	private int rocksCount;
	private int obstaclesCount;
	private Position agentLocation;
	private Position teleportalPosition;

	public Cell[][] getGrid() {
		return grid;
	}
	public Cell getCell(Position p){
		return grid[p.getX()][p.getY()];
	}
	public CellType getCellType(Position p){
		return getCell(p).getType();
	}
	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public int getPadsCount() {
		return padsCount;
	}

	public int getRocksCount() {
		return rocksCount;
	}

	public int getObstaclesCount() {
		return obstaclesCount;
	}
	public Position getAgentLocation() {
		return agentLocation;
	}

	public void setAgentLocation(Position agentLocation) {
		this.agentLocation = agentLocation;
	}

	public Position getTeleportalPosition() {
		return teleportalPosition;
	}

	public Grid(int m, int n, int pads, int rocks, int obstacles){
		/*
		 * Initialize instance variables.
		 */
		this.m = m;
		this.n = n;
		this.padsCount = pads;
		this.rocksCount = rocks;
		this.obstaclesCount = obstacles;
		this.grid = new Cell[m][n];

		/*
		 * Initialize the grid with BLANK cells.
		 */
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				grid[i][j] = new Cell(CellType.BLANK);
			}
		}

		/*
		 * Assign agent location to a random blank cell.
		 */
		Random random = new Random();
		int x = random.nextInt(m);
		int y = random.nextInt(n);
		agentLocation = new Position(x, y);

		/*
		 * Create and assign one teleportal cell.
		 */
		createCells(1, CellType.TELEPORTAL);
		/*
		 * Create and assign pressure pads, rocks, and obstacles cells.
		 */
		createCells(pads, CellType.PAD);
		createCells(rocks, CellType.ROCK); 
		createCells(obstacles, CellType.OBSTACLE);
	}
	public void createCells(int count, CellType type){
		Random random = new Random();
		for(int k=0; k < count; k++){
			/*
			 * Generate random indices of a blank cell
			 * that does NOT contain the agent.
			 */
			int i,j;
			do{
				i = random.nextInt(m);
				j = random.nextInt(n);
			}
			while(!(grid[i][j].getType() == CellType.BLANK) 
					|| agentLocation.equals(i, j));
			/*
			 * Change the type of the chosen blank cell to the needed type.
			 */
			grid[i][j].setType(type);
			/*
			 * Initialize location of teleportal if it is the created cell.
			 */
			if(type == CellType.TELEPORTAL)
				teleportalPosition = new Position(i, j);
		}
	}

	/* isActivated()
	 * returns true if all rocks are placed on pressure pads
	 * and hence the teleportal is activated.
	 */
	public boolean isActivated(){
		/*
		 * If all cells in the grid are NOT of type ROCK, then all rocks are on
		 * pressure pads (cells of type ROCKONPAD).
		 */
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j].getType() == CellType.ROCK)
					return false;
			}
		}
		return true;
	}
	public void moveRock(Position rockPos, Cell adjCell){
		/*
		 * 1. Update agent location after moving into the rock cell.
		 */
		this.agentLocation.set(rockPos);
		
	}
	public boolean liesInGrid(Position p){
		/*
		 * Check that the position (of a given cell) is in the valid range of the grid.
		 */
		if(p.getX()>=0 && p.getX() < this.m)
			if(p.getY()>=0 && p.getY() < this.n)
				return true;
		return false;
	}
	public static Position nextCell(Position curr, Operator op){
		int x = curr.getX(); int y = curr.getY();
		
		switch(op){
		case UP: y++; break;
		case DOWN: y--; break;
		case RIGHT: x++; break;
		case LEFT: x--; break;
		}
		
		return new Position(x,y);
	}
	public Grid clone(){
		/*
		 * Deep cloning of the cells in the grid array.
		 */
		/*
		 * 1. Create a new grid with the same counts of items.
		 */
		Grid newGrid = new Grid(this.m, this.n, this.padsCount, 
				this.rocksCount, this.obstaclesCount);
		/*
		 * 2. Clone the cells.
		 */
		Cell [][] gridArray = new Cell[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				gridArray[i][j] = this.grid[i][j].clone();
			}
		}
		newGrid.grid = gridArray;
		return newGrid;
	}
	public String toString(){
		/*
		 * Prints the grid in a nice visual format.
		 */
		String result = "";
		String seperator = "";
		for(int j = 0; j < n*6+1; j++)
			seperator += "-";
		result += "  " + seperator + "\n";
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				result += "  |  " + grid[i][j].toString();
			}
			result += "  |  " + "\n";
			result += "  " +seperator + "\n";
		}
		return result;
	}
}
