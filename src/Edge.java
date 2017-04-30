import java.util.*;

/**
 * Represents a link in the network 
 *
 * @author Kevin Ni
 */
public class Edge{

	private Node start;
	private Node end;
	private boolean isJob;
	private int weight;

	public Edge(Node start, Node end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
		this.isJob = false;
	}

	public void makeJob(){
		isJob = true;
	}

	public Node getStart(){
		return start;
	}

	public Node getEnd(){
		return end;
	}

	public boolean getIsJob(){
		return isJob;
	}

	public int getWeight(){
		return weight;
	}

//	@Override public boolean equals(Object obj) {
//		if(obj instanceof Job){
//			Job other = (Job)obj;
//			return other.getStart() == start && other.getEnd() == end;
//		}
//		return false;
//	}
//
//	@Override public int hashCode() {
//		return start.hashCode() * 17 + end.hashCode() * 23;
//	}
}
