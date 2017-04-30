import java.util.*;

/**
 * uses an A* search to find the most efficient schedule
 *
 * @author Kevin Ni
 */
public class AStarRouter{

	private static final String START = "Sydney";

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
		Node n = new Node(name, unloadingCost);
		nodes.put(name, n);
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
		if(optimalpath == null){
			return "No solution\n";
		}

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
		PriorityQueue<RoutingState> open = new PriorityQueue<RoutingState>(new Heuristic(nodes));
		open.add(new RoutingState(nodes.get(START), nJobs));
		HashMap<RoutingState, RoutingState> closed = new HashMap<RoutingState, RoutingState>();

		while(open.size() > 0){
			RoutingState cur = open.poll();

			expandedNodes++;
			if(closed.containsKey(cur)){
				continue;
			}
			if(cur.isGoalState()){
				return cur;
			}
			closed.put(cur, cur);

			for(Node adj : cur.getAdjacencies()){
				open.add(new RoutingState(adj, cur));
			}
		}
		return null; 
	}
}
