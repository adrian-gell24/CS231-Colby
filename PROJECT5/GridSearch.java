/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: GridSearch.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class GridSearch {
    LandscapeDisplay ld;
    Landscape grid;

    /**
     * Constructs grid using command line arguments for the dimensions and chance
     * @param args
     */
    public GridSearch(String[] args){
        this.grid = new Landscape(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
        ld = new LandscapeDisplay(grid, 20);
    }

    public boolean depthFirstSearch(int delay) throws InterruptedException {

        int vertices = 0;

        // Allocate a stack using last week's CellStack, initially empty
        CellStack cells = new CellStack();

        // Mark the start as visited
        Cell start = this.grid.getStart();
        start.visitFrom(start);

        // Push the start onto the stack
        cells.push(start);

        while (!cells.empty()){
            // set delay for repainting grid
            if (delay > 0){
                Thread.sleep(delay);
                this.ld.repaint();
            }

            // 
            Cell cur = cells.pop();
            for (Cell n : this.grid.getNeighbors(cur)){
                if (n.getType() != Cell.Type.OBSTACLE && !n.visited()){
                    vertices++;
                    n.visitFrom(cur);
                    if (n.getType() == Cell.Type.TARGET){
                        System.out.println("vertices: " + vertices);
                        return true;
                    }
                    cells.push(n);
                }
            }
        }

        // If we reach here, there is no path to the target cell.
        System.out.println("vertices: " + vertices);
        return false;
    }

    public boolean breadthFirstSearch(int delay) throws InterruptedException {

        int vertices = 0;

        // Allocate a stack using last week's CellStack, initially empty
        CellQueue cells = new CellQueue();

        // Mark the start as visited
        Cell start = this.grid.getStart();
        start.visitFrom(start);

        // Push the start onto the stack
        cells.offer(start);

        while (cells.size() != 0){
            // set delay for repainting grid
            if (delay > 0){
                Thread.sleep(delay);
                this.ld.repaint();
            }

            // 
            Cell cur = cells.poll();
            for (Cell n : this.grid.getNeighbors(cur)){
                if (n.getType() != Cell.Type.OBSTACLE && !n.visited()){
                    vertices++;
                    cells.offer(n);
                    n.visitFrom(cur);
                }
                if (n.getType() == Cell.Type.TARGET){
                    n.visitFrom(cur);
                    System.out.println("vertices: " + vertices);
                    return true;
                }
            }
        }

        // If we reach here, there is no path to the target cell.
        System.out.println("vertices: " + vertices);
        return false;
    }

    public static void main(String[] args) throws InterruptedException{
        int truecount = 0;
        int falsecount = 0;
        // for (int i = 0; i < 10; i++){
        //     GridSearch gridSearch = new GridSearch(args);
        //     if (gridSearch.breadthFirstSearch(0)){
        //         truecount++;
        //         System.out.println("true: " + truecount);
        //     }else {
        //         falsecount++;
        //         System.out.println("false: " + falsecount);
        //     }
        // }
        for (int i = 0; i < 10; i++){
            GridSearch gridSearch = new GridSearch(args);
            if (gridSearch.depthFirstSearch(0)){
                truecount++;
                System.out.println("true: " + truecount);
            }else {
                falsecount++;
                System.out.println("false: " + falsecount);
            }
        }
    }
}
