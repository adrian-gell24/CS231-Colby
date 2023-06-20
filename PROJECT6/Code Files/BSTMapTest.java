/**
 * Author: Adrian Gellert
 * Date: November 10, 2022
 * File: BSTMapTest.java
 * Section: Lecture A, Lab D
 * Project: Word Frequency
 * Course: CS231 
 */

public class BSTMapTest {

    public static void BSTMapTests() {

        // case 1: testing put(K key, V value) and get methods
        {
            // set up
            BSTMap map = new BSTMap<String, Integer>();
            map.put("a", 1);
            map.put("b", 1);
            map.put("c", 1);
            map.put("d", 1);
            map.put("c", 2);


            // verify
            System.out.println(map.get("a") + " == 1");
            System.out.println(map.get("b") + " == 1");
            System.out.println(map.get("c") + " == 2");
            System.out.println(map.get("d") + " == 1");

            // test
            assert map.size() != 0 : "Error in BSTMap::put()";
        }

        // case 2: testing clear()
        {
            // set up
            BSTMap map = new BSTMap<String, Integer>();
            map.put("a", 1);
            map.put("b", 1);
            map.put("c", 1);
            map.put("d", 1);
            map.put("e", 1);


            // verify
            System.out.println(map.get("a") + " == 1");
            System.out.println(map.get("b") + " == 1");
            System.out.println(map.get("c") + " == 1");
            System.out.println(map.get("d") + " == 1");
            System.out.println(map.get("e") + " == 1");

            map.clear();

            // test
            assert map.size() == 0 : "Error in BSTMap::clear()";
        }

        System.out.println("*** Done testing Cell! ***\n");
    }


    public static void main(String[] args) {

        BSTMapTests();
    }
}
