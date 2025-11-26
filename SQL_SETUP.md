# SQL Database Setup Instructions

## Prerequisites
- MySQL Server installed on your system
- MySQL Workbench or command-line access

## Setup Steps

### 1. Install MySQL on Fedora
```bash
sudo dnf install mysql-server
sudo systemctl start mysqld
sudo systemctl enable mysqld
```

### 2. Secure MySQL Installation
```bash
sudo mysql_secure_installation
```

### 3. Login to MySQL
```bash
mysql -u root -p
```

### 4. Run the Database Script
```bash
mysql -u root -p < database.sql
```

Or from MySQL prompt:
```sql
source /path/to/database.sql;
```

### 5. Verify Database Creation
```sql
SHOW DATABASES;
USE student_management;
SHOW TABLES;
SELECT * FROM students;
```

## Download MySQL JDBC Driver

1. Download from: https://dev.mysql.com/downloads/connector/j/
2. Extract the JAR file
3. Place `mysql-connector-java-X.X.XX.jar` in your project folder

## Compile and Run with Database
```bash
# Compile with JDBC driver
javac -cp ".:mysql-connector-java-8.0.33.jar" Student.java DatabaseConnection.java MainWithDatabase.java

# Run
java -cp ".:mysql-connector-java-8.0.33.jar" MainWithDatabase
```

## Configuration

Edit `DatabaseConnection.java` if your MySQL credentials are different:
- URL: `jdbc:mysql://localhost:3306/student_management`
- USER: Your MySQL username (default: `root`)
- PASSWORD: Your MySQL password
```

---

## **NOW FOR THE FORM ANSWERS:**

Since everything is in ONE project, use the SAME link for all:

### **Java Link:**
```
https://github.com/YOUR_USERNAME/student-management-system
```

### **SQL Link:**
```
https://github.com/YOUR_USERNAME/student-management-system
(Database: database.sql, Java integration: DatabaseConnection.java)
```

### **HTML/CSS/JS Link:**
```
https://github.com/YOUR_USERNAME/student-management-system
(Interactive web app: interactive-app.html)