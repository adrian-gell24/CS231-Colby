
/**
 * Interface for a data structure that maps a key to a value, like a
 * Python dictionary would.  This is a subset of the Java Map interface.
 * 
 * Javadocs taken directly from their corresponding methods in the Java Map interface.
 *
 * @author srtaylor, bmaxwell, mbender
 */
import java.util.ArrayList;

public interface MapSet<K, V> {

    public class KeyValuePair<K, V> {

        private K key;
        private V value;

        public KeyValuePair(K k, V v) {
            key = k;
            value = v;
        }

        public void setKey(K k) {
            key = k;
        }

        public K getKey() {
            return key;
        }

        public void setValue(V val) {
            value = val;
        }

        public V getValue() {
            return value;
        }

        public String toString() {
            return "<" + key.toString() + " -> " + value.toString() + ">";
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced. Does nothing if {@code value} is {@code null}.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V put(K key, V value);

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key to a value.
     *
     * @param key The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key to a value.
     */
    public boolean containsKey(K key);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    public V get(K key);

    /**
     * Returns an ArrayList of all the keys in the map in sorted order from least to
     * greatest.
     * 
     * @return an ArrayList of all the keys in the map in sorted order from least to
     *         greatest.
     */
    public ArrayList<K> keySet();

    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values();

    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet();

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size();

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear();
}