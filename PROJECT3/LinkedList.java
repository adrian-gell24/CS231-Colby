    /**
 * Author: Adrian Gellert
 * Date: October 11, 2022
 * File: LinkedList.java
 * Section: Lecture A, Lab D
 * Project: Agent-Based Simulations
 * Course: CS231 
 */

import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function

public class LinkedList<T> implements Iterable<T>{

    /**
     * Container class that holds a generic object and a pointer.
     */
    private class Node{
        
         /**
         * The data and pointer
         */
        T data;
        Node next;

        /**
         * A constructor that initializes next to null and the container field to item.
         * @param item the Node's initial data
         */
        public Node(T item){
            this.data = item;
            next = null;
        }

        /**
         * Returns the value of the container field.
         * @return data
         */
        public T getData(){
            return data;
        }

        /**
         * Sets next to the given node.
         * @param newNext a new Node
         */
        public void setNext(Node newNext){
            next = newNext;
        }

        /**
         * Returns the next field.
         * @return next pointer
         */
        public Node getNext(){
            return next;
        }
    }

    private int size; // the current size of the LinkedList
    private Node head; // the location of the head of the list.

    /**
     * Constructor that initializes the fields so it is an empty list.
     */
    public LinkedList(){
        size = 0;
        this.head = null;
    }

    /**
     * Insert item at beginning of list.
     * @param item a generic object called item
     */
    public void add(T item){

        Node newNode = new Node(item); // create new node with item

        newNode.setNext(head); // points new node to current head

        this.head = newNode; //reset front of list

        size++; // increments size of total node
    }

    /**
     * Inserts the item at the specified position in the list.
     * @param index an integer specifying the list index where an item should be added
     * @param item a generic object called item
     */
    public void add(int index, T item){

        // checking if index is within the list's size 
        // adding items to beginning of the list without needing a walker
        if (index < 0 || index > size){
            System.out.println("Invalid index for add");
            return;
        }
        if(index==0){
            add(item);
            return;
        }

        Node walker = this.head; // creating walker to get to a certain position to add node
        for (int i = 0; i < index - 1; i++){
            walker = walker.getNext();
        }
        // after this for loop finishes, walker is sitting at position index - 1

        Node newNode = new Node(item);

        newNode.setNext(walker.getNext()); // adding newNode in between walker and its next
        walker.setNext(newNode); // changing walker's next to the newNode

        size++;
    }

    /**
     * Empties the list (resets the fields so it is an empty list).
     */
    public void clear(){
        this.head = null; // removes references to the rest of the node
        size = 0;
    }

    /**
     * Returns true if o is present in this list, otherwise this method returns false.
     * @param o an Object that could be within the list
     * @return whether o is present in the list.
     */
    public boolean contains(Object o){
        Node walker = this.head; // creating walker to get to a certain position to add node
        
        while (walker != null){
            for (int i = 0; i < size; i++){
                if (walker.getData() == o){
                    return true;
                }
                else {
                    walker = walker.getNext();
                }
            }
        }

        return false;
    }

    /**
     *  Returns true if o is a LinkedList that also contains the same items in the same order.
     */
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        LinkedList oAsALinkedList = (LinkedList) o;
        // Now I have a reference to something Java knows is a LinkedList!
        Node walker = this.head; // creating walker to traverse class's linkedllist
        Node checker = oAsALinkedList.head; // creating walker to traverse o as a linked list
        // Check each node in each linked list
        while (walker != null && checker != null){
            for (int i = 0; i < size; i++){
                if (walker.getData() == checker.getData()){
                    walker = walker.getNext();
                    checker = checker.getNext();
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns the item specified by the given index.
     * @param index an integer specifying the item's index
     * @return data at index.
     */
    public T get(int index){
        if(index < 0 || index >= size){
            System.out.println("Index out of bounds");
            return null; 
        }

        if (index == 0){
            return head.getData();
        }

        Node walker = head; // creating walker to get to a certain position to add node
        for (int i = 0; i < index; i++){
            walker = walker.getNext();
        }
        return walker.getData();
    }

    /**
     * Returns true if the list is empty, otherwise this method returns false.
     * @return whether the list is empty
     */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Removes the first item of the list and returns it.
     * @return the list's first item
     */
    public T remove(){
        T temp_storage = head.getData();
        head = head.getNext();
        size--;
        return temp_storage;
    }

    /**
     * Removes the item at the specified position in the list and returns it.
     * @param index an integer specifying a location in the list
     * @return item at specified index
     */
    public T remove(int index){

        if(index < 0 || index >= size){
            System.out.println("Index out of bounds");
            return null; 
        }
        Node walker = head;
        if (index == 0){
            T temp_storage = head.getData();
            head.setNext(head.getNext());
            size--;
            return temp_storage;
        }
        for (int i = 0; i < index; i++){
            walker = walker.getNext();
        }
        T temp_storage = walker.getNext().getData();
        walker.setNext(walker.getNext().getNext());
        size--;
        return temp_storage;
    }

    /**
     * Returns the size of the list.
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Returns a String representation of the list.
     */
    public String toString(){
        //toString method for list
        Node walker = head;
        String storage = "";
        while (walker != null){
            storage += "" + walker.getData();
            walker = walker.getNext();
        }
        
        return storage;
    }

    /**
     * Subclass that handles traversing the list
     */
    private class LLIterator implements Iterator<T>{

        /**
         * The next node to be provided during a traversal.
         */
        Node next;

        /**
         * Constructor for the LLIterator given the head of a list.
         * @param head node specifying the head of the list
         */
        public LLIterator(Node head){
            this.next = head;
        }

        /**
         * Returns true if there are still values to traverse 
         * (if the current node reference is not null).
         */
        public boolean hasNext(){
            if (next != null){
                return true;
            }
            else {
                return false;
            }
        }

        /**
         * Returns the next item in the list, which is the item contained in the current node. 
         * Moves the traversal along to the next node in the list.
         */
        public T next(){
            T temp = next.getData();
            next = next.getNext();
            return temp;
        }

        /**
         * Does
         */
        public void remove(){
            return;
        }
    }

     /**
     * Return a new LLIterator with head passed to the constructo
     * @return  new LLIterator
     */
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    /**
     * Converts LinkedList to an ArrayList
     * @return the arrayList
     */
    public ArrayList<T> toArrayList(){
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(this.head.getData());
        for (int i = 0; i < size; i++){
            arrayList.add(this.head.next.getData());
        }

        return arrayList;
    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.add(7);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(8);

        System.out.println(list.toString());

        list.remove();
        System.out.println(list.toString());
    }
}
