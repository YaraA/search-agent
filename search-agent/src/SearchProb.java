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
		/*
		 * Counter for depthLimit of ID. 
		 */
		DepthLimit depthLimit = new DepthLimit();
		while(true){
			if(queue.isEmpty() ) {
				/*
				 * No solution found.
				 */
				if(st != Strategy.ID)
					return new SearchRes(null, 0);
				else{
					/*
					 * If queue is empty, then increment the depth limit, insert the root
					 * and flush visited nodes.
					 */
					if(depthLimit.isIncDepth())
					{	
						depthLimit.setIncDepth(false);
						depthLimit.incDepthLimit();;
						queue.add(root);
						visitedNodes.clear(); 
					}
					else
						/* 
						 * All leaves are visited and ID should terminate.
						 */
						return new SearchRes(null, 0);
				}
			}
			/*
			 * Dequeue the node from the queue. 
			 */
			Node node = queue.removeFirst();
			/*
			 * Check if the state of the node was visited before.
			 */
			boolean isNotVisited = !visitedNodes.contains(node);
			if (isNotVisited)
			{	
				visitedNodes.add(node);
				expNodesCount++;
				if(goalTest(node.getState()))
					return new SearchRes(node, expNodesCount);
				ArrayList<Node> children = expand(node);
				search.QING(st, queue, children, depthLimit);
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
