import java.util.*;

/**
 * Heuristic used for A* search, given to priority queue as a comparator
 *
 * @author Kevin Ni
 */
public class Heuristic implements Comparator<State>{
	public int compare(State a, State b){
		int costA = a.getPathLen() + a.getRemainingJobLength();
		int costB = b.getPathLen() + b.getRemainingJobLength();
		if(costA < costB){
			return -1;
		}
		else if(costA == costB){
			return 0;
		}
		return 1;
	}
}
