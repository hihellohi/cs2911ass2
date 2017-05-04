import java.util.*;

/**
 * Data structure used for the open list of an A* search
 *
 * @author Kevin Ni
 *
 * @inv scores != null
 * @inv queue != null
 * @inv heuristic != null
 */
public class OpenList{
	HashMap<State, Integer> scores;
	PriorityQueue<State> queue;
	Heuristic heuristic;

	/**
	 * class constructor
	 *
	 * @param heuristic the heuristic to be used
	 *
	 * @pre heuristic != null
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
	 *
	 * @pre s != null
	 */
	public boolean contains(State s){
		return scores.containsKey(s);
	}

	/**
	 * returns the number of elements in the list
	 *
	 * @return the number of elements in the list
	 */
	public int size(){
		return queue.size();
	}

	/**
	 * pops the next State off the list and returns it
	 *
	 * @return the state that was popped off.
	 *
	 * @post value != null
	 */
	public State poll(){
		State out = queue.poll();
		scores.remove(out);
		return out;
	}

	/**
	 * Checks if a state already exists in the list.
	 * If it doesn't then it adds the new state onto the list.
	 * If it does, it replaces the existing state with the state only if the path associated with 
	 * the existing state is longer than the new one.
	 *
	 * @param s the state that we are attempting to add
	 *
	 * @pre s != null
	 */
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

	/**
	 * Inserts a state into the list unconditionally
	 *
	 * @param s the state to be added
	 *
	 * @pre s != null
	 * @pre s is not already on the list
	 */
	public void insert(State s){
		scores.put(s, new Integer(heuristic.heuristicValue(s)));
		queue.add(s);
	}
}
