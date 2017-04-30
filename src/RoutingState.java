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
	private int[] jobsDone;
	private short nJobs;
	private boolean completedJobFlag;

	public RoutingState(Node start, short nJobs){
		prevState = null;
		curNode = start;
		jobsDone = new int[(nJobs + 31)/32]; //ceiling(nJobs/32)
		len = 0;
		completedJobFlag = false;
		this.nJobs = nJobs;
	}

	public RoutingState(Node cur, RoutingState prev){
		prevState = prev;
		curNode = cur;
		jobsDone = prev.getJobsDone();
		completedJobFlag = false;
		nJobs = prev.getNJobs();

		len = prev.len + prev.getEdgeWeight(cur);

		int completedJobId = prev.getJobId(cur);
		if(completedJobId != -1){
			jobsDone[completedJobId / 32] |= 1 << (completedJobId % 32);
			completedJobFlag = true;
		}
	}

	public boolean isGoalState(){
		if(nJobs == 0){
			return true;
		}

		int i;
		for(i = 0; i < jobsDone.length - 1; i++){
			if(jobsDone[i] != -1){
				return false;
			}
		}
		return jobsDone[i] == (1 << (nJobs % 32)) - 1;
	}

	public Iterable<Node> getAdjacencies(){
		return curNode.getAdjacencies();
	}

	public int[] getJobsDone(){
		return jobsDone.clone();
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

	public int getEdgeWeight(Node cur){
		return curNode.getEdgeWeight(cur);
	}

	public int getJobId(Node cur){
		return curNode.getJob(cur);
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
				if(jobsDone[i] != other.jobsDone[i]){
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
