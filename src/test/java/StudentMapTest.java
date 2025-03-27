import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StudentMapTest {

    private StudentMap studentMap;

    @BeforeEach
    void setUp() {
        studentMap = new StudentMap();
    }

    @Test
    public void testPutAndGet() {
        Student expectedStudent = new Student("John Doe", LocalDate.of(2000, 5, 12), "");

        studentMap.put(expectedStudent, 90);

        assertAll(() -> assertThat(studentMap).containsKey(expectedStudent),
                () -> assertThat(studentMap.get(expectedStudent)).isEqualTo(90),
                () -> assertThat(studentMap.size()).isEqualTo(1),
                () -> assertThat(studentMap.keySet()).contains(expectedStudent));
    }
}
