import java.util.*;

/**
 * An interface representing an A* heuristic
 */
public interface Heuristic extends Comparator<State> {
	/**
	 * Method that returns the heuristic value of the current state plus the length of the path to this state.
	 *
	 * @param s state to be evaluated
	 *
	 * @pre s != null
	 *
	 * @return the combined cost of the length of the path and the heuristic value of the state
	 */
	public int heuristicValue(State s);
}
