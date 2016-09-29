# WordLadder

TODO:
0. Create some Try/Catch for DFS to avoid StackOverflow
1. Create JUnit Test cases for BFS & DFS
2. Fully Test the "short_dict.txt" dictionary. Make sure that every combination works as expected (DFS good)(BFS good)
3. Test Fringe Cases (Chris: I looked at the PDF and I dont think they will give us these cases, however I would check this maybe on Piazza)
	-Input only one word****(We do need this one)
4. Clean up main to accept Keyboard Inputs rather than randomly generated inputs
5. Parce needs to be written 
	-From what I would think and have found, I think parse is the method for reading the input dictionary text file and dividing it into the word arraylist
6. PDF and testing plans and such need to be written up


REQUIREMENTS:
2. One Scanner object connected to keyboard within main()
3. 5 methods in main
	- initialize
	- parse
	- getWordLadderDFS 
	- getWordLadderBFS
	- printLadder

JUnit Tests (good if each case is run separate)
-BFS vs. BFS is same length
-BFS vs. DFS BFS<DFS length
-BFS vs. DFS both find a ladder a.k.a. both should be zero or both should not be zero
-File Input TestCase Tests
-Keyboard Input Test
^If you run all of these at the same time we get a "java.lang.OutOfMemoryError: Java heap space". I.e. we use a lot of memory


CLEANUP:
We need to read through piazza and the document again and make sure that all of our methods and interface are acting exactly
as they say to. We don't want to lose stupid points b/c we failed to see some random detail.

