package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Words contains initialization code for WordLadder project.
 * This creates a dictionary, linked list, and a matrix for quick look up.
 * @author KSolomon
 *
 */
public final class Words {
	public static ArrayList<Integer> searched = new ArrayList<Integer>();
	public static ArrayList<String> dictionary = new ArrayList<String>();
	public static ArrayList<ArrayList<Integer>> linkedList = new ArrayList<ArrayList<Integer>>();
	public static int[][] matrix;
	private static File shortDict;
	private static Scanner in;
	public static void init(){
		try{
			in = new Scanner(shortDict);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		makeDictionary();
		makeMatrix();
		makeLinkedList();
		in.close();
	}
	
	/**
	 * Sets the dictionary filename
	 * @param fileName name of dictionary (i.e. "five_letter_words.txt")
	 */
	public static void setFile(String fileName) {
		shortDict = new File(fileName);
	}
	
	/**
	 * Creates a dictionary of all strings
	 */
	private static void makeDictionary() {
		String word;
		while(in.hasNext()) {
			word = in.next().toLowerCase();
			dictionary.add(word);
		}
	}
	
	/**
	 * Creates an adjacency matrix where each word is represented by its index in the dictionary.
	 * The cell at the intersection between two words is represented as the number of letters they have in common.
	 */
	private static void makeMatrix(){
		matrix = new int[dictionary.size()][dictionary.size()];

		for(int i = 0; i < dictionary.size(); i ++) {
			for(int j = 0; j < dictionary.size(); j++) {
				matrix[i][j] = commonNumberOfLetters(dictionary.get(i), dictionary.get(j));
			}
		}
	}
	
	/**
	 * This is an adjacency List.
	 * Any word which is only one letter different is added as a link
	 */
	private static void makeLinkedList(){
		for(int i = 0; i < dictionary.size(); i ++) {
			linkedList.add(getList(i));
		}
	}
	
	/**
	 * Returns the number of letters that string a and string b have in common in the same spots
	 * @param a first string
	 * @param b second string
	 * @return number of letters that match between a and b. (i.e. Karl Kyle = 1)
	 */
	private static int commonNumberOfLetters(String a, String b){
		int commonLettersCounter = 0;
		char[] aChar = a.toCharArray();
		char[] bChar = b.toCharArray();
		for(int i = 0 ; i < a.length(); i++) {
			if(aChar[i] == bChar[i])
				commonLettersCounter++;
		}
		return commonLettersCounter;
	}
	
	/**
	 * Return the words which are nearly equal (one letter away) to the word represented by row
	 * @param row index of word of interest as found in the dictionary
	 * @return array of words which are one word away from the word represented by row
	 */
	private static ArrayList<Integer> getList(int row){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int column = 0; column < dictionary.size(); column++) {
			if(matrix[row][column] == 4)
				list.add(column);
		}
		return list;
	}
	
	/**
	 * Converts arraylist of integers to an arraylist of strings where each integer is the index of the string in the dictionary
	 * @param intList array of integers
	 * @return array of strings where each string was passed in as it's integer index in the dictionary
	 */
	public static ArrayList<String> toStringArray(ArrayList<Integer> intList) {
		ArrayList<String> stringList = new ArrayList<String>();
		for(Integer i : intList) {
			stringList.add(dictionary.get(i));
		}
		return stringList;	
	}
	
	/**
	 * Return the number of 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getNumberOfCommonLetters(int a, int b){
		return matrix[a][b];
	}
	
	
}
