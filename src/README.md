# gwv-finalProject (15-puzzle)
The final project for the "Grundlagen der Wissensverarbeitungs" lecture at the university of Hamburg.

The 15-puzzle problem, consists of a 4 x 4 board with 15 sliding tiles, numbered 1 to 15,
and one blank tile. There are four operators, which move the blank up, down, left, and
right.
Write a program that can take any current state of the puzzle as an input and find the
shortest way to solve the puzzle (if a solution exists). 

Use a heuristic search algorithm for the problem and devise a suitable heuristic function.
Your program should be able to do the following:
1. Accept input
2. Find a solution (if there is a solution)
3. Output the solution, so that it is human understandable (text output is fine)

Optional: Turn your program into a game
1. Provide some form of GUI so that the user can slide the tiles around.
2. Generate random start configurations for the user to solve, but make sure these
configurations can actually be solved.
3. Provide the user with a hint on his next step, if the user clicks a ’hint’ button.
4. Judge the user’s performance by comparing the number of steps the user took to
solve the puzzle with the minimum number of steps necessary for solving the puzzle.
5. You can also offer more complex puzzles with more tiles (5 x 5 and more).
6. You can use a picture instead of numbers.
