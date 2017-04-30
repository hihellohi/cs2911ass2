import java.util.*;

/**
 * Heuristic used for A* search, given to priority queue as a comparator
 *
 * @author Kevin Ni
 */
public class Heuristic implements Comparator<RoutingState>{
	public Heuristic(Map<String, Node> nodes){
	}

	public int compare(RoutingState a, RoutingState b){
		if(a.getLen() < b.getLen()){
			return -1;
		}
		else if(a.getLen() == b.getLen()){
			return 0;
		}
		return 1;
	}
}
