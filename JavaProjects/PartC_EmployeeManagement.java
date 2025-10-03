import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

public class PartC_EmployeeManagement {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            Employee emp = new Employee(id, name, salary);

            // Append employee to file
            File file = new File(FILE_NAME);
            ObjectOutputStream oos = file.exists()
                    ? new AppendableObjectOutputStream(new FileOutputStream(file, true))
                    : new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(emp);
            oos.close();

            System.out.println("Employee added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void viewEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No employees found.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("\nEmployee List:");
            while (true) {
                Employee emp = (Employee) ois.readObject();
                System.out.println(emp);
            }
        } catch (EOFException e) {
            // End of file reached — do nothing
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees.");
            e.printStackTrace();
        }
    }

    // Custom ObjectOutputStream to append without writing header again
    static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
