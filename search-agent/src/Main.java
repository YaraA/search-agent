import java.util.Random;

public class Main {
	final static int gridLimit = 20; //upper bound for m & n values of the grid
	public static void main (String[]args){
		
		System.out.println(genGrid().toString());
		
	}
	
	/* genGrid() generates random numbers of grid dimensions and objects,
	 * then constructs the grid.
	 */
	public static  Grid genGrid(){
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
		int rocks,pads;
		rocks = pads = random.nextInt(rocksLimit);
		
		/* 1. Adjust the limit to the number of remaining unoccupied cells.
		 * 2. Generate a random number of obstacles with a min of 0.
		 */
		int obstaclesLimit = gridLimit - rocks - pads;
		int obstacles= random.nextInt(obstaclesLimit);

		Grid grid= new Grid(m, n, rocks, pads, obstacles);
		return grid;
	}
	public Object Search(Grid grid, String strategy, boolean visualize){
		return null;
	}

}
