/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: CellStack.java
 * Section: Lecture A, Lab D
 * Project: Grid Search
 * Course: CS231 
 */

public class CellStack {

    private class Node{
        Cell cell;
        Node next;

        public Node(Cell cell){
            this.cell = cell;
            this.next = null;
        }
    }

    private Node head; // this should always be the top of my stack
    private int size;

    public CellStack(){
        head = null;
        size = 0;
    }

    public void push(Cell c){
        Node newNode = new Node(c);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public Cell peek(){
        return head.cell; 
    }

    public Cell pop(){
        if (this.head == null){
            return null;
        }
        Node temp = this.head;
        this.head = this.head.next;
        size--;
        
        return temp.cell;
    }

    public int size(){
        return size;
    }

    public boolean empty(){
        if (size != 0){
            return false;
        }
        return true;
    }
}