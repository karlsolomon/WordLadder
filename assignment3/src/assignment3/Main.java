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
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {

		Random r = new Random();
		String word1;
		String word2;
		
		initialize();
		word1 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
		word2 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
		
		//Scanner in = new Scanner(System.in);
		System.out.println("first word: " + word1);
		//String word1 = in.nextLine();
		System.out.println("second word: " + word2);
		//String word2 = in.nextLine();
		System.out.println("\nForward:");
		getWordLadderDFS(word1, word2);
		System.out.println("\nReverse:");
		getWordLadderDFS(word2, word1);
		
//		Scanner kb;	// input Scanner for commands
//		PrintStream ps;	// output file
//		// If arguments are specified, read/write from/to files instead of Std IO.
//		if (args.length != 0) {
//			kb = new Scanner(new File(args[0]));
//			ps = new PrintStream(new File(args[1]));
//			System.setOut(ps);			// redirect output to ps
//		} else {
//			kb = new Scanner(System.in);// default from Stdin
//			ps = System.out;			// default to Stdout
//		}
		//initialize();
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		Words.init();
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		//TODO
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		dfs.startDFS(start, end);
		return dfs.getLadder();

	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		// Return empty list if no ladder.
		return null; // replace this line later with real return
	}
}
