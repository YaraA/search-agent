import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;


public abstract class SearchProb {
	EnumSet<Operator> operators;
	State initialState;
	
	public SearchProb(EnumSet<Operator> operators, State initialState) {
		this.operators = operators;
		this.initialState = initialState;
	}
	
	public abstract State transition(State state, Operator operator) throws Exception;
	public abstract boolean goalTest(State state);
	public abstract int pathCost(Node node);
	public abstract EnumSet<Operator> allowedOperators(Node node);
	
	public ArrayList<Node> expand(Node node) throws Exception{
		//returns an array of all the possible children of Node node
		ArrayList<Node> childrenNodes = new ArrayList<Node>();
		for (Operator o : this.operators) {  
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

	public SearchRes generalSearch(Strategy st) throws Exception{
		/*
		 * Create a new search strategy instance.
		 */
		SearchStrategy search = new SearchStrategy();
		/*
		 * Create a node of the initial state.
		 */
		Node root = new Node(initialState, null, null, 0, 0);
		/*
		 * Initialize an empty queue and insert the root node.
		 */
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);
		/*
		 * Counter to keep track of expanded nodes.
		 */
		int expNodesCount = 0;
		while(true){
			if(queue.isEmpty()) 
				/*
				 * No solution found.
				 */
				return new SearchRes(null, 0);
			Node node = queue.removeFirst();
			expNodesCount++;
			if(goalTest(node.getState()))
				return new SearchRes(node, expNodesCount);
			ArrayList<Node> children = expand(node);
			search.QING(st, root, queue, children);
		}
	}

	
	public Object getOperators() {
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
