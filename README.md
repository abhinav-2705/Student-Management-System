# Student Management System

This is a simple Student Management System built using **Java Swing** and **MySQL**. It provides a GUI to manage student records, allowing users to add new students to the database.

## Features
- Add student details via a GUI
- MySQL database integration
- Simple and user-friendly interface

## Technologies Used
- **Java Swing** (GUI)
- **MySQL** (Database)
- **JDBC** (Java Database Connectivity)

## Setup Instructions

### 1. Install MySQL and Create the Database
- Run the SQL script `student_db.sql` to set up the database.
  ```sh
  mysql -u root -p < student_db.sql

### 2. Configure Database Connection
Open StudentManagementSystem.java
Update the JDBC URL, username, and password in the connectToDatabase() method.

### 3. Build and Run
# Using Maven:
mvn compile
mvn exec:java -Dexec.mainClass="StudentManagementSystem"

# Using Gradle:
gradle build
java -cp build/classes/java/main StudentManagementSystem

# Using Java Directly:
javac StudentManagementSystem.java
java StudentManagementSystem
