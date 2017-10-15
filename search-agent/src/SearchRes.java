import java.util.ArrayList;

public class SearchRes {
	ArrayList<Operator> moves; //the sequence of moves to reach the goal (if possible)
	int cost; //the cost of the solution computed
	int expNodesCount; //the number of nodes chosen for expansion during the search

	public SearchRes(Node goal, int expNodesCount) {
		moves = new ArrayList<Operator>();
		cost= goal==null?0:goal.getPathCost();
		
		this.calculateMoves(goal);
		this.expNodesCount= expNodesCount;
	}
	public void calculateMoves(Node goal){
		Node currNode = goal;
		while(currNode != null && currNode.getParent()!=null) {
			/*
			 * Add the operator of the current node to the beginning of the list of moves.
			 */
			moves.add(0, currNode.getOperator());
			/*
			 * Modify the current node to the parent.
			 */
			currNode = currNode.getParent();
		}
	}
	public ArrayList<Operator> getMoves() {
		return moves;
	}
	public void setMoves(ArrayList<Operator> moves) {
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
	
	public String toString() {
		if(moves.isEmpty())
			return "No solution found!";
		String result="Moves\n";
		for(Operator op: moves){
			result+= op + " "; 
		}
		result+= "\nCost\n" + this.cost + " \nNumber of Expanded Nodes\n" + this.expNodesCount;
		return result;
	}
	
	public void visualize(){
		
	}
}
