package assignment3;

import java.util.ArrayList;

public class Ladder {
	public ArrayList<String> ladder;
	public Ladder() {
		ladder = new ArrayList<String>();
	}
	
	public Ladder(ArrayList<String> inLadder) {
		this.ladder = inLadder;
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
	public void setLadder(ArrayList<String> ladder) {
		this.ladder = ladder;
	}
	
	public void reverseLadder() {
		String first;
		String second;
		int secondIndex;		
		for(int i = 0; i < ladder.size()/2; i++) {
			secondIndex = ladder.size() - (i+1);
			first = ladder.get(i);
			second = ladder.get(secondIndex);
			ladder.set(i, second);
			ladder.set(secondIndex, first);
		}		
	}
	
	public void printLadder(String end) {
		int size = ladder.size();
		String start = ladder.get(0);
		if(!ladder.get(ladder.size()-1).equals(end)) {
			noLadder(start, end);
		}
		else {
			String allRungs = "a " + size + "-rung word ladder exists between " + start + " and " + end + ".\n" ;
			for(String i : ladder) {
				allRungs += (i+"\n");
			}
			System.out.println(allRungs);
		}
	}	
	
	public void noLadder(String start, String end) {
		System.out.println("no word ladder can be found between " + start + " and " + end + ".");
	}

}
