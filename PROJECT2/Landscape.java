/**
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * File: Cell.java
 * Section: Lecture A, Lab D
 * Project: Conway's Game of Life
 * Course: CS231 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;
    private int nRows;
    private int nCols;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        nRows = rows;
        nCols = columns;
        landscape = new Cell[rows][columns];
        for (int r = 0; r < rows; r ++){
            for (int c = 0; c < columns; c++){
                landscape[r][c] = new Cell();
            }
        }
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        Random rand = new Random();
        nRows = rows;
        nCols = columns;
        landscape = new Cell[rows][columns];
        for (int r = 0; r < rows; r ++){
            for (int c = 0; c < columns; c++){
                // based on whether next random double is less than given chance or not
                // place an alive/dead cell at location in landscape
                landscape[r][c] = new Cell(rand.nextDouble() < chance); 
            }
        }
    }

    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */
    public void reset() {
        landscape = new Cell[nRows][nCols];
        for (int r = 0; r < nRows; r ++){
            for (int c = 0; c < nCols; c++){
                landscape[r][c] = new Cell();
            }
        }
    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        int numRows = landscape.length;
        return numRows;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        int numCols = landscape[0].length;
        return numCols;
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */
    public Cell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        String Landscape = new String();
        for (int r = 0; r < landscape.length; r++){
            for (int c = 0; c < landscape[r].length; c++){
                Landscape += landscape[r][c];
            }
            // make each row of landscape a new line in terminal
            Landscape += "\n";
        }
        return "" + Landscape;
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Cell> neighboringCells = new ArrayList<Cell>();

        for (int r = row - 1; r < row + 2; r++){
            for (int c = col - 1; c < col + 2; c++){
                // check whether neighboring location is whithin the landscape 
                // also do not include the specified Cell in the ArrayList
                if (r > -1 && r < nRows && c > -1 && c < nCols){
                    if (r != row || c != col){
                        neighboringCells.add(landscape[r][c]);
                    }
                }
            }
        }

        return neighboringCells;
    }

    /**
     * Advances the current Landscape by one step. 
     */
    public void advance() {
        // create temporary grid of the size of Landscape
        Cell[][] temporary = new Cell[nRows][nCols];

        // copy over the alive status of each cell in Landscape into the temporary grid
        for (int r = 0; r < nRows; r ++){
            for (int c = 0; c < nCols; c++){
                temporary[r][c] = new Cell(getCell(r, c).getAlive());
            }
        }
        
        // update each cell based on its list of neighbors within temporary grid
        for (int r = 0; r < nRows; r ++){
            for (int c = 0; c < nCols; c++){
                temporary[r][c].updateState(getNeighbors(r, c));
            }
        }

        // replace old landscape updated temporary grid
        landscape = temporary;
    }

    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                g.fillOval(x * scale, y * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {

        // Create landscape of stated rows and columns
        Landscape myLandscape = new Landscape(4, 4);

        // check each method in Landscape class on new landscape
        System.out.println("The number of rows: " + myLandscape.getRows()); 
        System.out.println("The number of columns: " + myLandscape.getCols());
        System.out.println(myLandscape.toString());
        System.out.println(myLandscape.getNeighbors(0, 3));
        System.out.println(myLandscape.getCell(3, 3));
        System.out.println(myLandscape.initialChance);

        // reset landscape to all dead cells
        myLandscape.reset();

        // check that only the status of the cells has been changed
        System.out.println("The number of rows: " + myLandscape.getRows()); 
        System.out.println("The number of columns: " + myLandscape.getCols());
        System.out.println(myLandscape.toString());
        System.out.println(myLandscape.getNeighbors(0, 3));
        System.out.println(myLandscape.getCell(3, 3));
        System.out.println(myLandscape.initialChance);

        // Create landscape of stated rows and columns, with cells that have a given chance to be alive
        Landscape setchLandscape = new Landscape(4, 4, 0.5);

        // check each method in Landscape class on new landscape
        System.out.println("The number of rows: " + setchLandscape.getRows()); 
        System.out.println("The number of columns: " + setchLandscape.getCols());
        System.out.println(setchLandscape.toString());
        System.out.println(setchLandscape.getNeighbors(0, 3));
        System.out.println(setchLandscape.getCell(3, 3));
        System.out.println(setchLandscape.initialChance);

        // reset landscape to all dead cells
        setchLandscape.reset();

        // check that only the status of the cells has been changed
        System.out.println("The number of rows: " + setchLandscape.getRows()); 
        System.out.println("The number of columns: " + setchLandscape.getCols());
        System.out.println(setchLandscape.toString());
        System.out.println(setchLandscape.getNeighbors(0, 3));
        System.out.println(setchLandscape.getCell(3, 3));
        System.out.println(setchLandscape.initialChance);
    }
}
