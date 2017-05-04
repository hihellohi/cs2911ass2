import java.util.*;

/**
 * Heuristic used for A* search, given to priority queue as a comparator
 *
 * @author Kevin Ni
 */
public class JobsHeuristic implements Heuristic{
	/**
	 * compares two states based on their paths and a heuristic
	 * 
	 * @param a the first state to compare
	 * @param b the second state to compare
	 *
	 * @pre a != null
	 * @pre b != null
	 *
	 * @return negative, 0 and positive integer if a is less than, equal to or greater than b respectively
	 */
	public int compare(State a, State b){
		int costA = heuristicValue(a);
		int costB = heuristicValue(b);
		if(costA < costB){
			return -1;
		}
		else if(costA == costB){
			return 0;
		}
		return 1;
	}

	/**
	 * gets the heuristic augmented value of a state 
	 *
	 * @param s the state to be evaluated
	 *
	 * @return the heuristic augmented value
	 */
	public int heuristicValue(State s){
		return s.getRemainingJobLength() + s.getPathLen();
	}
}
