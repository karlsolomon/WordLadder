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

/**
 * Contains all methods/properties for a ladder
 * Allows addition and instantiation, but no removal.
 * If a rung is added to the ladder it is assumed that the rung has already been verified and doesn't need to be removed.
 * @author KSolomon
 *
 */
public class Ladder {
	private ArrayList<String> ladder;
	
	/**
	 * Default Constructor
	 */
	public Ladder() {
		ladder = new ArrayList<String>();
	}
	
	/**
	 * Constructor which already has all rungs
	 * @param inLadder
	 */
	public Ladder(ArrayList<String> inLadder) {
		this.ladder = inLadder;
	}
	
	/**
	 * Adds a rung to the ladder where the input is the index of the word as found in Words.dictionary
	 * @param index
	 */
	public void add(int index) {
		ladder.add(Words.dictionary.get(index));
	}
	
	/**
	 * Returns the contents of the ladder
	 * @return the ladder array
	 */
	public ArrayList<String> getLadder() {
		return this.ladder;
	}	
	
	/**
	 * Sets the ladder of an already existing Ladder object to a certain array
	 * @param ladder
	 */
	public void setLadder(ArrayList<String> ladder) {
		this.ladder = ladder;
	}
	
	/**
	 * Reverse the directions of the ladder. Useful for DFS Reverse search
	 */
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
	
	/**
	 * Print the ladder with appropriate syntax
	 * @param end the expected last word of the ladder. Necessary to verify that this is a valid ladder, otherwise prints no ladder
	 */
	public void printLadder(String end) {
		int size = ladder.size();
		if(size == 0) {
			noLadder(Main.startWord, end);
		}
		else {
			String start = ladder.get(0);
			String allRungs = "a " + (size-2) + "-rung word ladder exists between " + start + " and " + end + ".\n" ;
			for(String i : ladder) {
				allRungs += (i+"\n");
			}
			System.out.println(allRungs);
		}
	}	
	
	/**
	 * If no ladder could be found between start and end, print the associated text.
	 * @param start expected starting word
	 * @param end  expected ending word
	 */
	private void noLadder(String start, String end) {
		System.out.println("no word ladder can be found between " + start + " and " + end + ".");
	}

}
