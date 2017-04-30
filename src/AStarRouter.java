import java.util.*;

/**
 * uses an A* search to find the most efficient schedule
 *
 * @author Kevin Ni
 */
public class AStarRouter{

	private Map<String, Node> nodes;
	private int totalUnloadingCost;
	private short nJobs;
	private int expandedNodes;

	public AStarRouter(){
		nodes = new HashMap<String, Node>();
		totalUnloadingCost = 0;
		nJobs = 0;
		expandedNodes = 0;
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

		nodea.declareJob(nodeb, nJobs++);
		totalUnloadingCost += nodeb.getUnloadingCost();
	}

	public int getExpandedNodes(){
		return expandedNodes;
	}

	public String run(){
		RoutingState optimalpath = runAStar();
		StringBuilder stringBuilder = new StringBuilder();

		for(RoutingState cur = optimalpath; cur.getPrevState() != null; cur = cur.getPrevState()){
			stringBuilder.insert(0, String.format("%s %s to %s\n",
						cur.justCompletedJob() ? "Job" : "Empty",
						cur.getPrevState().nodeName(),
						cur.nodeName()));
		}

		stringBuilder.insert(0, String.format("cost = %d\n", optimalpath.getLen() + totalUnloadingCost));

		return stringBuilder.toString();
	}

	private RoutingState runAStar(){
		PriorityQueue<RoutingState> pq = new PriorityQueue<RoutingState>(new Heuristic(nodes));
		pq.add(new RoutingState(nodes.get("Sydney"), nJobs));
		HashSet<RoutingState> seen = new HashSet<RoutingState>();
		while(pq.size() > 0){
			RoutingState cur = pq.poll();

			expandedNodes++;
			if(seen.contains(cur)){
				//change for astar
				continue;
			}
			if(cur.isGoalState()){
				return cur;
			}
			seen.add(cur);

			for(Node adj : cur.getAdjacencies()){
				pq.add(new RoutingState(adj, cur));
			}
		}
		return null; //this should never happen!
	}
}
