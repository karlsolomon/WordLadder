# WordLadder
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

This program is used for searching for a WordLadder between 2 words where each rung in the ladder is only one letter different from the previous, and as you go down the ladder, you are getting closer to the end word.
In this, we have BFS and DFS.
BFS is usually the fastest method to do this, while DFS can be faster, but only if you are lucky.
You may run either of these functions and get a returned WordLadder.

To run Breadth First Search:
javac Main.java
java Main <inputFile> <outputFile>

If outputFile does not exist we will create a file of the name to output to
If inputFile does not exist or if you do not specify either inputFile or outputFile we will interact with the console

If you would like to run DFS you may either change the call to BFS in main or you may call getWordLadderDFS separately.

Enjoy!



