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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SystemTest {
	@BeforeClass
	public static void setUp() throws Exception {
		Main.initialize();
	}
	
	@Test
	public void testBFSvsBFS() throws InterruptedException, ExecutionException {
		Long start;
		Long end;
		String time;
		ArrayList<String> tmp;		
		
		//RANDOM TESTING
		int numItems = 1000;
		String[][] sizes = new String[numItems][6];
		Random r = new Random();
		int bfs1Size = 0;
		int bfs2Size = 0;
		String word1;
		String word2;
		Long bfs1Time = (long) 0;
		Long bfs2Time = (long) 0;
		for(int i = 0 ; i < numItems; i ++) {
			word1 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			word2 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			sizes[i][0] = word1;
			sizes[i][1] = word2;
			
			start = System.nanoTime();
			tmp = Main.getWordLadderBFS(word1, word2);
			end = System.nanoTime();
			time = Long.toString(end-start);
			bfs1Size = tmp.size();
			sizes[i][2] = String.valueOf(bfs1Size);
			sizes[i][4] = time;
			bfs1Time += (end-start);
			
			start = System.nanoTime();
			tmp = Main.getWordLadderBFS(word2, word1);
			end = System.nanoTime();
			time = Long.toString(end-start);		
			bfs2Size = tmp.size();	
			sizes[i][3] = String.valueOf(bfs2Size);
			sizes[i][5] = time;
			bfs2Time += (end-start);			
			assertTrue(bfs1Size == bfs2Size);
		}
		System.out.println("Word1\tWord2\tB1size\tB2size\tB1time\tB2time");
		for(int i = 0 ; i < numItems ; i ++) {
			for(int j = 0 ; j < 6; j++) {
				System.out.print(sizes[i][j] + '\t');
			}
			System.out.print("\n");
		}		
		System.out.println("\n\nAvg BFS1 Time: " + bfs1Time/numItems + "\nAvg BFS2 Time: " + bfs2Time/numItems);
		
	}
	
	@Test
	public void testDFSvsBFS() throws InterruptedException, ExecutionException {
		Long start;
		Long end;
		String time;
		ArrayList<String> tmp;
		
		
		//RANDOM TESTING
		int numItems = 1000;
		String[][] sizes = new String[numItems][6];
		Random r = new Random();
		int bfsSize = 0;
		int dfsSize = 0;
		String word1;
		String word2;
		Long dfsTime = (long) 0;
		Long bfsTime = (long) 0;
		for(int i = 0 ; i < numItems; i ++) {
			word1 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			word2 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			sizes[i][0] = word1;
			sizes[i][1] = word2;
			
			start = System.nanoTime();
			tmp = Main.getWordLadderDFS(word1, word2);
			end = System.nanoTime();
			Main.printLadder(tmp);
			time = Long.toString(end-start);
			dfsSize = tmp.size();
			sizes[i][2] = String.valueOf(dfsSize);
			sizes[i][4] = time;
			dfsTime += (end-start);
			
			start = System.nanoTime();
			tmp = Main.getWordLadderBFS(word1, word2);
			end = System.nanoTime();
			Main.printLadder(tmp);
			time = Long.toString(end-start);		
			bfsSize = tmp.size();
			sizes[i][3] = String.valueOf(bfsSize);
			sizes[i][5] = time;
			bfsTime += (end-start);			
			assertTrue(bfsSize <= dfsSize);
		}
		System.out.println("Word1\tWord2\tDsize\tBsize\tDtime\tBtime");
		for(int i = 0 ; i < numItems ; i ++) {
			for(int j = 0 ; j < 6; j++) {
				System.out.print(sizes[i][j] + '\t');
			}
			System.out.print("\n");
		}		
		System.out.println("\n\nAvg BFS Time: " + bfsTime/numItems + "\nAvg DFS Time: " + dfsTime/numItems);		
	}
	//Test for if they are both zero or both not zero
	@Test
	public void testDFSvsBFS2() throws InterruptedException, ExecutionException{
		Long start;
		Long end;
		String time;
		ArrayList<String> tmp;
		
		
		//RANDOM TESTING
		int numItems = 1000;
		String[][] sizes = new String[numItems][6];
		Random r = new Random();
		int bfsSize = 0;
		int dfsSize = 0;
		String word1;
		String word2;
		Long dfsTime = (long) 0;
		Long bfsTime = (long) 0;
		for(int i = 0 ; i < numItems; i ++) {
			word1 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			word2 = Words.dictionary.get(r.nextInt(Words.dictionary.size()));
			sizes[i][0] = word1;
			sizes[i][1] = word2;
			
			start = System.nanoTime();
			tmp = Main.getWordLadderDFS(word1, word2);
			end = System.nanoTime();
			time = Long.toString(end-start);
			dfsSize = tmp.size();
			sizes[i][2] = String.valueOf(dfsSize);
			sizes[i][4] = time;
			dfsTime += (end-start);
			
			start = System.nanoTime();
			tmp = Main.getWordLadderBFS(word1, word2);
			end = System.nanoTime();
			time = Long.toString(end-start);		
			bfsSize = tmp.size();	
			sizes[i][3] = String.valueOf(bfsSize);
			sizes[i][5] = time;
			bfsTime += (end-start);
			if(bfsSize == 0 || dfsSize == 0) {
				assertTrue(bfsSize == dfsSize);
			}
		}
		System.out.println("Word1\tWord2\tDsize\tBsize\tDtime\tBtime");
		for(int i = 0 ; i < numItems ; i ++) {
			for(int j = 0 ; j < 6; j++) {
				System.out.print(sizes[i][j] + '\t');
			}
			System.out.print("\n");
		}		
		System.out.println("\n\nAvg BFS Time: " + bfsTime/numItems + "\nAvg DFS Time: " + dfsTime/numItems);		
	}
	
	@Test
	public void testKeyboardInput() throws Exception {
		String[] args = new String[0];
		Main.main(args);
	}
	
	//@Test
	public void testFileInput() throws Exception {
		String inFile = "fileInput.txt";
		String outFile = "fileOutput.txt";
		String[] args = {inFile, outFile};
		PrintWriter writer = new PrintWriter(args[1], "UTF-8");
		writer.close();
		Main.main(args);
		File output = new File("fileOutput.txt");
		File outputKey = new File("fileOutputKey.txt");
		byte[] out = Files.readAllBytes(output.toPath());
		byte[] outKey = Files.readAllBytes(outputKey.toPath());
		assertTrue(Arrays.equals(outKey, out));		
	}	
	
	@Test
	public void testZeroRungLadder() throws Exception {
		ArrayList<String> bfs = Main.getWordLadderBFS("gazer", "gager");
		ArrayList<String> dfs = Main.getWordLadderDFS("gazer", "gager");
		Main.printLadder(dfs);
		Main.printLadder(bfs);
	}
}
