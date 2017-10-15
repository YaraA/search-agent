import java.util.ArrayList;


public abstract class Heuristics {

	@SuppressWarnings("incomplete-switch")
	public static int applyHeuristic(Strategy s, Node n){
		/*
		 * Returns the estimated cost to the goal calculated by 
		 * the corresponding heuristic function of the given search strategy.
		 */
		switch(s){
		case GR1: 
		case AS1: return heuristic1(n);
		case GR2: 
		case AS2: return heuristic2(n);
		}
		return 0;
	}

	private static int heuristic1(Node node) {
		/*
		 * The estimated cost to the goal of a given state 
		 * is the summation of the city-block distances
		 * from each rock to its nearest pad.
		 */
		Grid grid = ((HelpR2D2State) node.getState()).getGrid();
		ArrayList<Position> rockPositions = grid.getRocksPositions();
		ArrayList<Position> padsPositions = grid.getPadsPositions();
		int m = grid.getM();
		int n = grid.getN();
		int result = 0;
		/*
		 * For every rock, calculates the city-block distance to its nearest pad.
		 */
		for(Position rock : rockPositions){
			int disToNearestPad = m + n ; //the maximum possible distance
			
			for(Position pad : padsPositions){
				int currDist = rock.cityBlockDistanceTo(pad);
				disToNearestPad = Math.min(disToNearestPad, currDist);
			}
			
			result += disToNearestPad;
		}

		return result;
	}

	private static int heuristic2(Node n) {
		return 0;
	}
}
