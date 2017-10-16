import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;

public abstract class SearchProb {
	EnumSet<Operator> operators;
	State initialState;
	ArrayList<Node> visitedNodes;
	public SearchProb(EnumSet<Operator> operators, State initialState) {
		this.operators = operators;
		this.initialState = initialState;
		visitedNodes = new ArrayList<Node>();
	}

	public abstract State transition(State state, Operator operator) throws Exception;
	public abstract boolean goalTest(State state);
	public abstract int pathCost(Node node);

	public ArrayList<Node> expand(Node node) throws Exception{
		//returns an array of all the possible children of Node node
		ArrayList<Node> childrenNodes = new ArrayList<Node>();
		for (Operator o : this.operators) {  
			State newState = this.transition(node.getState(), o);
			if(newState != null)
			{
				Node childNode = new Node(newState, node, o);
				childNode.setPathCost(this.pathCost(childNode));
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
		Node root = new Node(initialState, null, null);
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
			boolean isNotVisited = !visitedNodes.contains(node);
			boolean isEmptyQueue = queue.isEmpty();
			if ( isNotVisited || isEmptyQueue)
			{	
				if(isNotVisited){
					visitedNodes.add(node);
					expNodesCount++;
					if(goalTest(node.getState()))
						return new SearchRes(node, expNodesCount);
				}
				ArrayList<Node> children = expand(node);
				search.QING(st, root, queue, children, visitedNodes);
			}
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
