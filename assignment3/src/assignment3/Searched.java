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
