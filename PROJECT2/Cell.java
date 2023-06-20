/**
 * Author: Adrian Gellert
 * Date: September 26, 2022
 * File: Cell.java
 * Section: Lecture A, Lab D
 * Project: Conway's Game of Life
 * Course: CS231 
 */

import java.util.ArrayList;

public class Cell {

    /**
     * The status of the Cell.
     */
    private boolean alive;

    /**
     * Constructs a dead cell.
     */
    public Cell() {
        this.alive = false;
    }

    /**
     * Constructs a cell with the specified status.
     * 
     * @param status a boolean to specify if the Cell is initially alive
     */
    public Cell(boolean status) {
        this.alive = status;
    }

    /**
     * Returns whether the cell is currently alive.
     * 
     * @return whether the cell is currently alive
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Sets the current status of the cell to the specified status.
     * 
     * @param status a boolean to specify if the Cell is alive or dead
     */
    public void setAlive(boolean status) {
        this.alive = status;
    }

    /**
     * Updates the state of the Cell.
     * 
     * If this Cell is alive and if there are 2 or 3 alive neighbors,
     * this Cell stays alive. Otherwise, it dies.
     * 
     * If this Cell is dead and there are 3 alive neighbors,
     * this Cell comes back to life. Otherwise, it stays dead.
     * 
     * @param neighbors An ArrayList of Cells
     */
    public void updateState(ArrayList<Cell> neighbors) {
        int count = 0;
        for (Cell i : neighbors) {
            if (i.getAlive() == true){
                count ++;
            }
        }
        if ((count == 2 || count == 3) && this.alive == true){
            // checking first if statement
            this.alive = true;
        }
        else if (count == 3 && this.alive == false){
            // checking second if statement
            this.alive = true;
        }
        else {
            // otherwise cell is dead
            this.alive = false;
        }
    }

    /**
     * Returns a String representation of this Cell.
     * 
     * @return 1 if this Cell is alive, otherwise 0.
     */
    public String toString() {
        if (this.alive == true){
            return "" + 1;
        }
        else {
            return "" + 0;
        }
    }

    public static void main(String[] args) {
        // testing each method on a cell that is initially dead
        Cell deadCell = new Cell();
        System.out.println("This should be a dead cell (false): " + deadCell.getAlive());
        System.out.println("A dead cell as a string (0): " + deadCell.toString());
        deadCell.setAlive(true);
        System.out.println("Dead cell should now be alive (true): " + deadCell.getAlive());
        System.out.println("As a string (1): " + deadCell.toString());

        // testing each method on a cell that is initially alive
        Cell aliveCell = new Cell(true);
        System.out.println("This should be an alive cell (true): " + aliveCell.getAlive());
        System.out.println("A dead cell as a string (1): " + aliveCell.toString());
        aliveCell.setAlive(false);
        System.out.println("Alive cell should now be dead (false): " + aliveCell.getAlive());
        System.out.println("As a string (0): " + aliveCell.toString());
    }
}