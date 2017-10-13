import java.util.ArrayList;
import java.util.EnumSet;


public abstract class SearchProb {
	EnumSet operators;
	State initialState;
	
	public SearchProb(EnumSet operators, State initialState) {
		this.operators= operators;
		this.initialState= initialState;
	}
	
	public abstract State transition(State state, Operator operator) throws Exception;
	public abstract boolean goalTest(State state);
	public abstract int pathCost(Node node);
	public abstract EnumSet<Operator> allowedOperators(Node node);
	public ArrayList<Node> expand(Node node) throws Exception{
		//returns an array of all the possible children of Node node
		ArrayList<Node> childrenNodes= new ArrayList<Node>();
		EnumSet<Operator> allowedOperators= this.allowedOperators(node);
		for (Operator o : allowedOperators) {  
			State newState= this.transition(node.getState(), o);
			if(newState != null)
			{
				int cost = this.pathCost(node);
				int childDepth= node.getDepth() +1;
				Node childNode= new Node(newState, node, o, childDepth, cost);
				childrenNodes.add(childNode);
			}
			
		}
		return childrenNodes;
	}



	
	public Object getOperators() {
		return operators;
	}

	public void setOperators(EnumSet operators) {
		this.operators = operators;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	

}
