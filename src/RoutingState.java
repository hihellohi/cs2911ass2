import java.util.*;

/**
 * represents a state in the A* search
 *
 * @author Kevin Ni
 */
public class RoutingState{

	private RoutingState prevState;
	private Node curNode;
	private int len;
	private Collection<Edge> jobsDone;
	private short nJobs;
	private boolean completedJobFlag;

	public RoutingState(Node start, short nJobs){
		prevState = null;
		curNode = start;
		jobsDone = new HashSet<Edge>();
		len = 0;
		completedJobFlag = false;
		this.nJobs = nJobs;
	}

	public RoutingState(Node cur, RoutingState prev){
		prevState = prev;
		curNode = cur;
		jobsDone = prev.getJobsDone();
		nJobs = prev.getNJobs();
		len = prev.len + prev.getEdgeWeight(cur);

		if(prev.isJobEdge(cur) && !jobsDone.contains(cur)){
			jobsDone = new HashSet<Edge>(jobsDone);
			jobsDone.add(prev.getEdge(cur));
			completedJobFlag = true;
		}
		else{
			completedJobFlag = false;
		}
	}

	@Override public boolean equals(Object obj) {
		if(obj instanceof RoutingState){
			RoutingState other = (RoutingState)obj;
			if(other.curNode != curNode){
				return false;
			}

			Collection<Edge> otherJobsDone = other.getJobsDone();
			if(otherJobsDone.size() != jobsDone.size()){
				return false;
			}

			for(Edge job : otherJobsDone){
				if(!jobsDone.contains(job)){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override public int hashCode() {
		int hash = 982451653;
		for(Edge job : jobsDone){
			hash += job.hashCode();
		}
		hash = Objects.hash(hash, curNode.hashCode());
		return hash;
	}

	public boolean isGoalState(){
		return jobsDone.size() == nJobs;
	}

	public Iterable<Node> getAdjacencies(){
		return curNode.getAdjacencies();
	}

	public Collection<Edge> getJobsDone(){
		return jobsDone;
	}

	public short getNJobs(){
		return nJobs;
	}

	public Node getCurNode(){
		return curNode;
	}

	public int getLen(){
		return len;
	}

	public boolean justCompletedJob(){
		return completedJobFlag;
	}

	public RoutingState getPrevState(){
		return prevState;
	}

	public Edge getEdge(Node cur){
		return curNode.getEdge(cur);
	}

	public int getEdgeWeight(Node cur){
		return curNode.getEdgeWeight(cur);
	}

	public boolean isJobEdge(Node cur){
		return curNode.isJobEdge(cur);
	}

	public String nodeName(){
		return curNode.getName();
	}
}
