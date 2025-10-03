import java.io.*;

// Student class implementing Serializable
class Student implements Serializable {
    private String name;
    private int roll;
    private double marks;

    public Student(String name, int roll, double marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', roll=" + roll + ", marks=" + marks + "}";
    }
}

public class PartB_Serialization {
    public static void main(String[] args) {
        Student s1 = new Student("Chandan", 101, 92.5);

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            oos.writeObject(s1);
            System.out.println("Student object serialized to 'student.ser'");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
