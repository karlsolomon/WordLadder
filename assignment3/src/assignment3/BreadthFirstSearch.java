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
	
	public BreadthFirstSearch(){
		ladder = new Ladder();	
		searched = new Searched();
		previousIndex = new int[Words.dictionary.size()];
		Arrays.fill(previousIndex, -1);
		queue = new LinkedList<Integer>();// use .add() and .remove()
		results = new ArrayList<Integer>();
	}
	
	public void startBFS(String start, String end){
		search(Words.dictionary.indexOf(start), Words.dictionary.indexOf(end));
		ladder.printLadder(end);
	}
	
	private void search(int startIndex, int goalIndex){
		if(startIndex == goalIndex){
			ladder.add(startIndex);
			return;
		}
		searched.setIndex(startIndex, true);//startIndex has been visited
		ArrayList<Integer> listForQueue = Words.linkedList.get(startIndex);
		for(Integer i : listForQueue){
			queue.add(i);
			searched.setIndex(i, true);
			previousIndex[i] = startIndex;
		}
		int currentIndex = startIndex;
		if(queue.isEmpty() == false){
			currentIndex = queue.remove();
		}
		else{
			return;
		}
		while(currentIndex != goalIndex){
			listForQueue = Words.linkedList.get(currentIndex);
			for(Integer i : listForQueue){
				queue.add(i);
				searched.setIndex(i, true);
				previousIndex[i] = currentIndex;
			}
			if(queue.isEmpty() == false){
				currentIndex = queue.remove();
			}
			else{
				System.out.println("You goofed, no connection");
				return;
			}		
		}
		int x = previousIndex[currentIndex];
		while(x != startIndex){
			results.add(x);
			x = previousIndex[currentIndex];
		}
		for(int i = results.size(); i > 0; i--){
			ladder.add(results.get(i));
		}
		return;
	}
	public ArrayList<String> getLadder() {
		return ladder.getLadder();
	}
}
