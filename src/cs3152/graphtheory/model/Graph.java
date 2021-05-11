package cs3152.graphtheory.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T extends Comparable<T>> {
	
		private Map<T,List<T>> edges;

	    public Graph() {
	    	this.edges = new HashMap<T,List<T>>();
	    }
	    
	    public Set<T> getEdgeKeys() {
	    	return this.edges.keySet();
	    }
	
	    public int order(){
	    	return this.edges.keySet().size();
	    }
	
	    public void addNode(T node){
	        edges.put(node, new ArrayList<T>());
	    }
	    
	    public void removeNode(T node) {
	    	if (node == null) {
	    		throw new IllegalArgumentException("Not to be removed doesn't exist.");
	    	}
	    	this.edges.remove(node);
	    }

	    public boolean containsNode(T node) {
	        return this.edges.keySet().contains(node);
	    }
	

	    public void addEdge(T node0, T node1){
	    	this.edges.get(node0).add(node1);
	    }
	    
	    public int degree(T node) {
	    	return this.edges.get(node).size();
	    }
	    
	    public List<T> getNeighbors(T node) {
	    	if (node == null) {
	    		throw new IllegalArgumentException("Node cannot be null.");
	    	}
	    	if (!this.edges.containsKey(node)) {
	    		throw new IllegalArgumentException("Node not in graph: " + node.toString());
	    	}
	    	return this.edges.get(node);
	    }
	    
	    public Map<T,T> breadthFirstSearch(T start){
	    	if (start == null) {
	    		throw new IllegalArgumentException("start cannot be null");
	    	}
	    	Queue<T> queue = new LinkedList<T>();
	    	Map<T,T> search = new HashMap<T,T>();
	    	Set<T> marked = new HashSet<T>();
	    	
	    	marked.add(start);
	    	
	    	queue.add(start);
	    	while (!queue.isEmpty()) {
	    		T currentNode = queue.remove();
	    		for(T node : this.getNeighbors(currentNode)) {
		    		if (!marked.contains(node)) {
		    			marked.add(node);
		    			queue.add(node);
		    			search.put(node, currentNode);
		    		}
		    	}
	    	}
	    	
	    	return search;
	    }
}

