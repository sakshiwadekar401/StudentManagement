# 📚 Student Management System (Full Stack Project)

A complete Full-Stack Student Management System built using Java, MySQL, PHP, HTML, CSS, and JavaScript.
This project demonstrates OOP concepts, database integration, API development, and a modern responsive web UI.

## ✨ Features
🖥 Java Application (Backend Logic)

Add, delete, view, and search students

Fully OOP-structured (Student.java, DatabaseConnection.java)

JDBC integration using MySQL Connector

Auto-calculation:

Total marks

Average %

Grade (A+, A, B, C, D, F)

## 🗄 MySQL / MariaDB Database

Fully persistent database storage

Table: students

Stores all marks, totals, averages, and grade

## 🌐 PHP REST API

Handles all CRUD operations:

GET /api.php?action=getAll

POST /api.php?action=add

POST /api.php?action=delete

POST /api.php?action=deleteAll

Used by the frontend to communicate with MySQL.

## 🎨 Modern Frontend (HTML + CSS + JS)

Dashboard-style UI

Statistics cards (total students, average, highest & lowest score)

Add student form

Real-time rendering of database records

Dynamic delete

Responsive layout

## 📁 Project Structure
StudentManagement/
│
├── Student.java                 # OOP class for student data
├── DatabaseConnection.java      # JDBC MySQL connection class
├── MainWithDatabase.java        # Java menu-driven system
│
├── database.sql                 # MySQL schema + sample data
├── api.php                      # PHP API (CRUD operations)
│
├── index.html                   # Frontend UI
├── style.css                    # Styling for UI
├── scripts.js                   # JavaScript API calls & rendering
│
└── README.md                    # Project documentation

## 🧩 Student Class Details
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

Getters for all fields

## 🧮 Grading Scale
Average	Grade
90–100	A+
80–89	A
70–79	B
60–69	C
50–59	D
< 50	F
🛠 Prerequisites
Software needed

JDK 17+

PHP 8+

MySQL / MariaDB

DBeaver (optional UI viewer)

VS Code (for editing)

## ⚙️ Setup Instructions
#### 1️⃣ Install Java (Fedora)
```sudo dnf install java-latest-openjdk java-latest-openjdk-devel```


Check:

```java --version```
```javac --version```

#### 2️⃣ Install MySQL / MariaDB
```sudo dnf install mariadb mariadb-server```
```sudo systemctl enable --now mariadb```


Load the database:

``CREATE DATABASE student_management;``
```USE student_management;```
```SOURCE database.sql;```

#### 3️⃣ Run Java App
Compile:
``` javac -cp .:/usr/share/java/mysql-connector-java.jar *.java ```

Run:
```java -cp .:/usr/share/java/mysql-connector-java.jar``` MainWithDatabase

#### 4️⃣ Start PHP API Server

Inside project folder:

```php -S 127.0.0.1:8000```


This exposes:

http://127.0.0.1:8000/api.php

#### 5️⃣ Open Web Interface

Open:

http://127.0.0.1:8000/index.html

### 🖥 Java Output Example
========================================
Student Management System with Database
========================================

1. Add New Student
2. View All Students
3. Search Student by Roll Number
4. Delete Student
5. View Class Statistics
6. Exit

### 🌐 Web UI Preview

- Live student records

- Real-time stats

- Add/delete student

- Fully responsive

- Green badge when connected to MySQL

### 🧠 Skills Demonstrated

1. Java OOP

2. JDBC + MySQL integration

3. REST API development (PHP)

4. Frontend development with HTML/CSS/JS

5. Database design

6. Full-stack application flow

7. Fetch API + JSON handling

8. Debugging + environment setup

### 🔮 Future Enhancements

- User authentication

- Edit student feature

- Export data to CSV/PDF

- Add more subjects dynamically

- Build a Spring Boot backend

- Deploy online

#### 👩‍💻 Author

Created by Sakshi Wadekar
Full-Stack Java | Database | Web Development
