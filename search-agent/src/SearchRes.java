import java.util.EnumSet;


public class SearchRes {
	EnumSet<Operator> moves; //the sequence of moves to reach the goal (if possible)
	int cost; //the cost of the solution computed
	int expNodesCount; //the number of nodes chosen for expansion during the search

	public SearchRes(EnumSet<Operator> moves, int cost, int expNodesCount) {
		this.moves= moves;
		this.cost= cost;
		this.expNodesCount= expNodesCount;
	}

	public EnumSet<Operator> getMoves() {
		return moves;
	}

	public void setMoves(EnumSet<Operator> moves) {
		this.moves = moves;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getExpNodesCount() {
		return expNodesCount;
	}

	public void setExpNodesCount(int expNodesCount) {
		this.expNodesCount = expNodesCount;
	}
	

}
