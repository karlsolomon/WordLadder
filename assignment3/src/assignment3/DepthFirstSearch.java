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
		try{
			search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end));
			ladder.printLadder(end);
		} catch (StackOverflowError e1) { // if too large, try the reverse
			try {
				ladder = new Ladder();
				searched = new ArrayList<Integer>();
				search(Words.dictionary.indexOf(end), Words.dictionary.indexOf(start));
				ArrayList<String> reverseladder = ladder.getLadder();
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
				ladder.setLadder(reverseladder);
				ladder.printLadder(end);
			} catch (StackOverflowError e2) { //if both too large, fail
				ladder.noLadder(start, end);
			}
		}
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
		return -1;
	}
}
