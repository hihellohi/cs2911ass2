import java.util.*;

/**
 * Represents a link in the network 
 *
 * @author Kevin Ni
 */
public class Edge{

	private Node start;
	private Node end;
	private int jobId;
	private int weight;

	public Edge(Node start, Node end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
		this.jobId = -1;
	}

	public void setJobId(int jobId){
		this.jobId = jobId;
	}

	public Node getStart(){
		return start;
	}

	public Node getEnd(){
		return end;
	}

	public boolean isJob(){
		return jobId != -1;
	}

	public int getJobId(){
		return jobId;
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
