import java.util.*;

/**
 * Data structure used for the open list of an A* search
 *
 * @author Kevin Ni
 *
 * @inv scores != null
 * @inv queue != null
 */
public class OpenList{
	HashMap<State, Integer> scores;
	PriorityQueue<State> queue;
	Heuristic heuristic;

	/**
	 * class constructor
	 *
	 * @param comp the comparator to be used
	 *
	 * @pre comp != null
	 */
	public OpenList(Heuristic heuristic){
		scores = new HashMap<State, Integer>();
		queue = new PriorityQueue<State>(heuristic);
		this.heuristic = heuristic;
	}
	
	/**
	 * check if the list contains a state
	 *
	 * @param s the state that is to be checked
	 *
	 * @return true if the list constains s false if not
	 */
	public boolean contains(State s){
		return scores.containsKey(s);
	}

	/**
	 * returns the size of the list
	 *
	 * @return the size of the list 
	 */
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
			if(heuristic.heuristicValue(s) < scores.get(s).intValue()){
				queue.remove(s);
				insert(s);
			}
		}
		else{
			insert(s);
		}
	}

	public void insert(State s){
		scores.put(s, new Integer(heuristic.heuristicValue(s)));
		queue.add(s);
	}
}
