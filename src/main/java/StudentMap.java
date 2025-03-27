import java.util.*;

public class StudentMap<K, V> implements Map<K, V> {
    private static final int ARRAY_SIZE = 16;
    private LinkedList<MyEntry<K, V>>[] map = new LinkedList[ARRAY_SIZE];
    private int size = 0;

    public StudentMap() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndexOf(key);
        if (map[index] != null) {
            for (MyEntry<K, V> entry : map[index]) {
                if (entry.getKey()
                         .equals(key)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<MyEntry<K, V>> myEntries : map) {
            if (myEntries != null) {
                for (MyEntry<K, V> entry : myEntries) {
                    if (entry.getValue()
                             .equals(value)) return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = getIndexOf(key);
        if (map[index] != null) {
            for (MyEntry<K, V> entry : map[index]) {
                if (entry.getKey()
                         .equals(key)) return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size >= map.length) resize();
        int index = getIndexOf(key);

        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }

        for (MyEntry<K, V> entry : map[index]) {
            if (entry.getKey()
                     .equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        map[index].add(new MyEntry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = getIndexOf(key);
        if (map[index] != null) {
            MyEntry<K, V> toRemove = null;

            for (MyEntry<K, V> entry : map[index]) {
                if (entry.getKey()
                         .equals(key)) {
                    toRemove = entry;
                    break;
                }
            }

            if (toRemove != null) {
                map[index].remove(toRemove);
                size--;
                return toRemove.getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        map = new LinkedList[ARRAY_SIZE];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<MyEntry<K, V>> myEntries : map) {
            if (myEntries != null) {
                for (MyEntry<K, V> entry : myEntries) {
                    set.add(entry.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (LinkedList<MyEntry<K, V>> myEntries : map) {
            if (myEntries != null) {
                for (MyEntry<K, V> entry : myEntries) {
                    list.add(entry.getValue());
                }
            }
        }
        return list;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (LinkedList<MyEntry<K, V>> myEntries : map) {
            if (myEntries != null) {
                entries.addAll(myEntries);
            }
        }
        return entries;
    }

    private void resize() {
        LinkedList<MyEntry<K, V>>[] oldMap = map;
        map = new LinkedList[size * 2];
        size = 0;

        for (LinkedList<MyEntry<K, V>> myEntries : oldMap) {
            if (myEntries != null) {
                for (MyEntry<K, V> entry : myEntries) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private int getIndexOf(Object key) {
        return key.hashCode() % map.length;
    }
}