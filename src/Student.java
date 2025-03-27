import java.time.LocalDate;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(dateOfBirth, student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }


    @Override
    public int compareTo(Student other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", dateOfBirth=" + dateOfBirth + ", details='" + details + '\'' + '}';
    }
}
