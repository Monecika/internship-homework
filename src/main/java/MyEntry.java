import java.util.Map;

public class MyEntry implements Map.Entry<Student, Integer> {
    private Student key;
    private Integer value;

    public MyEntry(Student key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Student getKey() {
        return key;
    }

    public void setKey(Student key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public Integer setValue(Integer value) {
        this.value = value;
        return value;
    }
}
