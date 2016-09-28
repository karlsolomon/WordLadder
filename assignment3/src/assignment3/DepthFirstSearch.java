package assignment3;

import java.util.ArrayList;

public class DepthFirstSearch {
	private Ladder ladder = new Ladder();
	private boolean ladderFound;
	public String start;
	public String end;
	
	public DepthFirstSearch(String start, String end) {
		ladderFound = false;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Asynchronously checks both forward and backward DFS directions. Kills the slower operation when the faster one is found
	 * @param start first user input word, the start of the ladder
	 * @param end second user input word, the end of the ladder
	 */
	public void startDFS() {
		Thread forward = new Thread(new RunDFS(true, this));
		forward.start();
		Thread reverse = new Thread(new RunDFS(false, this));
		reverse.start();
		while (true) {
		   try {
		      forward.join();
		      reverse.join();
		      break;
		   } catch (InterruptedException e) {
		      e.printStackTrace();
		      break;
		   }
		}
	}
	
	public void forwardDFS() {
		Ladder ladder = new Ladder();
		ArrayList<Integer> searched = new ArrayList<Integer>();	
		try{
			search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end), searched, ladder);
			if(!ladderFound) {
				ladderFound = true;
				if(ladder.getLadder().contains(start) && ladder.getLadder().contains(end)) {
					ladder.printLadder(end);
					this.ladder = ladder;
				}
				else {
					ladder.noLadder(start, end);
					this.ladder = new Ladder();
				}
			}
		} catch (StackOverflowError e) { //if both too large, fail
			ladder.noLadder(start, end);
			this.ladder = new Ladder();
		}
	}
	
	public void reverseDFS() {
		Ladder ladderReverse = new Ladder();
		ArrayList<Integer> searchedReverse = new ArrayList<Integer>();	
		try {
			search(Words.dictionary.indexOf(end), Words.dictionary.indexOf(start), searchedReverse, ladderReverse);
			if(!ladderFound) {
				ladderFound = true;
				ArrayList<String> reverseLadder = ladderReverse.getLadder();
				if(reverseLadder.contains(start)) {
					ladderReverse.reverseLadder();
					this.ladder = ladderReverse;
					ladder.printLadder(end);
				}
				else {
					ladderReverse.noLadder(start, end);
					this.ladder = new Ladder();
				}				
			}
		} catch (StackOverflowError e) { //if both too large, fail
			ladderReverse.noLadder(start, end);
			this.ladder = new Ladder();
		}
		
	}
	
	private Integer search(int currentWordIndex, int goalIndex, ArrayList<Integer> searched, Ladder ladder) {
		if(searched.contains(currentWordIndex) || ladderFound) {
			return -1;
		}
		ladder.add(currentWordIndex);
		searched.add(currentWordIndex);
		//base case
		if(currentWordIndex == goalIndex) {
			return currentWordIndex;
		}
		
		ArrayList<Integer> greaterThan = new ArrayList<Integer>();
		ArrayList<Integer> equalTo = new ArrayList<Integer>();
		ArrayList<Integer> lessThan = new ArrayList<Integer>();
		
		int currentMatching = Words.getNumberOfCommonLetters(currentWordIndex, goalIndex);
		
		ArrayList<Integer> list = Words.linkedList.get(currentWordIndex);
		int match = 0;
		for(Integer i : list){
			match = Words.getNumberOfCommonLetters(i, goalIndex);
			if(match > currentMatching) {
				greaterThan.add(i);
			}
			else if (match == currentMatching) {
				equalTo.add(i);				
			}
			else {
				lessThan.add(i);
			}
		}		
		return searchList(greaterThan, equalTo, lessThan, goalIndex, searched, ladder);
	}
	
	public ArrayList<String> getLadder() {
		return this.ladder.getLadder();
	}
	
	private Integer searchList(ArrayList<Integer> gList, ArrayList<Integer> eList, ArrayList<Integer> lList, Integer goalIndex, ArrayList<Integer> searched, Ladder ladder) {
		for(Integer i : gList) {
			int a = search(i, goalIndex, searched, ladder);
			if(a != -1) return a;
		}
		for(Integer i : eList) {
			int a = search(i, goalIndex, searched, ladder);
			if(a != -1) return a;
		}
		for(Integer i : lList) {
			int a = search(i, goalIndex, searched, ladder);
			if(a != -1) return a;
		}
		return -1;
	}
}