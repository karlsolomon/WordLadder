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
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {
	// static variables and constants only here.
	public static String startWord = "";
	public static String endWord = "";
	public static ArrayList<String> ladder;
	
	public static void main(String[] args) throws Exception {		
		//initialize();		
		
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
				startWord = inputs.get(0);
				endWord = inputs.get(1);
				ladder = getWordLadderBFS(startWord, endWord);	
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
		startWord = inputWords.get(0);
		endWord = inputWords.get(1);
		return inputWords;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) throws InterruptedException, ExecutionException {
		startWord = start;
		endWord = end;
		DepthFirstSearch dfs = new DepthFirstSearch(start, end);
		dfs.startDFS();
		return dfs.getLadder(start, end);
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		startWord = start;
		endWord = end;
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		bfs.startBFS(start, end);
		return bfs.getLadder(start, end);
	}    
    
    
    public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		Ladder ladderToPrint = new Ladder(ladder);
		ladderToPrint.printLadder(startWord, endWord);
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
 