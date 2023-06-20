// import java.util.random.*;

public class Sudoku {
    Board board;
    LandscapeDisplay ld;

    public void Board(String[] args){
        this.board = new Board("board1.txt");
        this.board.Generate(Integer.parseInt(args[0]));
        ld = new LandscapeDisplay(board);
    }

    /**
     * Method that tries to solve the Sudoku board by using a stack and backtracking method
     * Draws the values added to the board onto the display at a speed elongated by delayvalue.
     * @param delayvalue integer representing how slowly each repaint method should be called
     * @return a boolean representing whether the board has been solved or not
     * @throws InterruptedException
     */
    public boolean solve(int delayvalue) throws InterruptedException{

        // Allocate a stack, initially empty
        CellStack cells = new CellStack(); // O(1)
        int delay = delayvalue;

        int unspecified = 0;
    
        // The totality of this block of code is O(n^2)
        for (int row = 0; row < this.board.getSize(); row++){
          for (int col = 0; col < this.board.getSize(); col++){
            if (!this.board.isLocked(row, col)){
              unspecified++;
            }
          }
        }

        //while the stack size is less than the number of unspecified cells
        // Each outer while loop is based on the number of cells within the Cell stack.
        // Therefore, this chunk of code has a O(n) run time. t
        while (cells.size() < unspecified){

            if (delay > 0)
                Thread.sleep(delay);
            if (ld != null)
                ld.repaint();
            
            //select the next cell to check (you'll be calling findNextCell, described below)
            Cell cellCheck = findNextCell();

            // if this cell has a valid value to try
            if (cellCheck == null){
                // while it is possible to backtrack (if the stack is nonempty)
                while (!cells.empty()){
                    // pop a cell off the stack
                    Cell backtrack = cells.pop();
                    int currVal = backtrack.getValue();
                    int currRow = backtrack.getRow();
                    int currCol = backtrack.getCol();
                    int newval = currVal;

                    // we know currVal works, but we already tried it, so we want to go from the next value
                    for (int i = currVal + 1; i < 10; i++){
                        if (this.board.possibleValue(currRow, currCol, i)){
                            newval = i;
                            break;
                        }
                    }
                    if (newval != currVal){
                        backtrack.setValue(newval);
                        cells.push(backtrack);
                        this.board.set(backtrack.getRow(), backtrack.getCol(), newval);
                        break;
                    }
                    else {
                        // System.out.println("currentRow: " + currRow + "\n" + "currentCol: " + currCol);
                        this.board.set(currRow, currCol, 0);
                    }
                }
                //if the stack size is 0 (no more backtracking possible)
                if (cells.empty()){
                    this.board.setfinished(true);
                    // return false: there is no solution
                    return false;
                }
            }
            else if (cellCheck.getValue() != 0){
                //push the cell onto the stack
                cells.push(cellCheck);
                //update the board
                this.board.set(cellCheck.getRow(), cellCheck.getCol(), cellCheck.getValue());
            }
        }
            
        this.board.setfinished(true);
        //return true: the board contains the solution        
        return true;
    }

    /**
     * Method that scans the board from the upper left corner of the Board until a Cell filled with a zero has been found
     * Returns a Cell holding the column, row, and possible value of a Cell previously filled with zero
     * @return next, the Cell
     */
    public Cell findNextCell(){

        for (int i = 0; i < this.board.getSize(); i++){
            for (int j = 0; j < this.board.getSize(); j++){
                if (this.board.value(i, j) == 0){
                    for (int value = 1; value < this.board.getSize() + 1; value++){
                        if (this.board.possibleValue(i, j, value)){
                            Cell next = board.get(i, j);
                            next.setRow(i);
                            next.setCol(j);
                            next.setValue(value);
                            return next;
                        }
                        else if (value == this.board.getSize() && !(this.board.possibleValue(i, j, value))){
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException{
        Board board = new Board("board1.txt");
        board.Board(args);
        board.solve(0);

        // System.out.println("here4" + board.toString());
    }
}
