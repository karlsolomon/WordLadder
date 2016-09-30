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
      * Helper class which enables multithreading using the Runnable implementation.
      * @param runForward will execute dfs.forwardDFS() (the expected search direction) if true. Will run the reverse direction if false
      * i.e. if searching start = "java", end = "code" forward will search java->code, but reverse will search code->java 
      * @param dfs The DepthFirstSearch object to run in
      */
     RunDFS(boolean runForward, DepthFirstSearch dfs) {
         this.forward = runForward;
         this.dfs = dfs;
     }

     /**
      * main method for this thread
      */
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
