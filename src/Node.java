import java.util.*;

/**
 * Represents a city in the network
 *
 * @author Kevin Ni
 */
public class Node{

	private Map<Node, Integer> adjacent;
	private String name;
	private int unloadingCost;

	public Node(String name, int unloadingCost){
		adjacent = new HashMap<Node, Integer>();
		this.name = name;
		this.unloadingCost = unloadingCost;
	}

	public void declareEdge(Node other, int weight){
		adjacent.putIfAbsent(other, new Integer(weight));
	}

	public int tryGetEdge(Node other){
		Integer tmp = adjacent.getOrDefault(other, null);
		if(tmp == null){
			return -1;
		}
		return tmp.intValue();
	}

	public int getEdge(Node other){
		return adjacent.get(other).intValue();
	}
	
	public String getName(){
		return name;
	}

	public int getUnloadingCost(){
		return unloadingCost;
	}
}
