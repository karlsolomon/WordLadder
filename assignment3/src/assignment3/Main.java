/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {		
		initialize();		
		
		//ACTUAL INPUT TESTING
		//SPECIFY I/O FILES IN RUN CONFIGURATIONS -> ARGUMENTS
		//^BLANK MEANS I/O IS CONSOLE
		Scanner kb = null;	// input Scanner for commands
		PrintStream ps = null;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			try {
				ps = new PrintStream(new File(args[1]));
			} catch (FileNotFoundException e) {
				PrintWriter writer = new PrintWriter(args[1], "UTF-8");
				writer.close();
				ps = new PrintStream(new File(args[1]));
			} finally {
				System.setOut(ps);
			}
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}  
		ArrayList<String> inputs;
		while(true) {
			try {
				inputs = parse(kb);
				getWordLadderDFS(inputs.get(0), inputs.get(1));	
			} catch (ArrayIndexOutOfBoundsException | NoSuchElementException | NullPointerException e) {
				ps.close();
				return;
			}
		}
	}
	
	public static void initialize() {
		Words.setFile("five_letter_words.txt");
		Words.init();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> inputWords = new ArrayList<String>();
		String input;
		input = keyboard.next().toLowerCase();
		if(input.equalsIgnoreCase("/quit"))
			return null;
		else
			inputWords.add(input);	
		
		input = keyboard.next().toLowerCase();
		if(input.equalsIgnoreCase("//quit"))
			return null;
		else
			inputWords.add(input);		
		return inputWords;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) throws InterruptedException, ExecutionException {
		DepthFirstSearch dfs = new DepthFirstSearch(start, end);
		dfs.startDFS();		
		return dfs.getLadder();
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.startBFS(start, end);
		return bfs.getLadder();
	}    
    
    public static boolean containsDuplicates(ArrayList<String> ladder) {
    	for(int i = 0; i < ladder.size(); i ++) {
    		for(int j = 0; j < ladder.size(); j++) {
    			if (i != j) {
    				if(ladder.get(i) == ladder.get(j))
    					return true;
    			}
    		}
    	} 
    	return false;
    }    
}
 