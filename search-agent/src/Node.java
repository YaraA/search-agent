
public class Node {
	State state;
	Node parent;
	Operator operator;
	int depth;
	int pathCost;

	public Node(State s, Node p, Operator o, int d, int c) {
		this.state = s;
		this.parent = p;
		this.operator = o;
		this.depth = d;
		this.pathCost = c;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	

}
