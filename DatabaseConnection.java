import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    // 🔧 CHANGED: JDBC URL (mysql → mariadb) + correct DB name
    private static final String URL =
        "jdbc:mariadb://localhost:3306/student_management";

    private static final String USER = "studentuser";
    private static final String PASSWORD = "student123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Database connected successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            return false;
        }
    }

    public static void addStudent(Student student) {
        String sql =
            "INSERT INTO students (name, roll_number, math_marks, science_marks, english_marks, total_marks, average_marks, grade) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            System.out.println("✅ Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY average_marks DESC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                    rs.getString("name"),
                    rs.getInt("roll_number"),
                    rs.getDouble("math_marks"),
                    rs.getDouble("science_marks"),
                    rs.getDouble("english_marks")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

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
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteStudent(int rollNumber) {
        String sql = "DELETE FROM students WHERE roll_number = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rollNumber);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayStatistics() {
        String sql =
            "SELECT COUNT(*) AS total, MAX(total_marks) AS highest, " +
            "MIN(total_marks) AS lowest, AVG(average_marks) AS avg FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n========== CLASS STATISTICS ==========");
                System.out.println("Total Students : " + rs.getInt("total"));
                System.out.println("Highest Score  : " + rs.getDouble("highest"));
                System.out.println("Lowest Score   : " + rs.getDouble("lowest"));
                System.out.println("Class Average  : " +
                    String.format("%.2f", rs.getDouble("avg")));
                System.out.println("====================================\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
