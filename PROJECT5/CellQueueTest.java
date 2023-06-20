/**
 * Author: Adrian Gellert
 * Date: November 10, 2022
 * File: CellQueueTest.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class CellQueueTest {
    public static void main(String[] args){
        CellQueue queue = new CellQueue();
        System.out.println("Queue size without any cells: " + queue.size());
        Cell cell = new Cell(0, 0, Cell.Type.START);
        queue.offer(cell);
        System.out.println("Queue size with one offered cell: " + queue.size());
        Cell removed = queue.poll();
        System.out.println("cell type: " + removed.getType() + ", cell row: " + removed.getRow() + ", cell col: " + removed.getCol());
        System.out.println("Queue size after polling offered cell: " + queue.size());
        queue.offer(cell);
        System.out.println("Queue size after offering same cell: " + queue.size());
        Cell peekCell = queue.peek();
        System.out.println("cell type: " + peekCell.getType() + ", cell row: " + peekCell.getRow() + ", cell col: " + peekCell.getCol());
        System.out.println("Queue size after peeking (should be same as previous size): " + queue.size());
    }
}
