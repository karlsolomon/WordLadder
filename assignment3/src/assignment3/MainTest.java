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

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	@Before
	public void setUp() throws Exception {
		Main.initialize();
	}



	@Test
	public void testParse() {
		Scanner s1 = new Scanner(System.in);
		ArrayList<String> l1 = Main.parse(s1);
		Scanner s2 = new Scanner("InStuff.txt");
		ArrayList<String> l2 = Main.parse(s2);
		if(l1.get(0) != "windy"){
			fail("You dun goof'd.v1");
		}
		if(l1.get(1) != "riles"){
			fail("You dun goof'd.v2");
		}
		if(l2.get(0) != "windy"){
			fail("You dun goof'd.v3");
		}
		if(l2.get(1) != "riles"){
			fail("You dun goof'd.v4");
		}
		
	}
//windy-rindy-rinds-rings-rinks-ricks-rices-riles
	@Test
	public void testGetWordLadderDFS() throws InterruptedException, ExecutionException {
		String start = "windy";
		String end = "riles";
		ArrayList<String> output = Main.getWordLadderDFS(start,end);
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("windy");
		expect.add("rindy");
		expect.add("rinds");
		expect.add("rings");
		expect.add("rinks");
		expect.add("ricks");
		expect.add("rices");
		expect.add("riles");
		for(int i = 0; i < 8; i++){
			if(expect.get(i)!=output.get(i)){
				fail("You dun goof'd");
			}
		}
	}
	//windy-winds-wilds-wiles-riles
	@Test
	public void testGetWordLadderBFS() {
		String start = "windy";
		String end = "riles";
		ArrayList<String> output = Main.getWordLadderBFS(start,end);
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("windy");
		expect.add("winds");
		expect.add("wilds");
		expect.add("wiles");
		expect.add("riles");
		for(int i = 0; i < 5; i++){
			if(expect.get(i)!=output.get(i)){
				fail("You dun goof'd");
			}
		}
	}
	/**
	 * 
	 * */
	@Test
	public void testPrintLadder() throws InterruptedException, ExecutionException{
		ArrayList<String> dfs = Main.getWordLadderDFS("windy","riles");
		ArrayList<String> bfs = Main.getWordLadderBFS("windy","riles");
		Main.printLadder(dfs);
		Main.printLadder(bfs);
	}

}
