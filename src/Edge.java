import java.util.*;

/**
 * Represents a directed link in the network 
 *
 * @author Kevin Ni
 */
public class Edge{

	private int weight;

	/**
	 * Class constructor
	 *
	 * @param weight weight of the link
	 *
	 */
	public Edge(int weight){
		this.weight = weight;
	}

	/**
	 * gets the weight of the edge
	 *
	 * @return the weight of the edge
	 */
	public int getWeight(){
		return weight;
	}
}
