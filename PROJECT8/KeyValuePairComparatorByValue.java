import java.util.Comparator;

public class KeyValuePairComparatorByValue<K, V extends Comparable<? super V>>
        implements Comparator<MapSet.KeyValuePair<K, V>> {
    public int compare(MapSet.KeyValuePair<K, V> kvp1, MapSet.KeyValuePair<K, V> kvp2) {
        return kvp1.getValue().compareTo(kvp2.getValue());
    }
}