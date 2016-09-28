package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BreadthFirstSearchTest {

	@Before
	public void setUp() throws Exception {
		Main.initialize();
	}
	@Test
	public void testStartBFS() {
		//Compare backwards and Forwards for BFS
		BreadthFirstSearch bfs1 = new BreadthFirstSearch();
		BreadthFirstSearch bfs2 = new BreadthFirstSearch();
		bfs1.startBFS("rains", "spunk");
		ArrayList<String> l1 = bfs1.getLadder();
		bfs2.startBFS("spunk", "rains");
		ArrayList<String> l2 = bfs2.getLadder();
		assertTrue(l1.size() == l2.size());
	}
}
