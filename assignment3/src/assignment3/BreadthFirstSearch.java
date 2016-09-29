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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

	private Ladder ladder;
	private Queue<Integer> queue;
	private Searched searched;
	private int[] previousIndex;
	private ArrayList<Integer> results;
	private boolean ladderFound = false;

	/**
	 * Constructor
	 */
	public BreadthFirstSearch(){
		ladder = new Ladder();	
		searched = new Searched();
		previousIndex = new int[Words.dictionary.size()];
		Arrays.fill(previousIndex, -1);
		queue = new LinkedList<Integer>();// use .add() and .remove()
		results = new ArrayList<Integer>();
	}
	/**
	 * Getting the index of the words and passing to search
	 * @param start is starting word
	 * @param end is ending word
	 * @return if a ladder was found
	 */
	public boolean startBFS(String start, String end){
		search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end));
		return ladderFound;
	}
	/**
	 * Based on a FIFO queue algorithm for searching,
	 * starts at a word, then goes to each of the words that are only 1 letter different and checks those
	 * and if none of those are the end word, then search will add those words' next words to the queue to be checked.
	 * @param startIndex is index of start word
	 * @param goalIndex is index of end word
	 */
	private void search(int startIndex, int goalIndex){
		if(startIndex == goalIndex){
			ladder.add(startIndex);
			return;
		}
		searched.setSearched(startIndex);//startIndex has been visited
		ArrayList<Integer> listForQueue = Words.linkedList.get(startIndex);
		if(listForQueue.size() == 0){
			ladder.noLadder(Words.dictionary.get(startIndex), Words.dictionary.get(goalIndex));
			return;
		}
		for(Integer i : listForQueue){
			queue.add(i);
			searched.setSearched(i);
			previousIndex[i] = startIndex;
		}
		int currentIndex = startIndex;
		if(!queue.isEmpty()){
			currentIndex = queue.remove();
		}
		else{
			return;
		}
		while(currentIndex != goalIndex){
			listForQueue = Words.linkedList.get(currentIndex);
			for(Integer i : listForQueue){
				if(!searched.wasSearched(i)){
					queue.add(i);
					searched.setSearched(i);
					previousIndex[i] = currentIndex;
				}
			}
			if(!queue.isEmpty()){
				currentIndex = queue.remove();
			}
			else{
				ladder.noLadder(Words.dictionary.get(startIndex), Words.dictionary.get(goalIndex));
				return;
			}		
		}
		
		while(currentIndex != startIndex){
			results.add(previousIndex[currentIndex]);
			currentIndex = previousIndex[currentIndex];
		}
		for(int i = results.size()-1; i >= 0; i--){
			ladder.add(results.get(i));
		}
		ladder.add(goalIndex);
		return;
	}
	/**
	 * Getting the ladder for the specific call of bfs
	 * @param start is starting word
	 * @param end is ending word
	 * @return the ladder
	 */
	public ArrayList<String> getLadder(String start, String end) {
		if(this.ladder.getLadder().size() != 0) {
			ArrayList<String> noEnds = this.ladder.getLadder();
			noEnds.remove(noEnds.indexOf(start));
			noEnds.remove(noEnds.indexOf(end));
			return noEnds;
		}
		else return this.ladder.getLadder();
	}
}
