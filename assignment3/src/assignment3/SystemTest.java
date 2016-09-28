package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class SystemTest {
	@Before
	public void setUp() throws Exception {
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
			tmp = Main.getWordLadderBFS(word1, word2);
			end = System.nanoTime();
			time = Long.toString(end-start);
			dfsSize = tmp.size();
			sizes[i][2] = String.valueOf(dfsSize);
			sizes[i][4] = time;
			dfsTime += (end-start);
			
			start = System.nanoTime();
			tmp = Main.getWordLadderBFS(word2, word1);
			end = System.nanoTime();
			time = Long.toString(end-start);		
			bfsSize = tmp.size();	
			sizes[i][3] = String.valueOf(bfsSize);
			sizes[i][5] = time;
			bfsTime += (end-start);			
			assertTrue(bfsSize == dfsSize);
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
			assertTrue(bfsSize == dfsSize);
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

}
