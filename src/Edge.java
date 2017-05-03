import java.util.*;

/**
 * Represents a link in the network 
 *
 * @author Kevin Ni
 */
public class Edge{

	private Node start;
	private Node end;
	private int weight;

	public Edge(Node start, Node end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public Node getStart(){
		return start;
	}

	public Node getEnd(){
		return end;
	}

	public int getWeight(){
		return weight;
	}
}
