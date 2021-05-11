package cs3152.graphtheory.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WikiReader {
	
	private static final String wikiIds = "C:\\Users\\Wilson\\Documents\\School\\West Georgia\\CS 3152 - Data Structures and Discrete Math 2\\Projects\\Project 4\\WikiIDs.txt";
	private static final String wikiLinks = "C:\\Users\\Wilson\\Documents\\School\\West Georgia\\CS 3152 - Data Structures and Discrete Math 2\\Projects\\Project 4\\WikiLinks.txt";
	
	private static Map<String, Integer> idMap = new HashMap<String, Integer>();
	private static Map<Integer, String> nameMap = new HashMap<Integer, String>();
	
	public static void readIds() {
		String line;
		try (BufferedReader idReader = new BufferedReader(new FileReader(new File(wikiIds)))) {
			while ((line = idReader.readLine()) != null) {
				String[] split = line.split(":");
				idMap.put(split[1], Integer.parseInt(split[0]));
				nameMap.put(Integer.parseInt(split[0]), split[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static Map<String, Integer> getIdMap() {
		return idMap;
	}
	
	public static Map<Integer, String> getNameMap() {
		return nameMap;
	}
	
	public static Graph<Integer> readLinksInOrder() {
		Graph<Integer> linksGraph = new Graph<Integer>();
		
		try (BufferedReader linksReader = new BufferedReader(new FileReader(new File(wikiLinks)))) {
			String line;
			while ((line = linksReader.readLine()) != null) {
				int sourceLink = 0;
				for(int i = 0; i < line.length(); i++) {
					if (!Character.isDigit(line.charAt(i))) {
						break;
					} else {
						sourceLink = (sourceLink * 10) + (line.charAt(i) - 48);
					}
				}
				linksGraph.addNode(sourceLink);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedReader linksReader = new BufferedReader(new FileReader(new File(wikiLinks)))) {
			int nextRead;
			int sourceid = -1;
			int sourceidBuilder = 0;
			int currentid = 0;
			while ((nextRead = linksReader.read()) != -1) {
				char nextChar = (char) nextRead;
				if (sourceid == -1) {
					if (Character.isDigit(nextChar)) {
						sourceidBuilder = (sourceidBuilder * 10) + (nextChar - 48);
					} else if (nextChar == ' ') {
						sourceid = sourceidBuilder;
						sourceidBuilder = 0;
					} else if (nextChar == '\n') {
						sourceid = -1;
						sourceidBuilder = 0;
					}
				} else {
					if (Character.isDigit(nextChar)) {
						currentid = (currentid * 10) + (nextChar - 48);
					} else if (nextChar == ' ') {
						linksGraph.addEdge(currentid, sourceid);
						currentid = 0;
					} else if (nextChar == '\n') {
						linksGraph.addEdge(currentid, sourceid);
						sourceid = -1;
						sourceidBuilder = 0;
						currentid = 0;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return linksGraph;
	}
}
