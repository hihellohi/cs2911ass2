import java.util.*;

/**
 * union find data structure
 *
 * @author Kevin Ni
 */
public class UnionFind{
	private Map<Object, Object> representative;

	public UnionFind(){
		representative = new HashMap<Object, Object>();
	}

	public void declareNode(Object o){
		representative.put(o, o);
	}

	public Object find(Object o){
		Object rep = representative.get(o);
		if(rep == o){
			return o;
		}

		Object newrep = find(rep);
		representative.put(o, newrep);
		return newrep;
	}

	public void join(Object a, Object b){
		representative.put(find(a), find(b));
	}
}
