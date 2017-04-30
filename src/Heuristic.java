import java.util.*;

/**
 * Heuristic used for A* search, given to priority queue as a comparator
 *
 * @author Kevin Ni
 */
public class Heuristic implements Comparator<RoutingState>{
	public int compare(RoutingState a, RoutingState b){
		int costA = a.getPathLen() + a.getHeuristicCache();
		int costB = b.getPathLen() + b.getHeuristicCache();
		if(costA < costB){
			return -1;
		}
		else if(costA == costB){
			return 0;
		}
		return 1;
	}
}
