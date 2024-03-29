import java.io.FileReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import java.util.Scanner;

public class Grid {
	private Cell[][] grid;
	private int m; //height
	private int n;	//width
	private int padsCount;
	private int rocksCount;
	private int obstaclesCount;
	private Position agentPosition;
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
	public Position getAgentPosition() {
		return agentPosition;
	}

	public void setAgentPosition(Position agentLocation) {
		this.agentPosition = agentLocation;
	}

	public Position getTeleportalPosition() {
		return teleportalPosition;
	}
	public Grid(int m, int n){
		/*
		 * Initialize the grid with only the known dimensions.
		 * Used with the manually generated grids 
		 */
		this.m = m;
		this.n = n;
		this.grid = new Cell[m][n];
	}
	public Grid(int m, int n, int pads, int rocks, int obstacles){
		/*
		 * Initialize instance variables.
		 */
		this(m,n);
		this.padsCount = pads;
		this.rocksCount = rocks;
		this.obstaclesCount = obstacles;
		/*
		 * Initialize the grid with BLANK cells.
		 */
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				grid[i][j] = new Cell(CellType.BLANK);
			}
		}
	}
	public void fillGridRandomly(){
		/*
		 * Assign agent location to a random blank cell.
		 */
		Random random = new Random();
		int x = random.nextInt(m);
		int y = random.nextInt(n);
		agentPosition = new Position(x, y);

		/*
		 * Create and assign one teleportal cell.
		 */
		createCells(1, CellType.TELEPORTAL);
		/*
		 * Create and assign pressure pads, rocks, and obstacles cells.
		 */
		createCells(padsCount, CellType.PAD);
		createCells(rocksCount, CellType.ROCK); 
		createCells(obstaclesCount, CellType.OBSTACLE);
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
					|| agentPosition.equals(i, j));
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

	/* isActivated:
	 * Returns true if all rocks are placed on pressure pads
	 * and hence the teleportal is activated.
	 */
	public boolean isActivated(){
		/*
		 * If all cells in the grid are NOT of type ROCK, then all rocks are on
		 * pressure pads (instead they are cells of type ROCKONPAD).
		 */
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j].getType() == CellType.ROCK)
					return false;
			}
		}
		return true;
	}

	/*moveRock:
	 * Handles the case where the agent pushes a rock to a valid position,
	 * by updating the position of the agent and the rock,
	 * along with the types of the affected cells (if any).
	 */
	public void moveRock(Position rockCell, Position adjCell) throws Exception{
		/*
		 * 1. Update agent location after moving into the rock cell.
		 */
		this.agentPosition.set(rockCell);
		/*
		 * 2. Update rockCell new type.
		 */
		CellType rockCellType = getCellType(rockCell);
		CellType newRockCellType;
		switch(rockCellType){
		case ROCK: newRockCellType = CellType.BLANK; break;
		case ROCKONPAD: newRockCellType = CellType.PAD; break;
		case ROCKONTELEPORTAL: newRockCellType = CellType.TELEPORTAL; break;
		default: throw new Exception("Tried to move rock from an invalid cell.");
		}
		getCell(rockCell).setType(newRockCellType);
		/*
		 * 3. Update adjCell new type.
		 */
		CellType adjCellType= getCellType(adjCell);
		CellType newAdjCellType;
		switch(adjCellType){
		case BLANK: newAdjCellType = CellType.ROCK; break;
		case PAD: newAdjCellType = CellType.ROCKONPAD; break;
		case TELEPORTAL: newAdjCellType = CellType.ROCKONTELEPORTAL; break;
		default: throw new Exception("Tried to move rock to an invalid cell.");
		}
		getCell(adjCell).setType(newAdjCellType);

	}
	public boolean liesInGrid(Position p){
		/*
		 * Check that the position (of a given cell) is in the valid range of the grid.
		 */
		if(p.getX() >= 0 && p.getX() < this.m)
			if(p.getY() >=  0 && p.getY() < this.n)
				return true;
		return false;
	}
	public static Position nextCell(Position curr, Operator op){
		int x = curr.getX(); int y = curr.getY();
		/*
		 * x represents the row number.
		 * y represents the column number.
		 */
		switch(op){
		case UP: x--; break;
		case DOWN: x++; break;
		case RIGHT: y++; break;
		case LEFT: y--; break;
		}

		return new Position(x,y);
	}
	public void addCell(int i, int j, CellType t){
		/*
		 * Create a cell in position (i,j) of type t,
		 * and increment count of its type (if needed).
		 */
		this.grid[i][j] = new Cell(t);
		switch(t){
		case ROCK: rocksCount++; break;
		case PAD: padsCount++; break;
		case OBSTACLE: obstaclesCount++; break;
		default:
			break;
		}
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
		/*
		 * 3. Clone teleportal and agent positions.
		 */
		newGrid.agentPosition = this.agentPosition.clone();
		newGrid.teleportalPosition = this.teleportalPosition.clone();
		return newGrid;
	}
	public boolean equals(Grid g)
	{
		/*
		 * Deep Comparison
		 * Compares the rock positions and the agent position.
		 */
		if (!this.getAgentPosition().equals(g.getAgentPosition()))
			return false;
		ArrayList<Position> myRockPosition = this.getRocksPositions();
		ArrayList<Position> gRockPosition = g.getRocksPositions();
		for (int i = 0; i < rocksCount; i++)
			if (!myRockPosition.get(i).equals(gRockPosition.get(i)))
				return false;
		return true;
	}
	public ArrayList<Position> getPositionsofTypes(EnumSet<CellType> types){
		/*
		 * Returns a list of the positions of cells of the given type in the grid.
		 */
		ArrayList<Position> positions = new ArrayList<Position>(this.rocksCount);
		for(int i = 0; i < getM(); i++){
			for(int j = 0; j < getN(); j++){
				if(types.contains(this.grid[i][j].getType())){
					positions.add(new Position(i,j));
				}
			}
		}
		return positions;
	}
	public ArrayList<Position> getRocksPositions(){
		/*
		 * Returns a list of the positions of ROCKS in the grid.
		 */
		ArrayList<Position> rocksPositions = this.getPositionsofTypes(CellType.getRockTypes());
		return rocksPositions;
	}
	public ArrayList<Position> getPadsPositions(){
		/*
		 * Returns a list of the positions of PADS in the grid.
		 */
		ArrayList<Position> padsPositions = this.getPositionsofTypes(CellType.getPadTypes());
		return padsPositions;
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
				String cell= grid[i][j].toString();
				if(agentPosition.equals(i, j)){
					cell = "A";
				}
				result += "  |  " + cell;
			}
			result += "  |  " + "\n";
			result += "  " +seperator + "\n";
		}
		return result;
	}
	public static Grid createGridFromFile(String fileName) throws Exception{
		/*
		 * Expects a grid in file like the following format:
		 	5 3
			O B B
			P B B
			B O R
			A B B
			B B T
		 */
		/* 
		 * Set up the needed tools to parse the text file.
		 */
		FileReader in = new FileReader("grid-tests/" + fileName);
		Scanner sc = new Scanner(in);
		/*
		 * Get grid dimensions and create a new grid instance.
		 */
		int m = sc.nextInt(); int n = sc.nextInt();
		Grid g = new Grid(m, n);

		/*
		 * Parse the file and set cell types accordingly.
		 */
		try{
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					String s = sc.next();						
					CellType type = CellType.getType(s);
					/*
					 * Handle special cases of telep. and agent.
					 */
					if(s.equals("A")){
						g.agentPosition = new Position(i, j);
						type = CellType.BLANK;
					}
					if(type == CellType.TELEPORTAL)
						g.teleportalPosition = new Position(i, j);
					/*
					 * Add the cell to the grid in proper position.
					 */
					g.addCell(i, j, type);
				}
			}
			sc.close();
		}
		catch (Exception e){
			throw new Exception("Grid format in file is not correct.");
		}
		return g;
	}
}
