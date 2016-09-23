package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words {
	public static ArrayList<Integer> searched = new ArrayList<Integer>();
	public static ArrayList<String> dictionary = new ArrayList<String>();
	public static ArrayList<ArrayList<Integer>> linkedList = new ArrayList<ArrayList<Integer>>();
	public static int[][] matrix;
	private static File shortDict = new File("five_letter_words.txt");
	private static Scanner in;
	public static void init(){
		try{
			in = new Scanner(shortDict);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long base = System.currentTimeMillis();
		long a = base;
		System.out.println("Dictionary Start: " + 0);
		makeDictionary();
		
		
		base = a;
		a = System.currentTimeMillis();
		System.out.println("Matrix Start: " + (a-base));
		makeMatrix();
		
		
		base = a;
		a = System.currentTimeMillis();
		System.out.println("LinkedList Start: " + (a-base));
		makeLinkedList();
		
		
		base = a;
		a = System.currentTimeMillis();
		System.out.println("End: " + (a-base));
	}
	
	private static void makeDictionary() {
		while(in.hasNext()) {
			dictionary.add(in.next());
		}
	}
	
	private static void makeMatrix(){
		matrix = new int[dictionary.size()][dictionary.size()];

		for(int i = 0; i < dictionary.size(); i ++) {
			for(int j = 0; j < dictionary.size(); j++) {
				matrix[i][j] = commonNumberOfLetters(dictionary.get(i), dictionary.get(j));
			}
		}
	}
	
	private static void makeLinkedList(){
		for(int i = 0; i < dictionary.size(); i ++) {
			linkedList.add(getList(i));
		}
	}
	
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
	
	private static ArrayList<Integer> getList(int row){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int column = 0; column < dictionary.size(); column++) {
			if(matrix[row][column] == 4)
				list.add(column);
		}
		return list;
	}
	
	public static ArrayList<String> toStringArray(ArrayList<Integer> intList) {
		ArrayList<String> stringList = new ArrayList<String>();
		for(Integer i : intList) {
			stringList.add(dictionary.get(i));
		}
		return stringList;	
	}
	
	public static int getNumberOfCommonLetters(int a, int b){
		return matrix[a][b];
	}
	
}
