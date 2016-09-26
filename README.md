# WordLadder

BUGS: (sorted by class)
  DFS:
  BFS:
    - Infinite loop when Result is found
    - Ladder Index Out of bounds Exception
  Main:
  Ladder:
  

TODO:
0. Create some Try/Catch for DFS to avoid StackOverflow
1. Create JUnit Test cases for BFS & DFS
2. Fully Test the "short_dict.txt" dictionary. Make sure that every combination works as expected (DFS good)
3. Test Fringe Cases
  a. Start and End word are the same word (DFS good)
  b. One Word is not in the dictionary
  c. Input word is not 5 letters long
5. Change comparisons to InvariantCultureIgnoreCase or convert all inputs to the same case


REQUIREMENTS:
2. One Scanner object connected to keyboard within main()
3. 5 methods in main
	- initialize
	- parse
	- getWordLadderDFS 
	- getWordLadderBFS
	- printLadder
4. 