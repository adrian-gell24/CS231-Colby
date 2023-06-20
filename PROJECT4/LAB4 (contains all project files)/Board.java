import java.io.*;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Board extends Sudoku{

  private Cell[][] Board;
  private int SIZE = 9;
  public boolean finished = false;

  /**
   * Retturns whether the board has been filled completely
   * @return a boolean corresponding with whether the board is filled or not
   */
  public boolean getfinished(){
    return finished;
  }

  /**
   * Changes whether the board parameter is finished or not
   * @param newfinished a boolean corresponding with whether the board is filled or not
   */
  public void setfinished(boolean newfinished){
    finished = newfinished;
  }

  /**
   * Returns the highest value that the board can take
   * @return SIZE
   */
  public int getSize(){
    return SIZE;
  }

  /**
   * Set the highest value that the board can take to a new value
   * @param newsize a new integer represting the highest value the board can take 
   */
  public void setSize(int newsize){
    SIZE = newsize;
  }

  /**
   * Return the number of columns that the Sudoku board has
   * @return an integer representing the number of columns the board has
   */
  public int getCols(){
    return Board.length;
  }

  /**
   * Return the number of rows that the Sudoku board has
   * @return an integer representing the number of rows the board has
   */
  public int getRows(){
    return Board[0].length;
  }

  /**
   * Returns the Cell at location r, c.
   * @param r integer representing the row index of the Cell
   * @param c integer representing the column index of the Cell
   * @return the Cell
   */
  public Cell get(int r, int c){
    return Board[r][c];
  }

  /**
   * Returns whether the Cell at r, c, is locked.
   * @param r integer representing the row index of the Cell
   * @param c integer representing the column index of the Cell
   * @return boolean corresponding with whether the Cell is locked or not
   */
  public boolean isLocked(int r, int c){
    return Board[r][c].isLocked();
  }

  /**
   * Returns the number of locked Cells on the board.
   * @return an integer counter representing the number of locked Cells on the board
   */
  public int numLocked(){
    int counter = 0;
    for (int r = 0; r < SIZE; r ++){
      for (int c = 0; c < SIZE; c++){
        if (Board[r][c].isLocked() == true)
        counter += 1;
      }
    }
    return counter;
  }

  /**
   * Returns the value at Cell r, c
   * @param r integer representing the row index of the Cell
   * @param c integer representing the column index of the Cell
   * @return the value that the Cell holds
   */
  public int value(int r, int c){
    return Board[r][c].getValue();
  }

  /**
   * Sets the value of the Cell r, c
   * @param r integer representing the row index of the Cell
   * @param c integer representing the column index of the Cell
   * @param value integer representing the new value to be held by the Cell
   */
  public void set(int r, int c, int value){
    Board[r][c].setValue(value);
  }

  /**
   * Sets the value and locked fields of the Cell at r, c.
   * @param r integer representing the row index of the Cell
   * @param c integer representing the column index of the Cell
   * @param value integer representing the new value to be held by the Cell
   * @param locked boolean corresponding with whether the Cell is locked or not 
   */
  public void set(int r, int c, int value, boolean locked){
    Board[r][c].setValue(value);
    Board[r][c].setLocked(locked);
  }

  /**
   * Checks the number of occurrences of a value within a certain row of the Board
   * @param row integer representing the row to be checked
   * @param value integer representing the value to be checked
   * @return the number of occurrences of the value
   */
  public int rowCheck(int row, int value){
    int occurrence = 0;
    for (int i = 0; i < SIZE; i++){
      if (value == Board[row][i].getValue()){
        occurrence++;
      }
    }

    return occurrence;
  }

  /**
   * Checks the number of occurrences of a value within a certain column of the Board
   * @param col integer representing the column to be checked
   * @param value integer representing the value to be checked
   * @return the number of occurrences of the value
   */
  public int colCheck(int col, int value){
    int occurrence = 0;
    for (int i = 0; i < SIZE; i++){
      if (value == Board[i][col].getValue()){
        occurrence++;
      }
    }

    return occurrence;
  }

  /**
   * Checks the number of occurrences of a value within a certain square on the Board
   * @param row integer representing a row of the board
   * @param col integer representing a column of the board
   * @param value integer representing the value to be checked 
   * @return the number of occurrences of the value
   */
  public int boxCheck(int row, int col, int value){
    int occurrence = 0;
    int r = row - row % 3;
    int c = col - col % 3;
    for (int i = r; i < r + 3; i++){
      for (int j = c; j < c + 3; j++){
        if (value == Board[i][j].getValue()){
          occurrence++;
        }
      }
    }

    return occurrence;
  }


  /**
   * Checks whether a certain value that has been placed results in a violation of row, column, and box checks
   * @param row integer representing a row of the board
   * @param col integer representing a column of the board
   * @param value integer representing the value to be checked 
   * @return a boolean corresponding with whether there is more than one of a given value (false) or there is not (true)
   */
  public boolean validValue(int row, int col, int value){
    if (value > 9 || value < 1){
      return false;
    }
    else if (rowCheck(row, value) > 1 || colCheck(col, value) > 1 || boxCheck(row, col, value) > 1){
      return false;
    }
    
    return true;
  }

  /**
   * Checks whether it is possible to place a value at a certain place on the board
   * @param row integer representing a row of the board
   * @param col integer representing a column of the board
   * @param value integer representing the value to be checked 
   * @return a boolean corresponding with whether there currently is a certain value in the row, column or square
   */
  public boolean possibleValue(int row, int col, int value){
    if (value > 9 || value < 1){
      return false;
    }
    else if (rowCheck(row, value) > 0 || colCheck(col, value) > 0 || boxCheck(row, col, value) > 0){
      return false;
    }
    
    return true;
  }

  /**
   * Constructor that creates a new 2D array of Cells that is Board.Size by Board.Size
   * Also initializes each location in the grid with a new Cell with value 0.
   */
  public void reset(){
    // create new 2D array of Cells
    Board = new Cell[SIZE][SIZE];

    // fill 2d array with Cells (Cell class initializes Cells with value 0)
    for (int r = 0; r < SIZE; r ++){
      for (int c = 0; c < SIZE; c++){
        Board[r][c] = new Cell(r, c, 0);
      }
    }
  }

  /**
   * Constructor that takes a String filename and calls read on that filename to fill the board
   * @param filename the file name
   */
  public Board(String filename){
    reset();
    read(filename);
  }

  /**
   * Read a given filename and fill out a Sudoku board based on its contents
   * @param filename the file to be read
   * @return a boolean value based on whether the file has been read or not
   */
  public boolean read(String filename) {
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      FileReader fr = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      BufferedReader br = new BufferedReader(fr);

      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line = br.readLine();

      int curRow = 0;

      // start a while loop that loops while line isn't null
      while(line != null){
        // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
        String[] splitline = line.split("[ ]+");

        for (int col = 0; col < SIZE; col++){
          set(curRow, col, Integer.parseInt(splitline[col]), true);
        }

        // increment which row currently at
        curRow++;

        // assign to line the result of calling the readLine method of your BufferedReader object.
        line = br.readLine();
      }

      // call the close method of the BufferedReader
      br.close();
      return true;
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    return false;
  }

  /**
   * Method that takes a certain value and randomly chooses certain initial values to be changed from empty to a locked value
   * Follows rules given by checks
   * @param cellsLocked integer representing the number of Cells to be locked
   */
  public void Generate(int cellsLocked){
    reset();
    Random ran = new Random();
    int i = 0;
    while (i != cellsLocked){
      int row = ran.nextInt(0, 9);
      int col = ran.nextInt(0, 9);
      int value = ran.nextInt(1, 10);

      if (possibleValue(row, col, value) && !(Board[row][col].isLocked())){
        set(row, col, value, true);
        i++;
      }
    }
  }

  /**
   * Generates a multi-line string representation of the board with spaces that separate the 9x9 board into 3x3 blocks
   * @return the grid as a string
   */
  public String toString(){
    String grid = new String();
    for (int r = 0; r < SIZE; r ++){
      for (int c = 0; c < SIZE; c++){
        grid += Board[r][c];
        if ((c + 1) % 3 == 0) grid += " ";
      }
      grid += "\n";
      if ((r + 1) % 3 == 0) grid += "\n";
    }
    return "" + grid;
  }


  /**
   * Returns whether how the board is filled corresponds with a solution of the board
   * @return a boolean corresponding with whether the board has been solved or not.
   */
  public boolean validSolution(){

    int occurrences = 0;

    for (int i = 0; i<SIZE; i++){
      for (int j = 0; j<SIZE; j++){
        if (validValue(i, j, Board[i][j].getValue())){
          occurrences++;
          System.out.println(occurrences);
        }
      }
    }

    if (occurrences != 81){
      return false;
    }

    return true;
  }

  /**
   * Method that takes the current board and draws it on a panel
   * @param g a Graphics object that can change colors and locations and draw on the Landscape. 
   * @param scale 
   */
  public void draw(Graphics g, int scale){
    for(int i = 0; i<9; i++){
        for(int j = 0; j<9; j++){
            Board[i][j].draw(g, j*scale+5, i*scale+10, scale);
        }
    } if(finished){
        if(validSolution()){
            g.setColor(new Color(0, 127, 0));
            g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
        } else {
            g.setColor(new Color(127, 0, 0));
            g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
        }
    }
  }

  public static void main(String[] args){
    Board game = new Board("board1.txt");
    System.out.println(game.validSolution());
    Board gamesolved = new Board("board1Solved.txt");
    System.out.println(gamesolved.validSolution());
    game.Generate(10);
    System.out.println(game.toString());

    // Board game = new Board("board1.txt");
    // game.reset();
    // game.solve();
    // System.out.println(game.toString());
    
    // System.out.println(game.read("board1.txt"));
    // System.out.println(game.toString());

    // System.out.println(game.toString());
    // System.out.println(game.getRows());
    // System.out.println(game.getCols());
    // System.out.println(game.numLocked());
    // game.set(3, 7, 9);
    // System.out.println(game.value(3, 7));
    // game.set(6, 6, 3, true);
    // game.set(8, 4, 2, true);
    // game.set(7, 5, 8, true);
    // System.out.println(game.numLocked());
    
    // if (args.length < 1){
    //   System.out.println("Please provide a command line argument");
    // }
    // else {
    //   game.read(args[0]);
    // }
    
    // if (game.getCols() != 9){
    //   System.out.println("Error in getCols() method");
    // }
    // else if (game.getCols() == 9){
    //   System.out.println("getCols() method working");
    // }

    // if (game.getRows() != 9){
    //   System.out.println("Error in getRows() method");
    // }
    // else if (game.getRows() == 9){
    //   System.out.println("getRows() method working");
    // }

    // if (game.getCols() != 9){
    //   System.out.println("Error in getCols() method");
    // }
    // else if (game.getCols() == 9){
    //   System.out.println("getCols() method working");
    // }
  }
}