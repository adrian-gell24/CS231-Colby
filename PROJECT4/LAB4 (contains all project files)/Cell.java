import java.awt.Graphics;
import java.awt.Color;

public class Cell {

    // class fields
    private int row_index;
    private int col_index;
    private int value;
    private boolean lock;

    // constructors
    /**
     * Initialize all values to 0 or false.
     */
    public Cell(){
        this.value = 0;
    }

    /**
     * Initialize the row, column, and value fields to the given parameter values. Set the locked field to false;
     * @param row the row location of the Cell
     * @param col the column location of the Cell 
     * @param value the value held by the Cell
     */
    public Cell(int row, int col, int value){
        this.row_index = row;
        this.col_index = col;
        this.value = value;
        this.lock = false;
    }
    
    /**
     * initialize all of the Cell fields given the parameters.
     * @param row the row location of the Cell
     * @param col the column location of the Cell
     * @param value the value held by the Cell
     * @param locked whether the value of the Cell can be changed
     */
    public Cell(int row, int col, int value, boolean locked){
        this.row_index = row;
        this.col_index = col;
        this.value = value;
        this.lock = locked;
    }

    // accessor and mutator functions
    /**
     * return the Cell's row index.
     * @return the Cell's row index.
     */
    public int getRow(){
        return this.row_index;
    }

    /**
     * Set the Cell's current row index to a new row index.
     * @param newrow the new row index
     */
    public void setRow(int newrow){
        this.row_index = newrow;
    }

    /**
     * return the Cell's column index.
     * @return Cell's column index
     */
    public int getCol(){
        return this.col_index;
    }

    /**
     * Set the Cell's current column index to a new column index.
     * @param newcol the new column index
     */
    public void setCol(int newcol){
        this.col_index = newcol;
    }

    /**
     * Return the Cell's value.
     * @return the value held by the Cell
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Set the Cell's value.
     * @param newval a new value for the Cell.
     */
    public void setValue(int newval){
        this.value = newval;
    }

    /**
     * Returns whether the Cell is locked
     * @return a boolean corresponding with whether the Cell is locked
     */
    public boolean isLocked(){
        return this.lock;
    }

    /**
     * Changes whether the Cell is locked or not
     * @param lock a boolean corresponding with whether the Cell is locked
     */
    public void setLocked(boolean lock){
        this.lock = lock;
    }

    /**
     * Returns the value of the Cell as a string
     */
    public String toString(){
        return "" + this.getValue();
    }

    /**
     * Draws the Cell's value at an x and y value corresponding with the row and column of the Cell.
     * Also change the Cell's color based on whether it is locked or not. 
     * @param g a Graphics object that can change colors and locations and draw on the Landscape.
     * @param x an integer specifying the x coordinate to draw at
     * @param y an integer specifying the y coordinate to draw at
     * @param scale 
     */
    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }
}