import java.util.*;

/**
 * Data structure used for the open list of an A* search
 *
 * @author Kevin Ni
 */
public class OpenList{
	HashMap<State, Integer> scores;
	PriorityQueue<State> queue;

	public OpenList(Comparator<State> comp){
		scores = new HashMap<State, Integer>();
		queue = new PriorityQueue<State>(comp);
	}
	
	public boolean contains(State s){
		return scores.containsKey(s);
	}

	public int size(){
		return queue.size();
	}

	public State poll(){
		State out = queue.poll();
		scores.remove(out);
		return out;
	}

	public void updateIfBetter(State s){
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

	public void insert(State s){
		scores.put(s, new Integer(s.getPathLen()));
		queue.add(s);
	}
}
