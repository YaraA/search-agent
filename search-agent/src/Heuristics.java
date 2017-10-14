import java.util.ArrayList;


public abstract class Heuristics {

	public int applyHeuristic(Strategy s, Node n){
		/*
		 * calls heuristic function according to given strategy
		 */
		switch(s){
		case GR1: 
		case AS1: return heuristic1(n);
		case GR2: 
		case AS2: return heuristic2(n);
		}
		return 0;
	}

	private int heuristic1(Node node) {
		HelpR2D2State currState = ((HelpR2D2State) node.getState());
		Grid grid = currState.getGrid();
		ArrayList<Position> rockPositions = grid.getRocksPositions();
		ArrayList<Position> padsPositions = grid.getPadsPositions();
		int m = grid.getM();
		int n = grid.getN();
		int result = 0;
		/*
		 * iterates over rockPositions and padsPositions, then calculates 
		 * the city block distance for each rock to the nearest pad
		 */
		for(int i=0; i<rockPositions.size(); i++){
			int disToNearestPad = m*n ;
			for(int j=0; j<padsPositions.size(); j++){
				int dis = rockPositions.get(i).cityBlockDistanceTo(padsPositions.get(j));
				disToNearestPad = Math.min(disToNearestPad, dis);
			}
			result += disToNearestPad;
		}
		
		return result;
	}
	
	private int heuristic2(Node n) {
		return 0;
	}
	 

}
