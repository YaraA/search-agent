import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public abstract class SearchStrategy {
	public static void QING(Strategy s, LinkedList<Node> queue ,ArrayList<Node> children){
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
	public static void BFS(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * inserts all children nodes at the end of the queue
		 */
		queue.addAll(children);
	}
	public static void DFS(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * inserts all children nodes at the beginning of the queue
		 */
		queue.addAll(0, children);
	}
	public static void UC(LinkedList<Node> queue ,ArrayList<Node> children){
		/*
		 * inserts all children nodes at the end of the queue and then sorts the queue  
		 */
		queue.addAll(children);
		Collections.sort(queue, new CostCompare());
	}
	public static void ID(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	public static void GR1(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	public static void GR2(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	public static void AS1(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	public static void AS2(LinkedList<Node> queue ,ArrayList<Node> children){

	}
	
}
class CostCompare implements Comparator<Node> {
	
    public int compare(Node n1, Node n2) {
    	/*
    	 * compares for an ascending order based on the path cost
    	 */
        return ((Integer) n1.getPathCost()).compareTo(n2.getPathCost());       
    }
}
