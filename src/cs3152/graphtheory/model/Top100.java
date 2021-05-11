package cs3152.graphtheory.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Top100 {
	
	Map<String, Integer> idMap;
	Map<Integer, String> nameMap;
	Graph<Integer> linkGraph;
	Map<Integer, Integer> bfs;
	
	public Top100() {
		this.loadIdMaps();
		this.loadLinkGraph();
	}
	
	public void play() {
		this.writeToFile("top100.txt");
	}
	
	private ArrayList<Integer> top100() {
		ArrayList<Integer> top100List = new ArrayList<Integer>();
		while (top100List.size() < 100) {
			int currentLeadingArticle = -1;
			int currentLeadingArticleLinksCount = -1;
			for (Integer currentArticle : this.linkGraph.getEdgeKeys()) {
				if (this.linkGraph.degree(currentArticle) > currentLeadingArticleLinksCount) {
					currentLeadingArticleLinksCount = this.linkGraph.degree(currentArticle);
					currentLeadingArticle = currentArticle;
				}
			}
			top100List.add(currentLeadingArticle);
			this.linkGraph.removeNode(currentLeadingArticle);
		}
		return top100List;
	}
	
	private void writeToFile(String fileName) {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			for (Integer articleId : this.top100()) {
				fileWriter.write(this.nameMap.get(articleId) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadIdMaps() {
		WikiReader.readIds();
		this.idMap = WikiReader.getIdMap();
		this.nameMap = WikiReader.getNameMap();
	}
	
	private void loadLinkGraph() {
		this.linkGraph = WikiReader.readLinksInOrder();
	}
}
