import java.util.*;

/**
 * represents a state in the A* search
 *
 * @author Kevin Ni
 */
public class RoutingState{

	private RoutingState prevState;
	private Node curNode;
	private int pathLen;
	private int heuristicCache;
	private Collection<Edge> jobsDone;
	private boolean completedJobFlag;

	public RoutingState(Node start, int heuristicCache){
		prevState = null;
		curNode = start;
		jobsDone = new HashSet<Edge>();
		pathLen = 0;
		completedJobFlag = false;
		this.heuristicCache = heuristicCache;
	}

	public RoutingState(Node cur, RoutingState prev){
		prevState = prev;
		curNode = cur;
		jobsDone = prev.getJobsDone();
		heuristicCache = prev.getHeuristicCache();
		int lastStepWeight = prev.getEdgeWeight(cur);
		pathLen = prev.pathLen + lastStepWeight;

		if(prev.isJobEdge(cur) && !jobsDone.contains(cur)){
			jobsDone = new HashSet<Edge>(jobsDone);
			jobsDone.add(prev.getEdge(cur));
			completedJobFlag = true;
			heuristicCache -= lastStepWeight;
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
			if(otherJobsDone == jobsDone){
				return true;
			}
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
		return heuristicCache == 0;
	}

	public Iterable<Node> getAdjacencies(){
		return curNode.getAdjacencies();
	}

	public Collection<Edge> getJobsDone(){
		return jobsDone;
	}

	public int getHeuristicCache(){
		return heuristicCache;
	}

	public Node getCurNode(){
		return curNode;
	}

	public int getPathLen(){
		return pathLen;
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
