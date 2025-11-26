import java.util.List;
import java.util.Scanner;

public class MainWithDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("Student Management System with Database");
        System.out.println("========================================\n");
        
        // Test database connection first
        if (!DatabaseConnection.testConnection()) {
            System.out.println("\n⚠️  Please check your database settings in DatabaseConnection.java");
            System.out.println("Make sure MySQL is running and credentials are correct.");
            return;
        }
        
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Delete Student");
            System.out.println("5. View Class Statistics");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    DatabaseConnection.displayStatistics();
                    break;
                case 6:
                    System.out.println("\nThank you for using Student Management System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\n❌ Invalid choice! Please try again.");
            }
        }
    }
    
    private static void addNewStudent(Scanner scanner) {
        System.out.println("\n=== ADD NEW STUDENT ===");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        
        System.out.print("Enter Mathematics marks (0-100): ");
        double math = scanner.nextDouble();
        
        System.out.print("Enter Science marks (0-100): ");
        double science = scanner.nextDouble();
        
        System.out.print("Enter English marks (0-100): ");
        double english = scanner.nextDouble();
        
        Student student = new Student(name, rollNumber, math, science, english);
        DatabaseConnection.addStudent(student);
        
        student.displayReport();
    }
    
    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        List<Student> students = DatabaseConnection.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in database.");
        } else {
            for (Student student : students) {
                student.displayReport();
            }
        }
    }
    
    private static void searchStudent(Scanner scanner) {
        System.out.print("\nEnter roll number to search: ");
        int rollNumber = scanner.nextInt();
        
        Student student = DatabaseConnection.getStudentByRoll(rollNumber);
        
        if (student != null) {
            student.displayReport();
        } else {
            System.out.println("❌ Student with roll number " + rollNumber + " not found.");
        }
    }
    
    private static void deleteStudent(Scanner scanner) {
        System.out.print("\nEnter roll number to delete: ");
        int rollNumber = scanner.nextInt();
        
        DatabaseConnection.deleteStudent(rollNumber);
    }
}