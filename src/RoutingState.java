import java.util.*;

/**
 * represents a state in the A* search
 *
 * @author Kevin Ni
 */
public class RoutingState{

	private RoutingState prevState;
	private Node curNode;
	private int[] jobsDone;
	private int len;
	private boolean completedJobFlag;

	public RoutingState(Node start, int nJobs){
		prevState = null;
		curNode = start;
		jobsDone = new int[(nJobs + 31)/32]; //ceiling(nJobs/32)
		len = 0;
		completedJobFlag = false;
	}

	public RoutingState(Node cur, RoutingState prev){
		prevState = prev;
		curNode = cur;
		jobsDone = prev.getJobsDone();
		len = prev.len + prev.getEdge(cur);
		completedJobFlag = false;
	}

	public RoutingState(Node cur, RoutingState prev, int jobId){
		this(cur, prev);
		jobsDone[jobId / 32] |= 1 << (jobId % 32);
		completedJobFlag = true;
	}

	public int[] getJobsDone(){
		return jobsDone.clone();
	}

	public int getEdge(Node cur){
		return curNode.getEdge(cur);
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

	public String nodeName(){
		return curNode.getName();
	}

	@Override public boolean equals(Object obj) {
		if(obj instanceof RoutingState){
			RoutingState other = (RoutingState)obj;
			if(other.curNode != curNode){
				return false;
			}

			for(int i = 0; i < jobsDone.length; i++){
				if(jobsDone != other.jobsDone){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override public int hashCode() {
		int hash = curNode.hashCode() * 17;
		int factor = 31;
		for(int i = 0; i < jobsDone.length; i++){
			hash += jobsDone[i] * factor;
			factor *= 31;
		}
		return hash;
	}
}
