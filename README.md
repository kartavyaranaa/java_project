# University Student Management System

A comprehensive Java-based student management system for universities that tracks students across different branches, their subjects, grades, and attendance.

## Features

- **Student Management**: Add, remove, and view students
- **Branch Management**: Track students across different branches/departments
- **Subject Management**: Add and manage subjects/courses
- **Grade Management**: Record and calculate grades with GPA
- **Attendance Tracking**: Mark and track student attendance per subject
- **Statistics**: View branch-wise student counts and statistics

## Classes Overview

1. **Branch.java**: Enum representing different university branches
2. **Subject.java**: Represents a subject/course with code, name, and credits
3. **Student.java**: Represents a student with ID, name, branch, subjects, grades, and attendance
4. **Grade.java**: Represents grades with marks, letter grades, and grade points
5. **Attendance.java**: Represents attendance records with date and present/absent status
6. **StudentManagementSystem.java**: Main system class managing all operations
7. **Main.java**: Menu-driven interface for user interaction

## How to Compile and Run

1. Compile all Java files:
```bash
javac *.java
```

2. Run the main program:
```bash
java Main
```

## Usage

The system provides a menu-driven interface with the following options:

1. **Add Student**: Register a new student with ID, name, and branch
2. **Add Subject**: Add a new subject with code, name, and credits
3. **Enroll Student in Subject**: Enroll a student in a subject
4. **Add Grade**: Record grades for a student in a subject (0-100 marks)
5. **Mark Attendance**: Mark attendance for a student in a subject
6. **View Student Details**: View complete student information including grades and attendance
7. **View Branch Statistics**: See student count for each branch
8. **View Students by Branch**: List all students in a specific branch
9. **View All Students**: Display all registered students
10. **View All Subjects**: Display all available subjects
11. **Remove Student**: Remove a student from the system

## Grade System

- **A+**: 90-100 (4.0 points)
- **A**: 80-89 (3.5 points)
- **B+**: 70-79 (3.0 points)
- **B**: 60-69 (2.5 points)
- **C+**: 50-59 (2.0 points)
- **C**: 40-49 (1.5 points)
- **F**: Below 40 (0.0 points)

GPA is calculated based on weighted average using credits.

## Sample Branches

- Computer Science
- Electrical Engineering
- Mechanical Engineering
- Civil Engineering
- Electronics Engineering
- Information Technology
- Business Administration
- Mathematics
- Physics
- Chemistry

## Example Usage Flow

1. Add a student: "STU001", "John Doe", "Computer Science"
2. Enroll student in subject: "STU001" in "CS101"
3. Add grade: "STU001" in "CS101" with 85 marks
4. Mark attendance: "STU001" in "CS101" for today as present
5. View student details to see all information

