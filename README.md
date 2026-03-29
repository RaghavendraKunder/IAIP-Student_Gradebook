# IAIP Student Gradebook

A simple console-based Java Gradebook system for managing students and their subject grades. Ideal for learning Java fundamentals, collections, and basic CLI input handling.

## ✅ Project Structure

- `src/GradebookSystem.java` - Main program with all functionality.
- `bin/` - Compiled class files (if already built).

## 🛠️ Features

- Add a student by ID and name.
- Add or edit subject grades for an existing student.
- Display a detailed report for a student (subjects, marks, average).
- Delete a student from the system.
- Display all students and their reports.
- Interactive console menu.

## 📦 Requirements

- Java JDK 8 or later
- Command line (PowerShell / cmd on Windows; terminal on macOS/Linux)

## ▶️ How to Build and Run

1. Open terminal in project root (`IAIP-Student_Gradebook`).
2. Compile:
   - `javac -d bin src\\GradebookSystem.java`
3. Run:
   - `java -cp bin GradebookSystem`

> If you’re using non-Windows shell, replace backslashes with forward slashes: `src/GradebookSystem.java`.

## 🧩 Usage

Once started, the program shows options:

1. Add Student
2. Add/Edit Grades
3. Display Student Report
4. Delete Student
5. Display All Students
6. Exit

Follow prompts for IDs, names, subject names, and marks.

## 💡 Behavior Notes

- `Student.id` is an integer and used as a unique lookup key.
- Adding grades for existing subjects overwrites marks (HashMap behavior).
- Average is calculated from all entered subjects; if none, average is 0.
- No data persistence: all data lost on program exit.

## 🛠️ Code Overview

`Student` class:
- fields: `id`, `name`, `subjects` map
- methods: `addSubject`, `calculateAverage`, `displayReport`

`GradebookSystem` class:
- static `students` list
- methods: `addStudent`, `findStudent`, `addGrades`, `displayStudent`, `deleteStudent`, `displayAll`

## 📌 Improvements (optional)

- Add persistent storage (file / DB)
- Add validation for duplicate IDs, invalid marks
- Add sorting and summary statistics
- Add grade letter conversions and pass/fail status

