# Student Management System

A simple Java-based Student Management System demonstrating core Object-Oriented Programming principles.

## Features

- **Student Data Management**: Store student information including name, roll number, and marks in three subjects
- **Automatic Calculations**: Calculate total marks, average percentage, and letter grades
- **Report Generation**: Display comprehensive student reports with formatted output
- **OOP Implementation**: Demonstrates encapsulation, constructors, and methods

## Project Structure

```
student-management/
│
├── Student.java       # Student class with fields and methods
├── Main.java          # Main class to run the program
└── README.md          # Project documentation
```

## Student Class Components

### Fields
- `name` - Student's full name
- `rollNumber` - Unique student identifier
- `mathMarks` - Mathematics marks (out of 100)
- `scienceMarks` - Science marks (out of 100)
- `englishMarks` - English marks (out of 100)

### Methods
- `calculateTotal()` - Returns sum of all marks
- `calculateAverage()` - Returns average percentage
- `calculateGrade()` - Returns letter grade (A+, A, B, C, D, F)
- `displayReport()` - Prints formatted student report

## Grading Scale

| Average | Grade |
|---------|-------|
| 90-100  | A+    |
| 80-89   | A     |
| 70-79   | B     |
| 60-69   | C     |
| 50-59   | D     |
| Below 50| F     |

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- VS Code with Java Extension Pack (recommended for Fedora)

## Setup on Fedora

1. **Install JDK** (if not already installed):
```bash
sudo dnf install java-latest-openjdk java-latest-openjdk-devel
```

2. **Verify installation**:
```bash
java --version
javac --version
```

3. **Install VS Code** (if needed):
```bash
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo sh -c 'echo -e "[code]\nname=Visual Studio Code\nbaseurl=https://packages.microsoft.com/yumrepos/vscode\nenabled=1\ngpgcheck=1\ngpgkey=https://packages.microsoft.com/keys/microsoft.asc" > /etc/yum.repos.d/vscode.repo'
sudo dnf check-update
sudo dnf install code
```

4. **Install Java Extension Pack in VS Code**:
   - Open VS Code
   - Go to Extensions (Ctrl+Shift+X)
   - Search for "Extension Pack for Java"
   - Click Install

## How to Run

### Using Command Line:

1. **Navigate to project directory**:
```bash
cd ~/home-projects/student-management
```

2. **Compile the Java files**:
```bash
javac Student.java Main.java
```

3. **Run the program**:
```bash
java Main
```

### Using VS Code:

1. Open the project folder in VS Code
2. Open `Main.java`
3. Click the "Run" button above the main method
4. View output in the integrated terminal

## Expected Output

```
Welcome to Student Management System!
=====================================

========== STUDENT REPORT ==========
Name: Rahul Sharma
Roll Number: 101
------------------------------------
Mathematics: 85.5
Science: 92.0
English: 88.5
------------------------------------
Total Marks: 266.0 / 300
Average: 88.67%
Grade: A
====================================
```

## Customization

You can easily modify the program to:
- Add more subjects
- Change the grading scale
- Add more student records
- Implement data persistence
- Create a GUI interface

## Skills Demonstrated

- Class design and implementation
- Encapsulation using private fields
- Constructor usage
- Method creation and implementation
- Data type handling (String, int, double)
- Conditional logic for grade calculation
- Formatted output using System.out

## Future Enhancements

- Add file I/O for data persistence
- Implement a menu-driven interface
- Create multiple classes (Course, Teacher, etc.)
- Add input validation
- Build a graphical user interface (GUI)
- Implement database connectivity

## Author

Created as a demonstration of Java OOP principles

## License

Open source - feel free to use and modify for learning purposes