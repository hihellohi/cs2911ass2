import java.util.*;

/**
 * Represents a city in the network
 *
 * @author Kevin Ni
 *
 * @inv adjacent != null
 * @inv name != null
 */
public class Node{

	private Map<Node, Edge> adjacent;
	private String name;
	private int unloadingCost;

	/**
	 * class constructor
	 *
	 * @param name the name of the node
	 * @param unloadingCost the cost of unloading at this node
	 * 
	 * @pre name != null
	 */
	public Node(String name, int unloadingCost){
		adjacent = new HashMap<Node, Edge>();
		this.name = name;
		this.unloadingCost = unloadingCost;
	}

	/**
	 * declares an edge that starts from this node
	 *
	 * @param other the destination of the edge
	 * @param weight the weight of the edge
	 *
	 * @pre other != null
	 */
	public void declareEdge(Node other, int weight){
		adjacent.put(other, new Edge(weight));
	}

	/**
	 * gets an edge that starts from this node
	 *
	 * @param other the destination of the edge
	 *
	 * @return null if no such edge exists, the edge if it does
	 *
	 * @pre other != null
	 */
	public Edge getEdge(Node other){
		return adjacent.get(other);
	}
	
	/**
	 * get all nodes adjacent to this one
	 *
	 * @return an iterable adjacent to this node
	 *
	 * @post value != null
	 */
	public Iterable<Node> getAdjacencies(){
		return adjacent.keySet();
	}
	
	/**
	 * get the name of this node
	 *
	 * @return the name of this node
	 *
	 * @post value != null
	 */
	public String getName(){
		return name;
	}

	/**
	 * get the unloading cost of this node
	 *
	 * @return the unloading cost of this node
	 */
	public int getUnloadingCost(){
		return unloadingCost;
	}
}
