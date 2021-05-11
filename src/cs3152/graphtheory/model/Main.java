package cs3152.graphtheory.model;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		playWikiGame();
//		playTop100();
	}
	
	private static void playWikiGame() {
		WikiGame game = new WikiGame();
		Scanner playerIn = new Scanner(System.in);
		String input = null;
		while (input != "!exit") {
			System.out.print("");
		    System.out.println("Enter a wiki article");
		    input = playerIn.nextLine();
		    System.out.println("");
		    if (game.containsArticle(input)) {
		    	game.play(input);
		    } else {
		    	System.out.println("Wiki Article doesn't exist, try again.");
		    }
		}
		playerIn.close();
	}
	
	private static void playTop100() {
		Top100 game = new Top100();
		game.play();
	}
}
