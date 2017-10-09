import java.util.ArrayList;


public abstract class SearchStrategy {
	public static void QING(Strategy s, Iterable<Node> queue ,ArrayList<Node> children){
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
	public static void BFS(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void DFS(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void UC(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void ID(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void GR1(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void GR2(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void AS1(Iterable<Node> queue ,ArrayList<Node> children){

	}
	public static void AS2(Iterable<Node> queue ,ArrayList<Node> children){

	}
}
