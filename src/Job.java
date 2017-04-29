import java.util.*;

/**
 * Represents a job that needs to be made
 *
 * @author Kevin Ni
 */
public class Job{

	private Node start;
	private Node end;
	private int id;

	public Job(Node start, Node end){
		this.start = start;
		this.end = end;
	}

	public Job(Node start, Node end, int id){
		this.start = start;
		this.end = end;
		this.id = id;
	}

	public Node getStart(){
		return start;
	}

	public Node getEnd(){
		return end;
	}

	public int getId(){
		return id;
	}

	@Override public boolean equals(Object obj) {
		if(obj instanceof Job){
			Job other = (Job)obj;
			return other.getStart() == start && other.getEnd() == end;
		}
		return false;
	}

	@Override public int hashCode() {
		return start.hashCode() * 17 + end.hashCode() * 23;
	}
}
