import java.util.EnumSet;


public abstract class SearchProb {
	EnumSet<Operator> operators;
	State initialState;
	
	public SearchProb(EnumSet<Operator> operators, State initialState) {
		this.operators= operators;
		this.initialState= initialState;
	}
	
	public abstract State transition(State state, Operator operator);
	public abstract boolean goalTest(State state);
	public abstract int pathCost(Node node);
	public abstract EnumSet<Operator> allowedOperators(Node node);
	public void expand(Node node){
		EnumSet<Operator> allowedOperators= this.allowedOperators(node);
		for (Operator o : allowedOperators) {  
			State newState= this.transition(node.state, o);
			int cost = this.pathCost(node);
			int childDepth= node.depth +1;
			Node childNode= new Node(newState, node, o, childDepth, cost);
		}
		
	}

	public EnumSet<Operator> getOperators() {
		return operators;
	}

	public void setOperators(EnumSet<Operator> operators) {
		this.operators = operators;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	

}