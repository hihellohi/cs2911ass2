import java.util.*;

/**
 * uses an A* search to find the most efficient schedule
 *
 * @author Kevin Ni
 */
public class AStarRouter{

	private Map<String, Node> nodes;
	private Set<Job> jobs;
	private int totalUnloadingCost;

	public AStarRouter(){
		nodes = new HashMap<String, Node>();
		jobs = new HashSet<Job>();
		totalUnloadingCost = 0;
	}

	public void declareNode(String name, int unloadingCost){
		nodes.put(name, new Node(name, unloadingCost));
	}

	public void declareEdge(String namea, String nameb, int weight){
		Node nodea = nodes.get(namea);
		Node nodeb = nodes.get(nameb);

		nodea.declareEdge(nodeb, weight);
		nodeb.declareEdge(nodea, weight);
	}

	public void declareJob(String namea, String nameb){
		Node nodea = nodes.get(namea);
		Node nodeb = nodes.get(nameb);

		totalUnloadingCost += nodea.getUnloadingCost();
		jobs.add(new Job(nodea, nodeb, jobs.size()));
	}

	public String run(){
		RoutingState optimalpath = runAStar();
		StringBuilder stringBuilder = new StringBuilder();

		for(RoutingState cur = optimalpath; cur.getPrevState() != null; cur = cur.getPrevState()){
			stringBuilder.insert(0, String.format("%s %s to %s",
						cur.justCompletedJob() ? "Job" : "Empty",
						cur.getPrevState().nodeName(),
						cur.nodeName()));
		}

		return stringBuilder.toString();
	}

	private RoutingState runAStar(){
		return null;
	}
}
