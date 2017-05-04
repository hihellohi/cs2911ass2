import java.util.*;

/**
 * uses an A* search to find the most efficient schedule
 *
 * @author Kevin Ni
 *
 * @inv nodes != null
 * @inv jobs != null
 * @inv heuristic != null
 */
public class AStarRouter{

	private static final String START = "Sydney";

	private Map<String, Node> nodes;
	private Set<Edge> jobs;
	private int expandedNodes;
	private int totalUnloadingCost;
	private Heuristic heuristic;

	/**
	 * Class constructor
	 */
	public AStarRouter(){
		nodes = new HashMap<String, Node>();
		heuristic = new JobsHeuristic();
		jobs = new HashSet<Edge>();
		totalUnloadingCost = 0;
		expandedNodes = 0;
	}

	/**
	 * Declares a node with uploading cost
	 *
	 * @param name the name of the city
	 * @param unloadingCost the cost of unloading at this city
	 *
	 * @pre name != null
	 */
	public void declareNode(String name, int unloadingCost){
		Node n = new Node(name, unloadingCost);
		nodes.put(name, n);
	}

	/**
	 * Declares a link between cities
	 *
	 * @param namea name of one city
	 * @param nameb name of another city
	 * @param weight size of the link
	 *
	 * @pre namea != null
	 * @pre nameb != null
	 * @pre declareNode has been called with namea
	 * @pre declareNode has been called with nameb
	 */
	public void declareEdge(String namea, String nameb, int weight){
		Node nodea = nodes.get(namea);
		Node nodeb = nodes.get(nameb);

		nodea.declareEdge(nodeb, weight);
		nodeb.declareEdge(nodea, weight);
	}

	/**
	 * Declares a job that needs to be done
	 *
	 * @param namea starting city of the job
	 * @param nameb destination of the job
	 *
	 * @pre namea != null
	 * @pre nameb != null
	 * @pre declareNode has been called with namea
	 * @pre declareNode has been called with nameb
	 * @pre declareEdge has been called with namea and nameb
	 */
	public void declareJob(String namea, String nameb){
		Node nodea = nodes.get(namea);
		Node nodeb = nodes.get(nameb);

		jobs.add(nodea.getEdge(nodeb));
		totalUnloadingCost += nodeb.getUnloadingCost();
	}

	/**
	 * getter for the number of nodes that have been expanded
	 *
	 * @return the number of nodes that have been expanded by A*
	 */
	public int getExpandedNodes(){
		return expandedNodes;
	}

	/**
	 * gets the optimal schedule
	 *
	 * @return a string with the optimal schedule
	 *
	 * @post value !+ null
	 */
	public String run(){
		State optimalpath = runAStar();
		if(optimalpath == null){
			return "No solution\n";
		}

		StringBuilder stringBuilder = new StringBuilder();

		for(State cur = optimalpath; cur.getPrevState() != null; cur = cur.getPrevState()){
			stringBuilder.insert(0, String.format("%s %s to %s\n",
						cur.justCompletedJob() ? "Job" : "Empty",
						cur.getPrevState().nodeName(),
						cur.nodeName()));
		}

		stringBuilder.insert(0, String.format("cost = %d\n", optimalpath.getPathLen() + totalUnloadingCost));

		return stringBuilder.toString();
	}

	/**
	 * runs A* to generate the optimal path 
	 *
	 * @return the optimal path
	 *
	 * @post value !+ null
	 */
	private State runAStar(){
		HashMap<State, Integer> closed = new HashMap<State, Integer>();
		OpenList open = new OpenList(heuristic);
		open.insert(new State(nodes.get(START), jobs));

		while(open.size() > 0){
			State cur = open.poll();

			expandedNodes++;
			if(cur.isGoalState()){
				return cur;
			}
			closed.put(cur, new Integer(heuristic.heuristicValue(cur)));

			for(Node adj : cur.getAdjacencies()){
				State newState = new State(adj, cur);
				int pathLen = heuristic.heuristicValue(newState);
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
