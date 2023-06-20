/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: Landscape.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

import java.util.Random;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Landscape {

    private int nRows;
    private int nCols;
    private Cell start;
    private Cell target;
    private Cell[][] gridCells;

    /**
     * Constructs the Landscape to have the specified number of rows and columns, where each Cell has a probability given by chance of being an Obstacle. 
     * Also set the start and target Cells.
     * @param rows
     * @param cols
     * @param chance
     */
    public Landscape(int rows, int cols, double chance){
        Random ran = new Random();
        nRows = rows;
        nCols = cols;

        //initialize Grid of Cells
        gridCells = new Cell[rows][cols];

        // loop through grid. If next random double is less than chance, set cell type to obstacle.
        // Otherwise set to free.
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (ran.nextDouble() < chance){
                    gridCells[r][c] = new Cell(r, c, Cell.Type.OBSTACLE);
                } 
                else {
                    gridCells[r][c] = new Cell(r, c, Cell.Type.FREE);
                }
            }
        }

        //Choose random locations on the grid to set to start and target
        start = new Cell(ran.nextInt(rows), ran.nextInt(cols), Cell.Type.START);
        gridCells[start.getRow()][start.getCol()] = start;
        target = new Cell(ran.nextInt(rows), ran.nextInt(cols), Cell.Type.TARGET);
        gridCells[target.getRow()][target.getCol()] = target;
    }

    /**
     * Returns the start Cell
     * @return start
     */
    public Cell getStart(){
        return start;
    }

    /**
     * Returns the target Cell
     * @return target
     */
    public Cell getTarget(){
        return target;
    }

    /**
     * Returns the number of rows
     * @return rows
     */
    public int getRows(){
        return nRows;
    }

    /**
     * Returns the number of columns
     * @return columns
     */
    public int getCols(){
        return nCols;
    }

    /**
     * Returns the Cell at the specified row and column
     * @param row
     * @param col
     * @return 
     */
    public Cell getCell(int row, int col){
        return gridCells[row][col];
    }

    /**
     * Resets all the Cells in the Landscape
     */
    public void reset(){
        for (int r = 0; r < this.gridCells.length; r++){
            for (int c = 0; c < this.gridCells[r].length; c++){
                this.gridCells[r][c].reset();
            }
        }
    }

    /**
     * Returns an ArrayList of Cells containing all the adjacent 
     * (diagonals not included) neighbors of the specified Cell in the grid.
     * @param cell Cell to find neighbors for
     * @return ArrayList of neighbors
     */
    public ArrayList<Cell> getNeighbors(Cell cell){
        ArrayList<Cell> neighboringCells = new ArrayList<Cell>();
        int r = cell.getRow();
        int c = cell.getCol();
        if (r > 0) neighboringCells.add(getCell(r - 1, c));
        if (c > 0) neighboringCells.add(getCell(r, c - 1));
        if (r < nRows - 1) neighboringCells.add(getCell(r + 1, c));
        if (c < nCols - 1) neighboringCells.add(getCell(r, c + 1));
        return neighboringCells;
    }

    public void draw(Graphics g, int scale) {
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getCols(); c++){
                getCell(r, c).draw(g, scale, this);
            }
        }
        g.setColor(Color.RED);
        CellQueue queue = new CellQueue();
        queue.offer(start);
        while (!(queue.size() == 0)) {
            Cell cur = queue.poll();
    
            for (Cell neighbor : getNeighbors(cur)) {
                if (neighbor.getPrev() == cur) {
                    queue.offer(neighbor);
                    g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                            neighbor.getCol() * scale + scale / 2, neighbor.getRow() * scale + scale / 2);
                }
            }
        }
    
        if (target.visited()) {
            int counter = 1;
            Cell cur = target.getPrev();
            while (cur != start) {
                g.setColor(Color.GREEN);
                g.fillRect(cur.getCol() * scale + 2, cur.getRow() * scale + 2, scale - 4, scale - 3);
                cur = cur.getPrev();
                counter++;
            }
            System.out.print("counter :" + counter + "\n");
            cur = target;
            while (cur != start) {
                g.setColor(Color.BLUE);
                g.drawLine(cur.getCol() * scale + scale / 2, cur.getRow() * scale + scale / 2,
                        cur.getPrev().getCol() * scale + scale / 2, cur.getPrev().getRow() * scale + scale / 2);
                cur = cur.getPrev();
            }
        }
    }
}

