import java.util.*;

/**
 * represents a state in the A* search
 *
 * @author Kevin Ni
 */
public class State{

	private State prevState;
	private Node curNode;
	private int pathLen;
	private int remainingJobLength;
	private Set<Edge> jobsToDo;
	private boolean completedJobFlag;

	public State(Node start, Set<Edge> jobsToDo){
		prevState = null;
		curNode = start;
		this.jobsToDo = jobsToDo;
		pathLen = 0;
		remainingJobLength = 0;
		
		for(Edge e : jobsToDo){
			remainingJobLength += e.getWeight();
		}
	}

	public State(Node cur, State prev){
		prevState = prev;
		curNode = cur;
		jobsToDo = prev.getJobsToDo();
		remainingJobLength = prev.getRemainingJobLength();

		Edge lastEdge = prev.getEdge(cur);
		int lastStepWeight = lastEdge.getWeight();
		pathLen = prev.pathLen + lastStepWeight;

		if(jobsToDo.contains(lastEdge)){
			jobsToDo = new HashSet<Edge>(jobsToDo);
			jobsToDo.remove(lastEdge);
			remainingJobLength -= lastStepWeight;
		}
	}

	@Override public boolean equals(Object obj) {
		if(obj instanceof State){
			State other = (State)obj;
			if(other.curNode != curNode){
				return false;
			}

			Collection<Edge> otherJobsToDo = other.getJobsToDo();
			if(otherJobsToDo == jobsToDo){
				return true;
			}
			if(otherJobsToDo.size() != jobsToDo.size()){
				return false;
			}

			for(Edge job : otherJobsToDo){
				if(!jobsToDo.contains(job)){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override public int hashCode() {
		int hash = 982451653;
		for(Edge job : jobsToDo){
			hash += job.hashCode();
		}
		hash = Objects.hash(hash, curNode.hashCode());
		return hash;
	}

	public boolean isGoalState(){
		return jobsToDo.size() == 0;
	}

	public Iterable<Node> getAdjacencies(){
		return curNode.getAdjacencies();
	}

	public Set<Edge> getJobsToDo(){
		return jobsToDo;
	}

	public int getRemainingJobLength(){
		return remainingJobLength;
	}

	public Node getCurNode(){
		return curNode;
	}

	public int getPathLen(){
		return pathLen;
	}

	public boolean justCompletedJob(){
		return jobsToDo != prevState.getJobsToDo();
	}

	public State getPrevState(){
		return prevState;
	}

	public Edge getEdge(Node cur){
		return curNode.getEdge(cur);
	}

	public String nodeName(){
		return curNode.getName();
	}
}
