-- Student Management System Database
-- MySQL Database Schema

CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Create Students Table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_number INT UNIQUE NOT NULL,
    math_marks DECIMAL(5,2) CHECK (math_marks >= 0 AND math_marks <= 100),
    science_marks DECIMAL(5,2) CHECK (science_marks >= 0 AND science_marks <= 100),
    english_marks DECIMAL(5,2) CHECK (english_marks >= 0 AND english_marks <= 100),
    total_marks DECIMAL(6,2),
    average_marks DECIMAL(5,2),
    grade CHAR(2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert Sample Data
INSERT INTO students (name, roll_number, math_marks, science_marks, english_marks, total_marks, average_marks, grade) VALUES
('Rahul Sharma', 101, 85.5, 92.0, 88.5, 266.0, 88.67, 'A'),
('Priya Patel', 102, 78.0, 85.5, 90.0, 253.5, 84.50, 'A'),
('Amit Kumar', 103, 95.0, 88.0, 92.5, 275.5, 91.83, 'A+'),
('Sneha Singh', 104, 72.0, 76.5, 80.0, 228.5, 76.17, 'B'),
('Rohan Verma', 105, 88.0, 91.5, 85.0, 264.5, 88.17, 'A');

-- Useful Queries

-- View all students ordered by average
SELECT * FROM students ORDER BY average_marks DESC;

-- Get top performers
SELECT name, roll_number, average_marks, grade
FROM students 
WHERE grade IN ('A+', 'A')
ORDER BY average_marks DESC;

-- Subject-wise averages
SELECT 
    ROUND(AVG(math_marks), 2) AS avg_math,
    ROUND(AVG(science_marks), 2) AS avg_science,
    ROUND(AVG(english_marks), 2) AS avg_english
FROM students;

-- Grade distribution
SELECT grade, COUNT(*) AS count
FROM students
GROUP BY grade
ORDER BY grade;

-- Class statistics
SELECT 
    COUNT(*) AS total_students,
    MAX(total_marks) AS highest_score,
    MIN(total_marks) AS lowest_score,
    ROUND(AVG(average_marks), 2) AS class_average
FROM students;