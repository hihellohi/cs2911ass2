import java.util.*;

/**
 * Data structure used for the open list of an A* search
 *
 * @author Kevin Ni
 */
public class OpenList{
	HashMap<RoutingState, Integer> scores;
	PriorityQueue<RoutingState> queue;

	public OpenList(Comparator<RoutingState> comp){
		scores = new HashMap<RoutingState, Integer>();
		queue = new PriorityQueue<RoutingState>(comp);
	}
	
	public boolean contains(RoutingState s){
		return scores.containsKey(s);
	}

	public int size(){
		return queue.size();
	}

	public RoutingState poll(){
		RoutingState out = queue.poll();
		scores.remove(out);
		return out;
	}

	public void updateIfBetter(RoutingState s){
		if(contains(s)){
			if(s.getPathLen() < scores.get(s).intValue()){
				queue.remove(s);
				insert(s);
			}
		}
		else{
			insert(s);
		}
	}

	public void insert(RoutingState s){
		scores.put(s, new Integer(s.getPathLen()));
		queue.add(s);
	}
}
