/**
 * Author: Adrian Gellert
 * Date: December 3, 2022
 * File: PQHeap.java
 * Section: Lecture A, Lab D
 * Project: Heaps
 * Course: CS231 
 */

import java.util.Comparator;

public class PQHeap<T>{
    /**
     * Rules for heap:
     * 1. Whenever I make a new node (insertion), I begin at the lowest level from left to right
     * 2. Any root to leaf path never increases
     * 3. This lab works with MaxHeaps where the parent nodes are always greater than the childs
     */

    private T[] heap;
    private int size;
    private Comparator<T> comparator;


    /**
     * Constructor:
     * initializes the empty heap, sets the size to zero, and stores the comparator.
     * @param comparator
     */
    @SuppressWarnings({"unchecked"})
    public PQHeap(Comparator<T> comparator){
        size = 0;
        this.comparator = comparator;
        heap = (T[]) new Object[16];
    }

    /**
     * Returns the number of elements in the heap
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * Returns the number of items the heap can currently hold
     * @return
     */
    public int capacity(){
        return this.heap.length;
    }

    /**
     * Takes in index and returns index associated with left child
     * @param index
     * @return
     */
    private int left(int index){
        return index * 2;
    }

    /**
     * Takes in index and returns index associated with right child
     * @param index
     * @return
     */
    private int right(int index){
        return index * 2 + 1;
    }

    /**
     * Takes in index and returns index associated with parent
     * @param index
     * @return
     */
    private int parent(int index){
        return index / 2;
    }

    /**
     * Swap data at different indices in the heap
     * @param index1
     * @param index2
     */
    public void swap(int index1, int index2){
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Recreate the array with a new size given by newSize
     * @param newSize
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize){
        T[] newHeap = (T[]) new Object[newSize];
        for (int i = 1; i < size + 1; i++){
            newHeap[i] = heap[i];
        } heap = newHeap;
    }

    /**
     * A recursive function that makes sure heap properties are maintained following an offer to the heap.
     * @param index
     */
    public void bubbleUp(int index){
        if (index == 1){
            return; // at root so don't need to do anything
        }

        // hold data at index and its parent
        T myself = heap[index];
        T myParent = heap[parent(index)];
        
        // compare the data holders
        // if data at the index has a higher priority than the parent, swap the data at the indices
        // recursively call the bubbleUp function to 
        if (comparator.compare(myself, myParent) > 0){
            swap(index, parent(index));
            bubbleUp(parent(index));
        }
    }

    /**
     * A recursive function that makes sure heap properties are maintained following a poll from the heap.
     * @param index
     */
    public void bubbleDown(int index){

        // if the left of the index is greater than the number of items within the heap (size),
        // then we are done going down the heap
        if (left(index) > size){
            return;
        } else if(right(index) > size){ // if the right is greater than size, then check if left has a greater priority than the index
            T myself = heap[index];
            T myLeft = heap[left(index)];
            if (comparator.compare(myLeft, myself) > 0){ // if it does, swap indices and recurse
                swap(left(index), index);
                bubbleDown(left(index));
            }
        } else{ // last scenario where left and right childs are still within size
            T myself = heap[index];
            T myLeft = heap[left(index)];
            T myRight = heap[right(index)];
            // First, compare left and right childs
            if (comparator.compare(myLeft, myRight) > 0){
                if (comparator.compare(myLeft, myself) > 0){ // if left has higher priority than both left and current index
                    // swap left with current index and recurse
                    swap(left(index), index);
                    bubbleDown(left(index));
                }
            } else { // otherwise, if right has higher priority than current index
                if (comparator. compare(myRight, myself) > 0){
                    // swap them and recurse
                    swap(right(index), index);
                    bubbleDown(right(index));
                }
            }
        }
    }

    /**
     * Adds the value to the heap and increments the size. 
     * If there's no room for the new item to be added, double the size of the array.
     * Also, maintain heap locality rules with bubbleUp
     * @param item
     */
    public void offer(T item){
        if (size + 1 == heap.length) resize(2 * heap.length);
        heap[size + 1] = item;
        bubbleUp(size + 1);
        size++;
    }

    /**
     * Removes and returns the highest priority element from the heap. 
     * If the size would now be below a quarter of the capacity, halve the capacity.
     * 1. Remove root from heap
     * 2. Replace root with last offered item
     * 3. Bubble down from root until heap correct
     * @param item
     */
    public T poll(){
        if (size == 0) return null;
        T highestVal = heap[1];
        swap(1, this.size);
        heap[this.size] = null;
        size--;
        bubbleDown(1);
        if (size + 1 <= (heap.length * .25)) resize(heap.length / 2);
        return highestVal;
    }

    /**
     * Returns the highest priority element from the heap without removing
     * @return
     */
    public T peek(){
        return heap[1];
    }

    /**
     * A toString method for the heap that orders the items based on priority
     */
    public String toString(){
        String toReturn = "";
        for (T i : heap){
            if (i != null){
                toReturn += i + " ";
            }
        }
        return toReturn;
    }

    public static void main(String[] args){
        PQHeap<Integer> heap = new PQHeap<Integer> (new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2){
                return o1 - o2;
            }
            
        });

        heap.offer(5);
    }
}