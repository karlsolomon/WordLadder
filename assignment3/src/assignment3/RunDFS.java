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

public class RunDFS implements Runnable {
     boolean forward;
     DepthFirstSearch dfs;
     
     /**
      * True = forward
      * False = backward
      * @param direction
      */
     RunDFS(boolean runForward, DepthFirstSearch dfs) {
         this.forward = runForward;
         this.dfs = dfs;
     }

	@Override
	public void run() {
		if(this.forward) {
			dfs.forwardDFS();
		}
		else {
			dfs.reverseDFS();
		}
	}
}
