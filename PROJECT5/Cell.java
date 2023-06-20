/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: Cell.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

import java.awt.Graphics;
import java.awt.Color;

public class Cell {

    /**
     * Declaring that type can only be free, obstructed, start, or target
     */
    public enum Type {
        FREE, OBSTACLE, START, TARGET
    }

    /**
     * whether we've visited this Cell yet
     */
    boolean visited;

    /**
     * pointer to previously visited Cell
     */
    Cell prev;

    /**
     * row and column a Cell belongs to in the Landscape
     */
    int row, col;

    /**
     * the Cell's type
     */
    Type type;

    /**
     * Constructor that sets up the row, col, and type as specified; 
     * remaining fields should be null.
     */
    public Cell(int r, int c, Type t){
        row = r;
        col = c;
        type = t;
        prev = null;
        visited = false;
    }

    /**
     * Returns the row
     * @return row
     */
    public int getRow(){
        return row;
    }

    /**
     * Returns the column
     * @return col
     */
    public int getCol(){
        return col;
    }

    /**
     * Returns the type
     * @return type
     */
    public Type getType(){
        return type;
    }

    /**
     * Set Cell type
     */
    public void setType(Cell.Type input){
        type = input;
    }

    /**
     * Returns previous Cell
     * @return prev
     */
    public Cell getPrev(){
        return prev;
    }

    /**
     * Returns visited
     * @return visited
     */
    public boolean visited(){
        return visited;
    }

    /**
     * Sets visited to true and prev to c
     * @param c a Cell;
     */
    public void visitFrom(Cell c){
        this.visited = true;
        this.prev = c;
    }

    /**
     * Sets visited to false and prev to null
     */
    public void reset(){
        this.visited = false;
        this.prev = null;
    }

    public void draw(Graphics g, int scale, Landscape scape) {
        g.setColor(Color.BLACK);
        g.drawRect(getCol() * scale, getRow() * scale, scale, scale);
        switch (getType()) {
            case FREE:
                g.setColor(visited() ? Color.YELLOW : Color.GRAY);
                break;
            case OBSTACLE:
                g.setColor(Color.BLACK);
                break;
            case START:
                g.setColor(Color.BLUE);
                break;
            case TARGET:
                g.setColor(Color.RED);
                break;
        }
        g.fillRect(getCol() * scale + 2, getRow() * scale + 2, scale - 4, scale - 3);
    
        g.setColor(Color.RED);
        if (getPrev() != null && getPrev() != this) {
            int midX = ((getCol() + getPrev().getCol()) * scale + scale) / 2;
            int midY = ((getRow() + getPrev().getRow()) * scale + scale) / 2;
            g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                    midX, midY);
        }
        for (Cell neighbor : scape.getNeighbors(this)) {
            if (neighbor.getPrev() == this) {
                int midX = ((getCol() + neighbor.getCol()) * scale + scale) / 2;
                int midY = ((getRow() + neighbor.getRow()) * scale + scale) / 2;
                g.drawLine(getCol() * scale + scale / 2, getRow() * scale + scale / 2,
                        midX, midY);
            }
        }
    }
}