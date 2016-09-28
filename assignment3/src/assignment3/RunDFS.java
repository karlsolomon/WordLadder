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
