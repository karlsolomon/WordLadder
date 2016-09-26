package assignment3;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class DepthFirstSearch {
	private Ladder ladder = new Ladder();
	private boolean ladderFound = false;
	public DepthFirstSearch() {		
	}
	
	public void startDFS(String start, String end) {
		new Thread(() -> {
		    forwardDFS(start, end);
		}).start();
		
		new Thread(() -> {
		    reverseDFS(start, end);
		}).start();
	}
	
	private void forwardDFS(String start, String end) {
		Ladder ladder = new Ladder();
		ArrayList<Integer> searched = new ArrayList<Integer>();	
		try{
			search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end), searched, ladder);
			if(!ladderFound) {
				ladder.printLadder(end);
				ladderFound = true;
				this.ladder = ladder;
			}
		} catch (StackOverflowError e) { //if both too large, fail
			ladder.noLadder(start, end);
		}
	}
	
	private void reverseDFS(String start, String end) {
		Ladder ladderReverse = new Ladder();
		ArrayList<Integer> searchedReverse = new ArrayList<Integer>();	
		try {
			search(Words.dictionary.indexOf(end), Words.dictionary.indexOf(start), searchedReverse, ladderReverse);
			ArrayList<String> reverseladder = ladderReverse.getLadder();
			String first;
			String second;
			int secondIndex;
			for(int i = 0; i < reverseladder.size()/2; i++) {
				secondIndex = reverseladder.size() - (i+1);
				first = reverseladder.get(i);
				second = reverseladder.get(secondIndex);
				reverseladder.set(i, second);
				reverseladder.set(secondIndex, first);
			}		
			if(!ladderFound){
				this.ladder = ladderReverse;
				ladderReverse.printLadder(end);
				ladderFound = true;
			}
		} catch (StackOverflowError e) { //if both too large, fail
			ladderReverse.noLadder(start, end);
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
