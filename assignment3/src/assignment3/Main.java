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
	public static boolean ladderExists = false;
	private static boolean initialized = false;
	
	/**
	 * For general running cases
	 * @param args inputs
	 * @throws Exception for if a file not found
	 */
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
		if(!initialized)
			initialize();
		
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
	/**
	 * Initializing the file for test cases and making the dictionary
	 */
	public static void initialize() {
		initialized = true;
		Words.setFile("five_letter_words.txt");
		Words.init();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in or a File input
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> inputWords = new ArrayList<String>();
		String input;
		input = keyboard.next().toLowerCase();
		if(input.equalsIgnoreCase("/quit"))
			System.exit(0);
		else
			inputWords.add(input);	
		
		input = keyboard.next().toLowerCase();
		if(input.equalsIgnoreCase("/quit"))
			System.exit(0);
		else
			inputWords.add(input);
		startWord = inputWords.get(0);
		endWord = inputWords.get(1);
		return inputWords;
	}
	/**
	 * Passing words to DFS class to build the ladder
	 * @param start is the starting word
	 * @param end is the ending word
	 * @return the wordladder for the 2 words
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) throws InterruptedException, ExecutionException {
		startWord = start;
		endWord = end;
		DepthFirstSearch dfs = new DepthFirstSearch(start, end);
		ladderExists = dfs.startDFS();
		return dfs.getLadder(start, end);
	}
	/**
	 * Passing words to BFS to build the ladder
	 * @param start is starting word
	 * @param end is ending word
	 * @return the wordladder for the 2 words
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		startWord = start;
		endWord = end;
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		ladderExists = bfs.startBFS(start, end);
		return bfs.getLadder(start, end);
	}    
    
    /**
     * Taking apart the input file and making it into a set of strings
     * @return set of words from dictionary
     */
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
	/**
	 * To print the ladder that is given
	 * @param ladder passing the list of words to be printed
	 */
	public static void printLadder(ArrayList<String> ladder) {
		Ladder ladderToPrint = new Ladder(ladder);
		ladderToPrint.printLadder(startWord, endWord, ladderExists);
	}
    /**
     * checks for duplicate words in the list of words
     * @param ladder is the list of words
     * @return true if there is a duplicate, else false
     */
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
 