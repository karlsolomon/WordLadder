package assignment3;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class MakeAllWords {

	public static void main(String[] args) throws IOException {
		FileWriter writer = new FileWriter("allWords.txt");		
		
		ArrayList<String> allWords = new ArrayList<String>();
		for(char i='a'; i<='z'; i++){
			for(char j = 'a'; j <= 'z' ; j ++ ) {
				for(char k='a'; k<='z'; k++) {
					for(char l='a'; l<='z'; l++) {
						for(char m='a'; m<='z'; m++) {
							char[] word= {i,j,k,l,m,'\t'};
							writer.write(word);
						}
						allWords.add("\n");
					}
				}
			}
		}
		writer.close();
	}
}
