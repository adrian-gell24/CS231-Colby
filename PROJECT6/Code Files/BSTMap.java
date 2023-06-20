/**
 * Author: Adrian Gellert
 * Date: October 31, 2022
 * File: BSTMap.java
 * Section: Lecture A, Lab D
 * Project: Word Frequency
 * Course: CS231 
 */

import java.security.Key;
import java.util.ArrayList;

public class BSTMap <K extends Comparable<K>, V> implements MapSet<K, V>{

    private class Node{

        Node left; // left child
        Node right; // right child
        KeyValuePair<K, V> kvp; // data of the node

        /**
         * Node constructor
         * @param kvp
         */
        public Node (KeyValuePair<K, V> kvp){
            this.kvp = kvp;
            left = null;
            right = null;
        }

        /**
         * Getter method for key
         * @return
         */
        public K getKey(){
            return kvp.getKey();
        }

        /**
         * Getter method for value
         * @return
         */
        public V getValue(){
            return kvp.getValue();
        }

        /**
         * Setter method for value
         * @param value
         */
        public void setValue(V value){
            kvp.setValue(value);
        }
    }

    private Node root;
    private int size;

    /**
     * Constructor of map
     */
    public BSTMap(){
        root = null;
        size = 0;
    }

    /**
     * Returns size of the map
     */
    public int size(){
        return size;
    }

    /**
     * Resets fields to default values
     */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
     * If size of the map is zero, places a new node containing a key-value pair into the map.
     * Otherwise, calls the private put method for the given key and value. 
     */
    public V put(K key, V value){
        if (size == 0){
            Node newNode = new Node(new KeyValuePair<K,V>(key, value));
            root = newNode;
            size++;
            return null;
        }
        return put(key, value, root);
    }

    /**
     * Recursive method. Checks whether the given key is greater than or less than current key in the map.
     * If it is greater/less than the key in the map, travel down the map to an empty location. 
     * If there is no empty location, the key must in this case already be in the map. 
     * In this case, set the value associated with the key to the new value given to the method. 
     * @param key
     * @param value
     * @param cur
     * @return
     */
    private V put(K key, V value, Node cur) {
        if (key.compareTo(cur.getKey()) < 0){
            if (cur.left != null){
                return put(key, value, cur.left);
            } else {
                Node newNode = new Node(new KeyValuePair<K,V>(key, value));
                cur.left = newNode;
                size++;
                return null;
            }
        } else if (key.compareTo(cur.getKey()) > 0){
            if (cur.right != null){
                return put(key, value, cur.right);
            } else {
                Node newNode = new Node(new KeyValuePair<K,V>(key, value));
                cur.right = newNode;
                size++;
                return null;
            }
        } else { // in this case, cur.getKey() == key
            V storeValue = cur.kvp.getValue();
            cur.kvp.setValue(value);
            return storeValue;
        }
    }

    /**
     *  Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key){
        if (size == 0){
            return null;
        }
        return get(key, root);
    }

    /**
     * Recursive function that travels down the map and checks 
     * whether the given key is greater than or less than the given key. 
     * If neither is true, return the value associated with the key-value pair where the traveler node is located. 
     * @param key
     * @param cur
     * @return
     */
    private V get(K key, Node cur){
        if (cur == null){
            return null;
        }

        if (key.compareTo(cur.getKey()) < 0){
            return get(key, cur.left);
        } else if (key.compareTo(cur.getKey()) > 0){
            return get(key, cur.right);
        } else {
            // return cur.getValue();
            return cur.kvp.getValue();
        }
    }

    /**
     * Recursive that travels down the tree making comparisons. 
     * If the given key is less than or greater than the key at which the traveler node is
     * but going left/right results in going into an empty location, return false. The key is not in the map.
     * Otherwise, keep going. If the key is neither greater/less than the key where the traveler node is, 
     * return true. The map contains the key. 
     */
    public boolean containsKey(K key){
        if (size == 0){
            return false;
        }

        Node cur = root;
        while (true){
            if (key.compareTo(cur.getKey()) < 0){
                if (cur.left == null){
                    return false;
                } cur = cur.left;
            } else if (key.compareTo(cur.getKey()) > 0){
                if (cur.right == null){
                    return false;
                } cur = cur.right;
            } else {
                return true;
            }
        }
    }

    /**
     * Returns an ArrayList of all the keys in the map in sorted order from least to greatest. 
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<K>();
        keySet(root, keys);
        return keys;
    }

    /**
     * Helper method that places the keys into the array given by the public method 
     * while using recursion to travel down the binary search tree. 
     * @param cur
     * @param output
     */
    private void keySet(Node cur, ArrayList<K> output){
        if (cur == null){
            return;
        }

        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    /**
     * Returns an ArrayList of all the values in the map in the same order returned by keySet()
     */
    public ArrayList<V> values(){
        ArrayList<V> valueSetList = new ArrayList<V>();
        values(root, valueSetList);
        return valueSetList;
    }

    /**
     * Helper method that places the values into the array given by the public method 
     * while using recursion to travel down the binary search tree. 
     * @param cur
     * @param output
     */
    private void values(Node cur, ArrayList<V> output){
        if (cur == null){
            return;
        }

        values(cur.left, output);
        output.add(cur.getValue());
        values(cur.right, output);
    }

    /**
     * Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> keyValuePairs = new ArrayList<KeyValuePair<K, V>>();
        entrySet(root, keyValuePairs);
        return keyValuePairs;
    }

    /**
     * Helper method that places the key-value pairs into the array given by the public method 
     * while using recursion to travel down the binary search tree.
     * @param cur
     * @param output
     */
    private void entrySet(Node cur, ArrayList<KeyValuePair<K, V>> output){
        if (cur == null){
            return;
        }

        output.add(cur.kvp);
        entrySet(cur.left, output);
        entrySet(cur.right, output);
    }

    /**
     * Returns a String that represents the BSTMap accurately depicting the levels.
     */
    public String toString(){
        if (root == null){
            return "";
        }
        return toString(root, 0, "root");
    }

    /**
     * Helper method that uses depth incrementation to depict levels in an outputted string
     * During each recursion to travel down the binary search tree, depth is increased by one. 
     * The returned string includes a number of tabs given by depth in between the direction
     * and the key-value pair information. 
     * @param cur
     * @param depth
     * @param direction
     * @return
     */
    private String toString(Node cur, int depth, String direction){
        if (cur == null){
            return "";
        }

        String left = toString(cur.left, depth + 1, "left");
        String right = toString(cur.right, depth + 1, "right");

        return direction + '\t' + "   ".repeat(depth) + cur.kvp + "\n" + left + right;
    }
}