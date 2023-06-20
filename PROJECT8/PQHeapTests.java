/**
 * Author: Adrian Gellert
 * Date: December 3, 2022
 * File: PQHeapTests.java
 * Section: Lecture A, Lab D
 * Project: Heaps
 * Course: CS231 
*/

import java.util.Comparator;

public class PQHeapTests {

    public static void PQHeapTests(){
        
        // case 1: Testing Constructor 
        {
            System.out.println("Testing PQHeap() Constructor");
            
            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });
            
            // verify
            /* System.out.println(heap.capacity() + " == 16");
            System.out.println(heap.size() + " == 0"); */
            
            // test
            assert heap != null: "Error in PQHeap::PQHeap()";
            assert heap.size() == 0 : "Error in PQHeap()";
        }
        
        // case 2: Testing Offer, offering below heap's capacity
        {
            System.out.println("Testing offer(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            heap.offer(5);
            heap.offer(3);
            heap.offer(9);
            
            // verify
            /* System.out.println(heap.size() + " == 3"); */
            
            // test
            assert heap.capacity() == 16: "Error in PQHeap::offer()";
            assert heap.size() == 3 : "Error in size(): PQ.Heap.java";
        }
        
        // case 3: Testing Resize, offering above heap's capacity
        {
            System.out.println("Testing resize(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            for (int i = 0; i < 16; i++){
                heap.offer(i);
            }

            // verify
            /* System.out.println(heap.capacity() + " == 32"); */
            
            // test
            assert heap.capacity() == 32: "Error in PQHeap::resize()";
        }
        
        // case 4: Testing Poll, without resize
        {
            System.out.println("Testing poll(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            for (int i = 0; i < 31; i++){
                heap.offer(i);
            }
            heap.poll();

            // verify
            /* System.out.println(heap.capacity() + " == 32");
            System.out.println(heap.size() + " == 30"); */

            
            // test
            assert heap.capacity() == 32: "Error in PQHeap::poll()";
        }

        // case 5: Testing Resize, poll until resize needed
        {
            System.out.println("Testing resize(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            for (int i = 0; i < 31; i++){
                heap.offer(i);
            }

            // verify
            /* System.out.println(heap.capacity() + " == 32"); */
            for (int i = 0; i < 25; i++){
                heap.poll();
            }
            /* System.out.println(heap.capacity() + " == 16"); */
            
            // test
            assert heap.capacity() == 16: "Error in PQHeap::poll()";
        }

        // case 6: Testing Swap Method
        {
            System.out.println("Testing swap(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            heap.offer(1);
            heap.offer(2);
            heap.swap(1, 2);

            // verify
            /* System.out.println(heap.toString() + "== 1 2"); */
            
            // test
            assert heap.toString().equals("1 2 "): "Error in PQHeap::swap()";
        }

        // case 7: Testing peek
        {
            System.out.println("Testing peek(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            heap.offer(1);
            heap.offer(2);

            // verify
            /* System.out.println(heap.peek() + " == 2"); */
            
            // test
            assert heap.peek() == 2: "Error in PQHeap::peek()";
        }

        // case 8: Testing bubbleUp
        {
            System.out.println("Testing bubbleUp(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            heap.offer(10);
            heap.offer(22);
            heap.offer(4);

            // verify
            /* System.out.println(heap.toString() + "== 22 10 4"); */
            
            // test
            assert heap.toString().equals("22 10 4 "): "Error in PQHeap::bubbleUp()";
        }

        // case 9: Testing bubbleDown
        {
            System.out.println("Testing bubbleDown(): ");

            // set up
            PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){
                public int compare(Integer e1, Integer e2){
                    return e1 - e2;
                }
            });

            heap.offer(10);
            // System.out.println(heap.toString());
            heap.offer(22);
            // System.out.println(heap.toString());
            heap.offer(4);
            // System.out.println(heap.toString());
            heap.offer(16);
            // System.out.println(heap.toString());
            heap.offer(6);

            // verify
            // System.out.println(heap.toString() + "== 22 16 4 10 6 ");
            heap.poll();
            // System.out.println(heap.toString() + "== 16 10 4 6 ");
            
            // test
            assert heap.toString().equals("16 10 4 6 "): "Error in PQHeap::bubbleDown()";
        }
    }

    public static void main(String[] args){
        PQHeapTests();
        System.out.println("Done Testing!");
    }
}