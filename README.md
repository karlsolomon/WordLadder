# WordLadder

TODO:
0. Create some Try/Catch for DFS to avoid StackOverflow
1. Create JUnit Test cases for BFS & DFS
2. Fully Test the "short_dict.txt" dictionary. Make sure that every combination works as expected (DFS good)(BFS good)
3. Test Fringe Cases (Chris: I looked at the PDF and I dont think they will give us these cases, however I would check this maybe on Piazza)
  a. Start and End word are the same word (DFS good)(BFS good)
	-This wont be tested
  b. One Word is not in the dictionary
	-This wont be tested
  c. Input word is not 5 letters long
	-This wont be tested
  d. Input only one word****
	-(We do need this one)
	
4. Clean up main to accept Keyboard Inputs rather than randomly generated inputs
5. Parce needs to be written 
	-From what I would think and have found, I think parse is the method for reading the input dictionary text file and dividing it into the word arraylist
6. PDF and testing plans and such need to be written up
7. Ladder needs to be fixed for two word difference. ie) Ladder is 0 rungs long for this

REQUIREMENTS:
2. One Scanner object connected to keyboard within main()
3. 5 methods in main
	- initialize
	- parse
	- getWordLadderDFS 
	- getWordLadderBFS
	- printLadder




