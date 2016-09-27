package assignment3;

import java.util.ArrayList;

public class DepthFirstSearch {
	private ArrayList<Integer> searched;
	private Ladder ladder;
	
	public DepthFirstSearch() {
		ladder = new Ladder();
		searched = new ArrayList<Integer>();		
	}
	
	public void startDFS(String start, String end) {
		search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end));
		ladder.printLadder(end);
	}
	
	private Integer search(int currentWordIndex, int goalIndex) {
		if(searched.contains(currentWordIndex)) {
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
		return searchList(greaterThan, equalTo, lessThan, goalIndex);
	}
	
	public ArrayList<String> getLadder() {
		return ladder.getLadder();
	}
	
	private Integer searchList(ArrayList<Integer> gList, ArrayList<Integer> eList, ArrayList<Integer> lList, Integer goalIndex) {
		try {
			for(Integer i : gList) {
				int a = search(i, goalIndex);
				if(a != -1) return a;
			}
			for(Integer i : eList) {
				int a = search(i, goalIndex);
				if(a != -1) return a;
			}
			for(Integer i : lList) {
				int a = search(i, goalIndex);
				if(a != -1) return a;
			}
		} catch (StackOverflowError e) {
			return -1; // ladder is too long for Stack. Can't solve with recursion
		}
		return -1;
	}
}
