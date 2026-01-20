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

