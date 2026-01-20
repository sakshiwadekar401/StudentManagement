Student Management System — Full Stack Project

A full-stack Student Management System built to understand how frontend, backend, and database work together in a real application.

This project combines:

a Java console application (for core OOP + JDBC practice)

a PHP-based backend API

a web frontend (HTML, CSS, JavaScript)

a MariaDB/MySQL database

The goal of this project was not just UI, but end-to-end data flow — from user input to database and back.

✨ Key Features
🖥 Java Application (OOP + JDBC)

Menu-driven console application

Add, view, search, and delete students

Clean OOP structure using Student class

JDBC integration with MySQL/MariaDB

Automatic calculation of:

Total marks

Average percentage

Grade (A+, A, B, C, D, F)

This part focuses on Java fundamentals and database connectivity.

🌐 Web Application (Frontend + PHP Backend)

Modern dashboard-style UI

Add students through web form

View all students stored in database

Delete individual or all records

Real-time statistics:

Total students

Highest score

Lowest score

Average score

UI updates dynamically without page reload

The web app uses JavaScript Fetch API to communicate with the backend.

🗄 Database (MySQL / MariaDB)

Persistent relational database

Database name: student_management

Table: students

Stores:

Student details

Subject marks

Total, average, and grade

Constraints to ensure valid data

🔌 PHP Backend API

The PHP file acts as a REST-style API between the frontend and database.

Available endpoints:

GET /api.php?action=getAll → fetch all students

POST /api.php?action=add → add a student

POST /api.php?action=delete → delete one student

POST /api.php?action=deleteAll → delete all students

All communication is done using JSON.

📁 Project Structure
StudentManagement/
│
├── Student.java                 # Student OOP class
├── DatabaseConnection.java      # JDBC database connection
├── MainWithDatabase.java        # Java console application
│
├── database.sql                 # Database schema + sample data
├── api.php                      # PHP backend API
│
├── index.html                   # Web UI
├── style.css                    # UI styling
├── scripts.js                   # Frontend logic (Fetch + rendering)
│
└── README.md                    # Documentation

🧩 Student Class (Java)
Fields

name (String)

rollNumber (int)

mathMarks (double)

scienceMarks (double)

englishMarks (double)

Methods

calculateTotal()

calculateAverage()

calculateGrade()

Getter methods for all fields

🧮 Grading Logic
Average (%)	Grade
90–100	A+
80–89	A
70–79	B
60–69	C
50–59	D
< 50	F
🛠 Tech Stack
Frontend

HTML

CSS

JavaScript (Fetch API)

Backend

PHP (REST-style API)

Java (OOP + JDBC)

Database

MySQL / MariaDB

Tools & Environment

VS Code

Linux (Fedora/Ultramarine)

Git & GitHub

PHP built-in server

⚙️ Setup Instructions
1️⃣ Database Setup
CREATE DATABASE student_management;
USE student_management;
SOURCE database.sql;

2️⃣ Run Java Application

Compile:

javac *.java


Run:

java MainWithDatabase

3️⃣ Start PHP Backend

Inside project directory:

php -S localhost:8000


Backend endpoint:

http://localhost:8000/api.php

4️⃣ Open Web Application

Open in browser:

http://localhost:8000/index.html

🧠 What This Project Demonstrates

Java OOP concepts

JDBC and SQL integration

PHP backend development

REST-style API communication

Frontend–backend integration

Asynchronous JavaScript (Fetch API)

Real-time UI updates from database

Debugging full-stack issues

Version control using GitHub

🔮 Possible Future Improvements

User authentication

Edit/update student records

Pagination & search filters

Export data to CSV/PDF

Spring Boot backend

Deployment to cloud

👩‍💻 Author

Sakshi Wadekar
Full-Stack Development | Java | Databases | Web Technologies
