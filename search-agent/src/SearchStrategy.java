import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class SearchStrategy {
	public void QING(Strategy s, LinkedList<Node> queue ,ArrayList<Node> children){
		switch(s){
		case BF: BFS(queue, children); break;
		case DF: DFS(queue, children); break;
		case UC: UC(queue, children); break;
		case ID: ID(queue, children); break;
		case GR1: GR1(queue, children); break;
		case GR2: GR2(queue, children); break;
		case AS1: AS1(queue, children); break;
		case AS2: AS2(queue, children); break;
		}	
	}
	public void BFS(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Inserts all children nodes at the end of the queue.
		 */
		queue.addAll(children);
	}
	public void DFS(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Inserts all children nodes at the beginning of the queue.
		 */
		queue.addAll(0, children);
	}
	public void UC(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Inserts all children nodes at the end of the queue,
		 * then sorts the queue according to the path cost in an ascending order
		 * via CostCompare.
		 */
		queue.addAll(children);
		Collections.sort(queue, new CostCompare());
	}
	public void ID(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	public void GR1(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Sets estimated cost by the city-block heuristic for each child node,
		 * then queues them accordingly in an ascending order via GreedyCompare.
		 */
		Heuristics.setChildrenHeuritic(children, Strategy.GR1);
		queue.addAll(children);
		Collections.sort(queue, new GreedyCompare());

	}
	public void GR2(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Sets estimated cost by the city-block heuristic for each child node,
		 * then queues them accordingly in an ascending order via GreedyCompare.
		 */
		Heuristics.setChildrenHeuritic(children, Strategy.GR2);
		queue.addAll(children);
		Collections.sort(queue, new GreedyCompare());
	}
	public void AS1(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Sets estimated cost by the city-block heuristic for each child node,
		 * then queues them according to the heuristic and the path cost
		 * in an ascending order via AStarCompare.
		 */
		Heuristics.setChildrenHeuritic(children, Strategy.AS1);
		queue.addAll(children);
		Collections.sort(queue, new AStarCompare());

	}

	public void AS2(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * Sets estimated cost by the city-block heuristic for each child node,
		 * then queues them according to the second heuristic and the path cost
		 * in an ascending order via AStarCompare.
		 */
		Heuristics.setChildrenHeuritic(children, Strategy.AS2);
		queue.addAll(children);
		Collections.sort(queue, new AStarCompare());
	}

}
/*
 * Comparator classes for different search strategies and their respective QING fn.
 */
/*
 * 1. CostCompare: compares according to the path cost for Uniform-Cost Search.
 */
class CostCompare implements Comparator<Node> {
	public int compare(Node n1, Node n2) {
		return ((Integer) n1.getPathCost()).compareTo(n2.getPathCost());       
	}
}
/*
 * 2. GreedyCompare: compares according to the estimated cost to goal for Greedy Search.
 */
class GreedyCompare implements Comparator<Node> {

	public int compare(Node n1, Node n2) {
		/*
		 * compares for an ascending order based on the estimated cost to goal 
		 * resulting from heuristic function
		 */
		return ((Integer) n1.getEstimatedCostToGoal()).compareTo(n2.getEstimatedCostToGoal());       
	}
}
/*
 * 3. AStarCompare: compares according to sum of the estimated cost to goal and the actual path cost for A* Search.
 */
class AStarCompare implements Comparator<Node> {

	public int compare(Node n1, Node n2) {
		/*
		 * compares for an ascending order based on the estimated cost to goal 
		 * resulting from heuristic function
		 */
		int n1Cost = n1.getEstimatedCostToGoal() + n1.getPathCost();
		int n2Cost = n2.getEstimatedCostToGoal() + n2.getPathCost();
		return ((Integer) n1Cost).compareTo(n2Cost);       
	}
}
