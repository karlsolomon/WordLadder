package assignment3;

import java.util.ArrayList;

/**
 * Uses Multithreading to simultaneously check forward and reverse directions of DFS to find the quickest and (usually) shortest ladder
 * of the two.
 * @author KSolomon
 */
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
	 * Uses two threads to checks both forward and reverse DFS directions.
	 * Joins the two threads before moving on to ensure that this.ladder is set before the main thread returns it
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
	
	/**
	 * searches the forward ladder (start -> end)
	 * If the ladder is found before the reverse process:
	 * 		1. This will print this ladder and terminate the reverse process by setting ladderFound = true
	 * 		2. This will set this.ladder to be the ladder that this method finds
	 * Otherwise:
	 * 		If the ladder is not found before reverse this process will stop and print nothing.
	 */
	public void forwardDFS() {
		Ladder ladder = new Ladder();
		ArrayList<Integer> searched = new ArrayList<Integer>();	
		try{
			search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end), searched, ladder);
			if(!ladderFound) {
				ladderFound = true;
				if(ladder.getLadder().contains(start) && ladder.getLadder().contains(end)) {
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
	
	/**
	 * searches the reverse ladder (end -> start)
	 * If the ladder is found before the forward process:
	 * 		1. This will print this ladder and terminate the forward process by setting ladderFound = true
	 * 		2. This will set this.ladder to be the ladder that this method finds
	 * Otherwise:
	 * 		If the ladder is not found before forward this process will stop and print nothing.
	 */
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
	
	/**
	 * Searches the current node for the end word. If found returns the current/end word's index. Otherwise it creates lists of all words
	 * closer to, equidistant to, and further from the goal word which are connected to this word to be searched in that order.
	 * @param currentWordIndex index of the current word in the dictionary as defined in Words.makeDictionary()
	 * @param goalIndex
	 * @param searched all words which have already been searched and are not the correct word
	 * @param ladder current ladder searching (forward/reverse)
	 * @return -1 if the currentWord has already been searched or if the ladder was found by the other thread. Otherwise returns the result of searchList
	 */
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
	
	/**
	 * Returns the resulting ladder of DFS. The ladder is whichever thread completed first (forward or reverse).
	 * @return the DFS ladder
	 */
	public ArrayList<String> getLadder(String start, String end) {
		ArrayList<String> noEnds = this.ladder.getLadder();
		noEnds.remove(noEnds.indexOf(start));
		noEnds.remove(noEnds.indexOf(end));
		return noEnds;
	}
	
	/**
	 * Searches the given lists (which are all nodes connected to the calling node) prioritizing nodes closer to the desired word, then nodes equal in distance to the desired word, then nodes further from the desired word
	 * @param gList more letters in common with the final word than the current node
	 * @param eList equal number of letters in common with the final word 
	 * @param lList fewer number of letters in common with final word
	 * @param goalIndex final word's index
	 * @param searched all previously searched nodes. searchList will skip checking these.
	 * @param ladder the current ladder being build (forward/reverse)
	 * @return -1 if this is a dead end or if all connecting nodes are equivalently dead ends, the next node's index otherwise
	 */
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