/**
 * File: Shuffle.java
 * Author: Adrian Gellert
 * Date: 09/13/2022
 */


import java.util.ArrayList;
import java.util.Random;



public class Shuffle{
    public static void main(String[] args) {
    ArrayList<Integer> listofNums = new ArrayList<Integer>();
    Random ran = new Random();
    
    // for (int i=0;i<10;i++) {
    //     /* loop to create a list of 10 random values */
    //     int val = ran.nextInt(99);
    //     listofNums.add(val);
    // }

    int val = 1;
    for (int i=0;i<10;i++){
        listofNums.add(val++);
    }

    System.out.println(listofNums);

    // for (int i=0;i<10;i++) {
    //     /* loop through listofNums 10 times
    //      * randomly removing one element at a time
    //      */

    //     int delete = ran.nextInt(0, listofNums.size());
    //     System.out.print(listofNums.get(delete));
    //     listofNums.remove(delete);
    //     System.out.println(listofNums);
    // }
    
    ArrayList<Integer> randomList = new ArrayList<Integer>();
    for (int i=0;i<10;i++) {
        /* loop through listofNums 10 times
         * and add each number to a new list in random order
         */
        
        int delete = ran.nextInt(0, listofNums.size());
        randomList.add(listofNums.get(delete));
        listofNums.remove(delete);
        System.out.println(randomList);
 }
}
}


