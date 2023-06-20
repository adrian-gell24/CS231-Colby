/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: CellQueue.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class CellQueue{

    private class Node{
        Node next;
        Cell cell;
        Node prev;

        /**
         * A constructor that initializes next and prev to null and replaces cell field with a given cell
         * @param cell 
         */
        public Node(Cell c){
            cell = c;
            next = null;
            prev = null;
        }
    }

    private int size; // the current size of the Queue
    private Node head; // the location of the head of the Queue
    private Node tail; // the location of the end of the Queue

    /**
     * Constructor that initializes the fields so it is an empty list.
     */
    public CellQueue(){
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Inserts the cell at the end of the queue
     * @param cell a Cell object called cell
     */
    public void offer(Cell c){
        Node newNode = new Node(c);

        if(size == 0){
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns but does not remove the Cell at the front of the queue
     * @return head
     */
    public Cell peek(){
        if (head == null){
            return null;
        } else {
            return head.cell;
        }
    }

    /**
     * Returns and removes the Cell at the front of the queue
     * @return head
     */
    public Cell poll(){
        if (size == 0){
            return null;
        } else if (size == 1){
            Cell remove = head.cell;
            head = null;
            size--;
            return remove;
        }
        else {
            Cell remove = head.cell;
            this.head = head.next;
            size--;
            return remove;
        }
    }

    /**
     * Returns the number of Cells in the queue
     * @return size
     */
    public int size(){
        return this.size;
    }
}