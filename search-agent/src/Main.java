import java.util.EnumSet;
import java.util.Random;

public class Main {
	final static int gridLimit = 6; //upper bound for m & n values of the grid
	
	public static void main (String[]args) throws Exception{
		Grid grid = Grid.createGridFromFile("Sol1");
		System.out.println(grid);
		Search(grid, Strategy.AS1, false);
	}

	/* genGrid() generates random numbers of grid dimensions and objects,
	 * then constructs the grid.
	 */
	public static Grid GenGrid(){
		/*
		 To generate random number between min(inclusive) and max(inclusive), use: 
		 random.nextInt((max - min) + 1) + min;
		 */

		Random random = new Random();

		/* 
		 * Generate grid dimensions (m x n)
		 * max = gridLimit, min = 3
		 */
		int m = random.nextInt(gridLimit-3) + 3;
		int n=  random.nextInt(gridLimit-3) + 3;

		/* 1. Reserve one place for the initial location of the agent and 
		 * one for the teleportal.
		 * 2. Adjust limit for the number of rocks/pads to a max of half
		 * of the remaining unoccupied cells each.
		 */
		int rocksLimit= ((m*n)/2 - 2);

		/*
		 * Generate random number for both rocks and pads 
		 * with a min of 0 each.
		 */
		int rocks = 0, pads = 0;
		if(rocksLimit > 0)
			rocks = pads = random.nextInt(rocksLimit);

		/* 1. Adjust the limit to the number of remaining unoccupied cells.
		 * 2. Generate a random number of obstacles with a min of 0.
		 */

		int obstaclesLimit = gridLimit - rocks - pads;
		int obstacles = 0;
		if(obstaclesLimit > 0)
			obstacles= random.nextInt(obstaclesLimit);

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
		System.out.println(problem.generalSearch(st));
	}
}
