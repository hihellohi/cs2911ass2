import java.util.*;

public interface Heuristic extends Comparator<State> {
	public int heuristicValue(State s);
}
