import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;


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
		ArrayList<Node> childrenNodes = new ArrayList<Node>();
		EnumSet<Operator> operators = this.allowedOperators(node);
		for (Operator o : operators) {  
			State newState = this.transition(node.getState(), o);
			if(newState != null)
			{
				int cost = this.pathCost(node);
				int childDepth = node.getDepth() +1;
				Node childNode = new Node(newState, node, o, childDepth, cost);
				childrenNodes.add(childNode);
			}
			
		}
		return childrenNodes;
	}

	public Node generalSearch(Strategy st) throws Exception{
		/*
		 * Create a node of the initial state.
		 */
		Node root = new Node(initialState, null, null, 0, 0);
		/*
		 * Initialize an empty queue and insert the root node.
		 */
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(true){
			if(queue.isEmpty())
				return null;
			Node node = queue.removeFirst();
			if(goalTest(node.getState()))
				return node;
			ArrayList<Node> children = expand(node);
			SearchStrategy.QING(st, queue, children);
		}
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
