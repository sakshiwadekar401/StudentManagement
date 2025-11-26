public class Student {
    // Fields
    private String name;
    private int rollNumber;
    private double mathMarks;
    private double scienceMarks;
    private double englishMarks;
    
    // Constructor
    public Student(String name, int rollNumber, double mathMarks, double scienceMarks, double englishMarks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.mathMarks = mathMarks;
        this.scienceMarks = scienceMarks;
        this.englishMarks = englishMarks;
    }
    
    // Method to calculate total marks
    public double calculateTotal() {
        return mathMarks + scienceMarks + englishMarks;
    }
    
    // Method to calculate average marks
    public double calculateAverage() {
        return calculateTotal() / 3;
    }
    
    // Method to calculate grade
    public String calculateGrade() {
        double average = calculateAverage();
        
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // Method to display student report
    public void displayReport() {
        System.out.println("\n========== STUDENT REPORT ==========");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("------------------------------------");
        System.out.println("Mathematics: " + mathMarks);
        System.out.println("Science: " + scienceMarks);
        System.out.println("English: " + englishMarks);
        System.out.println("------------------------------------");
        System.out.println("Total Marks: " + calculateTotal() + " / 300");
        System.out.println("Average: " + String.format("%.2f", calculateAverage()) + "%");
        System.out.println("Grade: " + calculateGrade());
        System.out.println("====================================\n");
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public double getMathMarks() {
        return mathMarks;
    }
    
    public double getScienceMarks() {
        return scienceMarks;
    }
    
    public double getEnglishMarks() {
        return englishMarks;
    }
}