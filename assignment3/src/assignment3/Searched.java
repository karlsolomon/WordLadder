package assignment3;

public class Searched {
	private boolean[] isSearched;
	public Searched(){
		isSearched = new boolean[Words.dictionary.size()];
	}
	public boolean indexIsTrue(int i){
		if(isSearched[i] == true){
			return true;
		}
		else return false;
	}
	public void setIndex(int i, boolean b){
		isSearched[i] = b;
	}
}
