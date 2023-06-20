import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.LinkedHashSet;

public class HashMap<K, V> implements MapSet<K, V> {

    LinkedList<KeyValuePair<K, V>>[] map;
    int size;
    double loadFactor;

    /**
     * Call HashMap(int capacity) with chosen capacity of 16
     */
    public HashMap(){
        this(16);
    }

    /**
     * Calls HashMap(int capacity, double loadFactor) with chosen loadFactor of .5
     * @param capacity
     */
    public HashMap(int capacity){
        this(capacity, .5);
    }

    /**
     * Initializes the HashMap with the given capacity and stores the given loadFactor as a field.
     * @param capacity
     * @param loadFactor
     */
    @SuppressWarnings("unchecked")
    public HashMap(int capacity, double loadFactor) {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    /**
     * Returns the size of the hashmap
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * Returns the capacity of the map given by the length of the map
     * @return map.length
     */
    private int capacity() {
        return map.length;
    }

    /**
     * Resets fields to default values
     */
    @Override
    public void clear() {
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[capacity()];
        this.size = 0;
        this.loadFactor = 0;
    }

    /**
     * Returns the index that will be used by any given key for this mapping
     * @param key
     * @return
     */
    private int hash(K key) {
        //  Shrinks hashCode to within capacity of the map
        //  Then takes the absolute value to make the value positive
        return Math.abs(key.hashCode() % capacity()); // this returns a value between 0 and capacity() - 1, inclusive
    }

    /**
     * Associates the specified value with the specified key in this map. 
     * If the map previously contained a mapping for the key, the old value is replaced. 
     * Does nothing if value is null. Returns the previous value associated with key, or null if there was no mapping for key.
     */
    public V put(K key, V value) {
        int index = hash(key);

        if (map[index] == null) {
            map[index] = new LinkedList<KeyValuePair<K, V>>();
            map[index].add(new KeyValuePair<K, V>(key, value));
            size++;
            // if size ever gets too big compared to capacity, then I need to recreate my map to be bigger
            // map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[map.length * someReasonableFactor];
            if (size > loadFactor * capacity()) resizeUp();
            return null;
        } else {
            for (KeyValuePair<K, V> kvp : map[index]) {
                if (kvp.getKey().equals(key)) {
                    V oldValue = kvp.getValue();
                    kvp.setValue(value);
                    return oldValue;
                }
            } // none of keys correspond with the value
            map[index].add(new KeyValuePair<K, V>(key, value));
            size++;
            if (size > loadFactor * capacity()) resizeUp();
            return null;
        }
    }

    /**
     * Increases the capacity of the hashmap by a factor given by the loadFactor
     */
    private void resizeUp(){
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(int) (capacity() / loadFactor)];
        size = 0;
        for (KeyValuePair<K, V> kvp : kvps){
            put(kvp.getKey(), kvp.getValue());
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public V get(K key) {
        int index = hash(key);

        if (map[index] == null) {
            return null;
        } else {
            for (KeyValuePair<K, V> kvp : map[index]) {
                if (kvp.getKey().equals(key)) {
                    return kvp.getValue();
                }
            } return null;
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key to a value.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Removes the specified key (and its associated value) from this mapping and returns the value it was mapped to.
     * @param key
     * @return
     */
    public V remove(K key){
        int index = hash(key);

        if (map[index] == null) {
            return null;
        } 
        Iterator<KeyValuePair<K, V>> iterator = map[index].iterator();
        while (iterator.hasNext()) {
            KeyValuePair<K, V> kvp = iterator.next();
            if (kvp.getKey().equals(key)) {
                iterator.remove();
                size--;
                if (size < loadFactor * capacity()) resizeDown();
                return kvp.getValue();
            }
        }

        return null;
    }

    /**
     * Decreases the capacity of the hashmap by a factor given by the loadFactor
     */
    private void resizeDown(){
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(int) (capacity() * loadFactor)];
        size = 0;
        for (KeyValuePair<K, V> kvp : kvps){
            put(kvp.getKey(), kvp.getValue());
        }
    }

    /**
     * Returns an ArrayList of all the keys in the map in no particular order.
     */
    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> output = new ArrayList<K>();
        for (LinkedList<KeyValuePair<K, V>> list : map){
            if (list == null) continue;
            for(KeyValuePair<K, V> kvp : list){
                output.add(kvp.getKey());
            }
        }
        return output;
    }

    /**
     * Returns an ArrayList of all the values in the map in the same order returned by keySet()
     */
    @Override
    public ArrayList<V> values() {
        ArrayList<V> output = new ArrayList<V>();
        for (LinkedList<KeyValuePair<K, V>> list : map){
            if (list == null) continue;
            for(KeyValuePair<K, V> kvp : list){
                output.add(kvp.getValue());
            }
        }
        return output;
    }

    /**
     * Returns an ArrayList of each KeyValuePair in the map in the same order as the keys as returned by keySet().
     */
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> output = new ArrayList<KeyValuePair<K, V>>();
        for (LinkedList<KeyValuePair<K, V>> list : map){
            if (list == null) continue;
            for(KeyValuePair<K, V> kvp : list){
                output.add(kvp);
            }
        }
        return output;
    }

    /**
     * Returns a String that represents the HashMap.
     */
    public String toString(){
        ArrayList<KeyValuePair<K, V>> kvps = entrySet();
        return entrySet().toString();

        // The below method also seems to work??
        // String map = "{";
        // for (KeyValuePair<K, V> kvp : kvps){
        //     map += kvp.getKey() + ": " + kvp.getValue() + "\n";
        // }
        // map += "}";
        // return map;
    }

    // public static void main(String[] args) {
    //     HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(5);
    //     for (int i = 0; i < 5; i++) {
    //         System.out.println(hm.size());
    //         System.out.println(hm.capacity());
    //         hm.put(i, i + 1);
    //     }

    //     System.out.println(hm.toString());
    //     System.out.println(hm.containsKey(3));
    //     System.out.println(hm.containsKey(5));

    //     System.out.println(hm.keySet());
    //     System.out.println(hm.values());
    //     System.out.println(hm.entrySet());

    //     System.out.println(hm.get(0));
    //     System.out.println(hm.get(5));

    //     for (int i = 5; i > 0; i--) {
    //         System.out.println(hm.size());
    //         System.out.println(hm.capacity());
    //         hm.remove(i);
    //     }

    //     System.out.println(hm.toString());

    //     hm.clear();
    //     System.out.println(hm.toString());

    //     // hm.put(6, 7);
    //     // System.out.println(hm.get(6));
    //     // System.out.println(hm.get(1));

    //     // HashMap<String, Integer> hm = new HashMap<String, Integer>(10);
    //     // for(String word : someRedditCountFile){
    //     //     hm.put(word, whateverValueItShouldBe);
    //     // }
    // }

    
    public static void main(String[] args) {
        Random rand = new Random();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(16, .5);
        System.out.println(map.capacity() == 16);

        LinkedHashSet<Integer> vals = new LinkedHashSet<Integer>();
        System.out.println("Starting tests");
        for(int i = 0; i < 1000; i++){
            int val = rand.nextInt(1000000);
            while (vals.contains(val)) val = rand.nextInt(1000000);
            vals.add(val);
            map.put(val, i);
            if (map.size() != i + 1) System.out.println("Bad size: " + i);
            if (map.size() >= 16 && (map.size() > map.capacity() / 2 || map.size() < map.capacity() / 8)){
                System.out.println("Didn't resize: (" + map.size() + ", " + map.capacity());
            }
        }

        for (int i = 0; i < 10; i++){
            int val = rand.nextInt(1000000);
            while (!vals.contains(val)) val = rand.nextInt(1000000);
            if (!map.containsKey(val)) System.out.println("Bad containsKey (false when present");

            val = rand.nextInt(1000000);
            while (vals.contains(val)) val = rand.nextInt(1000000);
            if (map.containsKey(val)) System.out.println("Bad containsKey (true when not present");
        }

        ArrayList<KeyValuePair<Integer, Integer>> entries = map.entrySet();
        if (entries.size() != 1000) System.out.println("Bad entries: " + entries.size());

        int i = 0;
        for(int val : vals){
            int v = map.remove(val);
            if (v != i) System.out.println("Bad value: " + v);
            if (map.size() != 1000 - i - 1) System.out.println("Bad size: " + i);
            if (map.size() >= 16 && (map.size() > map.capacity() / 2 || map.size() < map.capacity() / 8)){
                System.out.println("Didn't resize: (" + map.size() + ", " + map.capacity());
            }
            i++;
        }
        System.out.println("Ending tests");
    }
}