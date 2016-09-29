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

public class Searched {
	private boolean[] isSearched;
	public Searched(){
		isSearched = new boolean[Words.dictionary.size()];
	}
	public boolean wasSearched(int i){
		if(isSearched[i]){
			return true;
		}
		else return false;
	}
	public void setSearched(int i){
		isSearched[i] = true;
	}
}
