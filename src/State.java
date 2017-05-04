import java.util.*;

/**
 * represents a state in the A* search
 *
 * @author Kevin Ni
 *
 * @inv prevState != null
 * @inv curNode != null
 * @inv jobsToDo != null
 */
public class State{

	private State prevState;
	private Node curNode;
	private int pathLen;
	private int remainingJobLength;
	private Set<Edge> jobsToDo;
	private boolean completedJobFlag;

	/**
	 * Root class constructor
	 *
	 * @param start the starting node
	 * @param jobsToDo the set of jobs that need to be done
	 *
	 * @pre start != null
	 * @pre jobsToDo != null
	 */
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

	/**
	 * Child class constructor
	 *
	 * @param cur the current node of this state
	 * @param prev the immediate state that you reached this state from
	 *
	 * @pre cur != null
	 * @pre prev != null
	 * @pre prev is adjacent to cur
	 */
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

	/**
	 * checks if this object represents the same state as another object
	 *
	 * @param obj the object we are comparing against
	 *
	 * @return true if the object represents the same stae, false otherwise
	 */
	@Override public boolean equals(Object obj) {
		if(obj != null && obj instanceof State){
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

	/**
	 * gets the hash code of this object
	 *
	 * @return the hash code of this object
	 */
	@Override public int hashCode() {
		int hash = 0;
		for(Edge job : jobsToDo){
			hash += job.hashCode();
		}
		hash = Objects.hash(hash, curNode.hashCode());
		return hash;
	}

	/**
	 * checks whether this object represents a goal state
	 *
	 * @return true if this represents a goal state, false otherwise
	 */
	public boolean isGoalState(){
		return jobsToDo.size() == 0;
	}

	/**
	 * get all nodes adjacent to the current node
	 *
	 * @return all nodes adjacent to the current node
	 *
	 * @post value != null
	 */
	public Iterable<Node> getAdjacencies(){
		return curNode.getAdjacencies();
	}

	/**
	 * get all jobs that have yet to be completed
	 *
	 * @return jobs that have yet to be completed
	 *
	 * @post value != null
	 */
	public Set<Edge> getJobsToDo(){
		return jobsToDo;
	}

	/**
	 * get the combined length of all job edges that have yet to be completed
	 *
	 * @return the combined length of all job edges that have yet to be completed
	 */
	public int getRemainingJobLength(){
		return remainingJobLength;
	}

	/**
	 * get the current node
	 *
	 * @return the current node
	 *
	 * @post value != null
	 */
	public Node getCurNode(){
		return curNode;
	}

	/**
	 * get the length of the path that was taken to get to this state
	 *
	 * @return the length of the path that was taken to get to this state
	 */
	public int getPathLen(){
		return pathLen;
	}

	/**
	 * checks if this state is immediately after a job completeion
	 *
	 * @return true if this state is immediately after a job completeion false otherwise
	 */
	public boolean justCompletedJob(){
		return jobsToDo != prevState.getJobsToDo();
	}

	/**
	 * get the state immediately preceding this one in the path
	 *
	 * @return the state immediately preceding this one in the path
	 *
	 * @post value != null
	 */
	public State getPrevState(){
		return prevState;
	}

	/**
	 * gets an edge that starts from the current node
	 *
	 * @param other the destination of the edge
	 *
	 * @return null if no such edge exists, the edge if it does
	 *
	 * @pre other != null
	 */
	public Edge getEdge(Node other){
		return curNode.getEdge(other);
	}

	/**
	 * get the name of the current node
	 *
	 * @return the name of the current node
	 *
	 * @pre value != null
	 */
	public String nodeName(){
		return curNode.getName();
	}
}
