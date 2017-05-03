import java.util.*;

/**
 * Represents a city in the network
 *
 * @author Kevin Ni
 */
public class Node{

	private Map<Node, Edge> adjacent;
	private String name;
	private int unloadingCost;

	public Node(String name, int unloadingCost){
		adjacent = new HashMap<Node, Edge>();
		this.name = name;
		this.unloadingCost = unloadingCost;
	}

	public void declareEdge(Node other, int weight){
		adjacent.put(other, new Edge(this, other, weight));
	}

	public Edge getEdge(Node other){
		return adjacent.get(other);
	}
	
	public Iterable<Node> getAdjacencies(){
		return adjacent.keySet();
	}
	
	public String getName(){
		return name;
	}

	public int getUnloadingCost(){
		return unloadingCost;
	}
}
