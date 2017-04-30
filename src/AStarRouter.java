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
	private int jobsLeft;
	private int expandedNodes;
	private Heuristic heuristic;

	public AStarRouter(){
		nodes = new HashMap<String, Node>();
		heuristic = new Heuristic();
		totalUnloadingCost = 0;
		jobsLeft = 0;
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

		jobsLeft += nodea.declareJob(nodeb);
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

		stringBuilder.insert(0, String.format("cost = %d\n", optimalpath.getPathLen() + totalUnloadingCost));

		return stringBuilder.toString();
	}

	private RoutingState runAStar(){
		HashMap<RoutingState, Integer> closed = new HashMap<RoutingState, Integer>();
		OpenList open = new OpenList(heuristic);
		open.insert(new RoutingState(nodes.get(START), jobsLeft));

		while(open.size() > 0){
			RoutingState cur = open.poll();

			expandedNodes++;
			if(cur.isGoalState()){
				return cur;
			}
			closed.put(cur, new Integer(cur.getPathLen()));

			for(Node adj : cur.getAdjacencies()){
				RoutingState newState = new RoutingState(adj, cur);
				int pathLen = newState.getPathLen();
				if(closed.containsKey(newState)){
					if(closed.get(newState).intValue() > pathLen){
						closed.remove(newState);
						open.insert(newState);
					}
				}
				else{
					open.updateIfBetter(newState);
				}
			}
		}
		return null; 
	}
}
