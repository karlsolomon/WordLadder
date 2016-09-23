package assignment3;

import java.util.ArrayList;

public class Ladder {
	private ArrayList<String> ladder;
	public Ladder() {
		ladder = new ArrayList<String>();
	}
	
	public void add(int index) {
		ladder.add(Words.dictionary.get(index));
	}
	
	public void add(String word) {
		ladder.add(word);
	}
	
	public ArrayList<String> getLadder() {
		return this.ladder;
	}
	
	public void printLadder(String end) {
		int size = ladder.size();
		String start = ladder.get(0);
		if(!ladder.get(ladder.size()-1).equals(end)) {
			System.out.println("no word ladder can be found between " + start + " and " + end + ".");
		}
		else {
			String allRungs = "a " + size + "-rung word ladder exists between " + start + " and " + end + ".\n" ;
			for(String i : ladder) {
				allRungs += (i+"\n");
			}
			System.out.println(allRungs);
		}
	}

}
