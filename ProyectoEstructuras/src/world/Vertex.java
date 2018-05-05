package world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Vertex<V, E> {

	private Vertex<V,E> ancestor;
	private V value;
	private int color;
	private int d;
	private HashMap<Vertex<V,E>,ArrayList<Edge<V,E>>> edges;
	/**
	 * 
	 * @param v
	 */
	public Vertex(V v) {
		edges=new HashMap<Vertex<V,E>,ArrayList<Edge<V,E>>>();
		ancestor=null;
		color=0;
		d=0;
		value=v;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Iterator<Vertex<V,E>> neighborIterator() {
		return edges.keySet().iterator();
	}

	/**
	 * 
	 * @param v
	 */
	public ArrayList<Edge> getEdges(Vertex v) {
		// TODO - implement Vertex.getEdges
		throw new UnsupportedOperationException();
	}

	public boolean containsEdge(E e, Vertex<V, E> v22) {
		if(!edges.containsKey(v22))return false;
		ArrayList<Edge<V, E>>edgy=edges.get(v22);
		boolean contains=false;
		for (int i = 0; i < edgy.size() && !contains; i++) {
			if(edgy.get(i).getLabel().equals(e)) {
				return true;
			}
		}
		return contains;
	}

	public void addEdge(Edge<V, E> edge1) {
		
	}
	/**
	 * <pre>: this does not contain edge1
	 * @param edge1
	 * @param ver
	 */
	public void addEdge(Edge<V, E> edge1, Vertex<V, E> ver) {
		ArrayList<Edge<V, E>>ed=null;
		if(edges.containsKey(ver)){
			ed=edges.get(ver);
			ed.add(edge1);
		}else{
			ed=new ArrayList<Edge<V, E>>();
			ed.add(edge1);
			edges.put(ver,ed);
		}
	}
}