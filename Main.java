public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System!");
        System.out.println("=====================================\n");
        
        // Creating student objects
        Student student1 = new Student("Rahul Sharma", 101, 85.5, 92.0, 88.5);
        Student student2 = new Student("Priya Patel", 102, 78.0, 85.5, 90.0);
        Student student3 = new Student("Amit Kumar", 103, 95.0, 88.0, 92.5);
        
        // Displaying reports
        student1.displayReport();
        student2.displayReport();
        student3.displayReport();
        
        // Demonstrating individual method calls
        System.out.println("Individual Method Demonstration:");
        System.out.println("================================");
        System.out.println(student1.getName() + "'s Total: " + student1.calculateTotal());
        System.out.println(student2.getName() + "'s Average: " + String.format("%.2f", student2.calculateAverage()));
        System.out.println(student3.getName() + "'s Grade: " + student3.calculateGrade());
    }
}