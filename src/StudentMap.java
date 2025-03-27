import java.util.*;


public class StudentMap implements Map<Student, Integer> {
    private static final int ARRAY_SIZE = 16;
    private LinkedList<MyEntry>[] map = new LinkedList[ARRAY_SIZE];
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
        int index = getIndexOf((Student) key);

        if (map[index] != null) {
            for (MyEntry entry : map[index])
                if (entry.getKey()
                         .equals(key)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<MyEntry> myEntries : map)
            for (MyEntry entry : myEntries)
                if (entry.getValue()
                         .equals(value)) return true;
        return false;
    }

    @Override
    public Integer get(Object key) {
        if (containsKey(key)) {
            int index = getIndexOf((Student) key);
            if (map[index] != null) {
                for (MyEntry entry : map[index])
                    if (entry.getKey()
                             .equals(key)) return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Integer put(Student key, Integer value) {
        if (size >= map.length) resize();
        int index = getIndexOf(key);

        if (map[index] == null) {
            map[index] = new LinkedList<>();
            map[index].add(new MyEntry(key, value));
            size++;
        } else {
            for (MyEntry entry : map[index]) {
                if (entry.getKey()
                         .equals(key)) entry.setValue(value);
            }
        }
        return null;
    }

    @Override
    public Integer remove(Object key) {
        int index = getIndexOf((Student) key);
        if (map[index] != null) {
            MyEntry toRemove = null;

            for (MyEntry entry : map[index]) {
                if (entry.getKey()
                         .equals(key)) toRemove = entry;
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
    public void putAll(Map<? extends Student, ? extends Integer> m) {
        for (Entry<? extends Student, ? extends Integer> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public void clear() {
        map = new LinkedList[ARRAY_SIZE];
        size = 0;
    }

    @Override
    public Set<Student> keySet() {
        Set<Student> set = new HashSet<>();
        for (LinkedList<MyEntry> myEntries : map)
            for (MyEntry entry : myEntries)
                set.add(entry.getKey());
        return set;
    }

    @Override
    public Collection<Integer> values() {
        List<Integer> list = new ArrayList<>();
        for (LinkedList<MyEntry> myEntries : map)
            for (MyEntry entry : myEntries)
                list.add(entry.getValue());
        return list;
    }

    @Override
    public Set<Entry<Student, Integer>> entrySet() {
        Set<Entry<Student, Integer>> entries = new HashSet<>();
        for (LinkedList<MyEntry> myEntries : map)
            entries.addAll(myEntries);
        return entries;
    }

    private void resize() {
        LinkedList<MyEntry>[] oldMap = map;
        map = new LinkedList[size * 2];
        size = 0;

        for (LinkedList<MyEntry> myEntries : oldMap) {
            if (myEntries == null) continue;
            for (MyEntry entry : myEntries) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private int getIndexOf(Student key) {
        return key.hashCode() % map.length;
    }
}