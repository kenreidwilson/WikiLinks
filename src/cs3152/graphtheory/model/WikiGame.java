package cs3152.graphtheory.model;

import java.util.Map;

public class WikiGame {

	private static final String ObjectiveArticle = "University of West Georgia";
	
	Map<String, Integer> idMap;
	Map<Integer, String> nameMap;
	Graph<Integer> linkGraph;
	Map<Integer, Integer> bfs;
	
	public WikiGame() {
		this.loadIdMaps();
		this.loadLinkGraph();
		this.loadBFS(ObjectiveArticle);
	}
	
	public void play(String search) {
		int searchId = idMap.get(search);
		String output = "";
		while(searchId != idMap.get(ObjectiveArticle)) {
			output += nameMap.get(searchId) + " -> ";
			searchId = bfs.get(searchId);
		}
		output += ObjectiveArticle;
		System.out.println(output + "\n");
	}
	
	public boolean containsArticle(String articleTitle) {
		return this.idMap.get(articleTitle) != null;
	}
	
	private void loadIdMaps() {
		System.out.println("Loading Maps...");
		long start_time = System.currentTimeMillis();
		WikiReader.readIds();
		this.idMap = WikiReader.getIdMap();
		this.nameMap = WikiReader.getNameMap();
		long end_time = System.currentTimeMillis();
		System.out.println("Loaded Maps: " + this.idMap.size() + " && " + this.nameMap.size());
		long elapsed_time = (end_time - start_time) / 1000;
		System.out.println("Elasped time: "+ elapsed_time + "\n");
	}
	
	private void loadLinkGraph() {
		System.out.println("Loading Graph...");
		long start_time = System.currentTimeMillis();
		this.linkGraph = WikiReader.readLinksInOrder();
		long end_time = System.currentTimeMillis();
		System.out.println("Loaded Graph: " + this.linkGraph.order());
		long elapsed_time = (end_time - start_time) / 1000;
		System.out.println("Elasped time: "+ elapsed_time + "\n") ;
	}
	
	private void loadBFS(String start) {
		System.out.println("Loading bfs...");
		long start_time = System.currentTimeMillis();
		this.bfs = linkGraph.breadthFirstSearch(idMap.get(start));
		long end_time = System.currentTimeMillis();
		System.out.println("Loaded bfs: " + this.bfs.size());
		long elapsed_time = (end_time - start_time) / 1000;
		System.out.println("Elasped time: "+ elapsed_time + "\n");
	}
}
