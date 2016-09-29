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

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
	/**
	 * Initializing the tests so there isn't a heap overflow
	 * */
	@Before
	public void setUp() throws Exception {
		Main.initialize();
	}


	/**
	 * Testing Parse to verify it returns the right output for file and keyboard inputs
	 * @throws FileNotFoundException 
	 * */
	//@Test
	public void testParse() throws FileNotFoundException {
		Scanner s1 = new Scanner(System.in);
		ArrayList<String> l1 = Main.parse(s1);		
		Scanner s2 = new Scanner(new File("InStuff.txt"));
		ArrayList<String> l2 = Main.parse(s2);
		if(!l1.get(0).equals("windy")){
			fail("You dun goof'd.v1");
		}
		if(!l1.get(1).equals("riles")){
			fail("You dun goof'd.v2");
		}
		if(!l2.get(0).equals("windy")){
			fail("You dun goof'd.v3");
		}
		if(!l2.get(1).equals("riles")){
			fail("You dun goof'd.v4");
		}
		
	}
//windy-winey-wined-wiled-riled-riley-riles
	/**
	 * Testing for a known case(above in a comment,
	 * to see if the ladder is what is supposed to be output.
	 * @throws InterruptedException if one thread is unexpectedly terminated
	 * @throws ExecutionException to allow the method to run
	 */
	@Test
	public void testGetWordLadderDFS() throws InterruptedException, ExecutionException {
		String start = "windy";
		String end = "riles";
		ArrayList<String> output = Main.getWordLadderDFS(start,end);
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("winey");
		expect.add("wined");
		expect.add("wiled");
		expect.add("riled");
		expect.add("riley");
		boolean b = true;
		for(int i = 0; i < 5; i++){
			if(!expect.get(i).equals(output.get(i))){
				b = false;
			}
		}
		assertTrue(b == true);
	}
	//windy-winds-wilds-wiles-riles
	/**
	 * Testing for a known case(above in a comment,
	 * to see if the ladder is what is supposed to be output.
	 * */
	@Test
	public void testGetWordLadderBFS() {
		String start = "windy";
		String end = "riles";
		ArrayList<String> output = Main.getWordLadderBFS(start,end);
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("winds");
		expect.add("wilds");
		expect.add("wiles");
		boolean b = true;
		for(int i = 0; i < 3; i++){
			if(!expect.get(i).equals(output.get(i))){
				b = false;
			}
		}
		assertTrue(b == true);
		
	}
	/**
	 * Testing via visuals to see that printLadder returns the correct output.
	 * @throws InterruptedException if one thread is unexpectedly terminated
	 * @throws ExecutionException to allow the method to run
	 * */
	//@Test
	public void testPrintLadder() throws InterruptedException, ExecutionException{
		ArrayList<String> dfs = Main.getWordLadderDFS("windy","riles");
		ArrayList<String> bfs = Main.getWordLadderBFS("windy","riles");
		Main.printLadder(dfs);
		Main.printLadder(bfs);
	}

}
