import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    // UPDATED for your local MariaDB setup
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/student_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "sakshi";
    private static final String PASSWORD = "Sakshipw";

    // Get database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Test connection
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Database connected successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Add student to database
    public static void addStudent(Student student) {
        String sql = "INSERT INTO students (name, roll_number, math_marks, science_marks, english_marks, total_marks, average_marks, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getRollNumber());
            pstmt.setDouble(3, student.getMathMarks());
            pstmt.setDouble(4, student.getScienceMarks());
            pstmt.setDouble(5, student.getEnglishMarks());
            pstmt.setDouble(6, student.calculateTotal());
            pstmt.setDouble(7, student.calculateAverage());
            pstmt.setString(8, student.calculateGrade());

            pstmt.executeUpdate();
            System.out.println("✅ Student added to database successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }

    // Get all students from database
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY average_marks DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int rollNumber = rs.getInt("roll_number");
                double math = rs.getDouble("math_marks");
                double science = rs.getDouble("science_marks");
                double english = rs.getDouble("english_marks");

                students.add(new Student(name, rollNumber, math, science, english));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error retrieving students: " + e.getMessage());
        }

        return students;
    }

    // Get student by roll number
    public static Student getStudentByRoll(int rollNumber) {
        String sql = "SELECT * FROM students WHERE roll_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rollNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getString("name"),
                    rs.getInt("roll_number"),
                    rs.getDouble("math_marks"),
                    rs.getDouble("science_marks"),
                    rs.getDouble("english_marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error finding student: " + e.getMessage());
        }

        return null;
    }

    // Delete student
    public static void deleteStudent(int rollNumber) {
        String sql = "DELETE FROM students WHERE roll_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rollNumber);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("❌ Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }

    // Get class statistics
    public static void displayStatistics() {
        String sql = "SELECT COUNT(*) as total, MAX(total_marks) as highest, MIN(total_marks) as lowest, AVG(average_marks) as avg FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n========== CLASS STATISTICS ==========");
                System.out.println("Total Students: " + rs.getInt("total"));
                System.out.println("Highest Score: " + rs.getDouble("highest"));
                System.out.println("Lowest Score: " + rs.getDouble("lowest"));
                System.out.println("Class Average: " + String.format("%.2f", rs.getDouble("avg")) + "%");
                System.out.println("======================================\n");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error getting statistics: " + e.getMessage());
        }
    }
}
