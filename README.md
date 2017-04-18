# GameOfLife
Conway's Game of Life
This program runs an algorithm for Conway's Game of Life. The rules of which can be seen here
https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life. The program assumes a rectangular input of '.' (dead cells) and
'O' (live cells) in a .txt file (see example test files in 'src\test\resources'). The program will take the initial
input and evaluate the next generation of cells based on the rules of the game. Both the initial grid and the next
generation grid are displayed as console output to the user. The program will then ask the user if they want to continue
to evaluate the next generation based of the newly generated grid state until the user says 'N/n'. This program supports
any dimension of rectangular grids to be evaluated by the rules of the game. For testing convince, the program also
provides an optional randomGridGenerator() method to generate arbitrary files (see javadocs for more on usage).

Running the Application
Note: This application used Java 8, but may be compatible with earlier versions
IDE:
If using an IDE such as IntelliJ or Eclipse, simple run the GameOfLife.java class with command line arguments set to
either the absolute file path to the grid text file, or the relative file path text file is in the src file (ex.
src\test\resources\thisIsYourGridFile.txt). The JUnit test cases are round in GridTest.java and can be run in the same
way with no need for arguments. Note: make sure you add JUnit4 as an external library to the project.

Cmd:
To run on command line, navigate the the file location of GameOfLife.java. Assuming java path is set, run
'javac GameOfLife.java' to compile. Once compiled, simply run 'java GameOfLife <filePathHere>', where <filePathHere> can
be either the relative or absolute file path to the grid file desired to be entered. Note: with the relative file path,
you will have to add "..\" (ex: ..\..\test\resources\gridExample.txt) to navigate to the correct location in the src
file assuming it wasn't placed in the same location of GameOfLife.java.