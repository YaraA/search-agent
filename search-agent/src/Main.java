import java.util.EnumSet;
import java.util.Random;

public class Main {
	final static int gridLimit = 5; //upper bound for dimensions of the grid.

	public static void main (String[]args) throws Exception{
		/*
		 * Uncomment the following line to input the file name of a manual grid.
		 */
		//Grid grid = Grid.createGridFromFile("Sol2-DFS");
		/*
		 * Generate a random grid.
		 */
		Grid grid = GenGrid();
		System.out.println(grid);
		/*
		 * Execute search with a chosen strategy.
		 */
		Search(grid, Strategy.GR3, true);
	}

	/* genGrid() generates random numbers of grid dimensions and objects,
	 * then constructs the grid.
	 */
	public static Grid GenGrid(){
		/*
		 To generate a random number between min (inclusive) and max (exclusive), use: 
		 random.nextInt((max - min) + 1) + min;
		 */

		Random random = new Random();

		/* 
		 * Generate grid dimensions (m x n)
		 * max = gridLimit, min = 2
		 */
		int m = 0; int n= 0;
		int upper = (gridLimit - 2) + 1;
		m = (upper > 0)? random.nextInt(upper) + 2: 2;
		n = (upper > 0)? random.nextInt(upper) + 2: 2;

		/* Reserve one place for the initial agent location on a blank cell,
		 * and one for the teleportal.
		 */
		int unoccupiedCells = m*n -2;
		/* Adjust limit for the number of rocks/pads to a max of half
		 * of the remaining unoccupied cells each.
		 */
		int rocksLimit= (unoccupiedCells/2);
		/*
		 * Generate random number for both rocks and pads 
		 * with a min of 0 each.
		 */
		int rocks = 0, pads = 0;
		if(rocksLimit > 0)
			rocks = pads = random.nextInt(rocksLimit + 1);

		/* 1. Adjust the limit for the obstacles to the number of remaining unoccupied cells.
		 */
		unoccupiedCells -= (pads+rocks);
		int obstaclesLimit = unoccupiedCells;
		/*2. Generate a random number of obstacles with a min of 0.
		 */
		int obstacles = 0;
		if(obstaclesLimit > 0)
			obstacles= random.nextInt(obstaclesLimit + 1);

		Grid grid= new Grid(m, n, rocks, pads, obstacles);
		grid.fillGridRandomly();
		return grid;
	}
	public static void Search(Grid grid, Strategy st, boolean visualize) throws Exception{
		/*
		 * Create initial state.
		 */
		HelpR2D2State initialState = new HelpR2D2State(grid);
		/*
		 * Create a new instance of the problem.
		 */
		HelpR2D2 problem =  new HelpR2D2(EnumSet.allOf(Operator.class), initialState);
		/*
		 * Compute the search solution given the strategy and print it.
		 */
		SearchRes result = problem.generalSearch(st);
		System.out.println(result);
		/*
		 * If the visualize option is on, print every step of the solution (if any).
		 */
		if(visualize)
			result.visualize(problem);
	}
}
