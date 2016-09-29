/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Christopher Sickler
 * cbs2468
 * 16445
 * Karl Solomon
 * kws653
 * 16445
 * Slip days used: <0>
 * Git URL: https://github.com/karlsolomon/WordLadder 
 * Fall 2016
 */
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
	
	public ArrayList<String> getLadderWithoutStartEnd(String start, String end) {
		ArrayList<String> noEnds = this.ladder;
		noEnds.remove(noEnds.indexOf(end));
		noEnds.remove(noEnds.indexOf(start));
		return noEnds;
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
	
	public void printLadder(String start, String end, boolean exists) {
		if(!exists) {
			noLadder(start, end);
			return;
		}
		else {
			int size = ladder.size();
			String allRungs = "a " + size + "-rung word ladder exists between " + start + " and " + end + ".\n" + start +"\n";
			for(String i : ladder) {
				allRungs += (i+"\n");
			}
			System.out.println(allRungs + end + "\n");
		}
	}	
	
	public void noLadder(String start, String end) {
		System.out.println("no word ladder can be found between " + start + " and " + end + ".");
	}

}
