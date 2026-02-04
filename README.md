# 📚 Student Management System  
**Full-Stack Web + Java Project**

A full-stack Student Management System built to understand **end-to-end data flow** — from frontend input to backend processing and database storage.

This project combines:
- a **Java console application** (OOP + JDBC)
- a **PHP backend API**
- a **web frontend (HTML, CSS, JavaScript)**
- a **MariaDB / MySQL database**

---

## ✨ Features

### 🖥 Java Console Application
- Menu-driven system
- Add, view, search, and delete students
- Object-oriented design using `Student` class
- JDBC integration with MySQL / MariaDB
- Automatic calculation of:
  - Total marks  
  - Average percentage  
  - Grade (A+, A, B, C, D, F)

---

### 🌐 Web Application
- Modern dashboard-style UI
- Add students via web form
- View all students from database
- Delete individual students or clear all records
- Real-time statistics:
  - Total students
  - Highest score
  - Lowest score
  - Average score
- Dynamic UI updates without page reload

---

## 🗄 Database
- MySQL / MariaDB
- Database: `student_management`
- Table: `students`
- Stores:
  - Student details
  - Subject marks
  - Total, average, and grade
- Constraints to ensure valid data

---

## 🔌 PHP Backend API

The PHP backend acts as a **REST-style API** between the frontend and database.

**Endpoints:**
- `GET  /api.php?action=getAll`
- `POST /api.php?action=add`
- `POST /api.php?action=delete`
- `POST /api.php?action=deleteAll`

All communication uses **JSON**.

---

## 📁 Project Structure

StudentManagement/
│
├── Student.java
├── DatabaseConnection.java
├── MainWithDatabase.java
│
├── database.sql
├── api.php
│
├── index.html
├── style.css
├── scripts.js
│
└── README.md


---

## 🧩 Java: Student Class

**Fields**
- `name` (String)
- `rollNumber` (int)
- `mathMarks` (double)
- `scienceMarks` (double)
- `englishMarks` (double)

**Methods**
- `calculateTotal()`
- `calculateAverage()`
- `calculateGrade()`
- Getter methods

---

## 🧮 Grading Logic

| Average (%) | Grade |
|------------|-------|
| 90–100     | A+    |
| 80–89      | A     |
| 70–79      | B     |
| 60–69      | C     |
| 50–59      | D     |
| < 50       | F     |

---

## 🛠 Tech Stack

**Frontend**
- HTML
- CSS
- JavaScript (Fetch API)

**Backend**
- PHP
- Java (OOP + JDBC)

**Database**
- MariaDB

**Tools**
- VS Code
- Linux
- Git & GitHub
- PHP built-in server

---

## ⚙️ Setup & Run

### 1️⃣ Database Setup
```sql
CREATE DATABASE student_management;
USE student_management;
SOURCE database.sql;
2️⃣ Run Java Application
javac *.java
java MainWithDatabase

3️⃣ Start PHP Backend
php -S localhost:8000


Backend URL:

http://localhost:8000/api.php

4️⃣ Open Web App
http://localhost:8000/index.html

🧠 What This Project Shows

Java OOP principles

JDBC + SQL integration

PHP backend development

REST-style API usage

Frontend-backend communication

Asynchronous JavaScript (Fetch API)

Real-time UI updates from database

Full-stack debugging experience

🔮 Future Improvements

Authentication

Edit/update student records

Search and pagination

Export data (CSV / PDF)

Spring Boot backend

Cloud deployment

👩‍💻 Author

Sakshi Wadekar
Full-Stack Development | Java | Databases | Web Technologies
