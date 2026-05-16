# 🎓 Student Grade Management System

A command-line Java application to manage students, courses, and grades — built with clean OOP design, CSV file persistence, and live GPA/analytics reporting.

---

## 📋 Features

- **Student Management** — Add, remove, search students by name or ID
- **Course Management** — Register courses with code, name, instructor, and credit hours
- **Grade Assignment** — Assign scores (0–100), auto-converts to letter grade & GPA points
- **GPA Calculation** — Weighted GPA on a 4.0 scale based on course credits
- **Report Card** — Full per-student transcript with letter grades and academic standing
- **Leaderboard** — Ranks all students by GPA in descending order
- **Course Statistics** — Average, highest, lowest score + pass rate per course
- **Data Persistence** — All data saved automatically to CSV files in `/data`

---

## 🏗️ Project Structure

```
StudentGradeManager/
├── src/
│   ├── Main.java                  # Entry point & CLI menus
│   ├── model/
│   │   ├── Student.java           # Student entity + GPA logic
│   │   ├── Course.java            # Course entity
│   │   └── Grade.java             # Grade entity (score → letter → GPA points)
│   ├── manager/
│   │   └── GradeManager.java      # Core service layer (CRUD + reports)
│   └── util/
│       └── FileHandler.java       # CSV read/write persistence
├── data/                          # Auto-created at runtime
│   ├── students.csv
│   ├── courses.csv
│   └── grades.csv
└── README.md
```

---

## ⚙️ Requirements

- Java 17 or higher (uses `switch` expressions)
- No external libraries needed — pure Java

---

## 🚀 How to Run

### 1. Clone the repository
```bash
git clone https://github.com/your-username/StudentGradeManager.git
cd StudentGradeManager
```

### 2. Compile all source files
```bash
javac -d out src/model/*.java src/util/*.java src/manager/*.java src/Main.java
```

### 3. Run the application
```bash
java -cp out Main
```

> The `/data` directory will be created automatically on first run.

---

## 🖥️ Sample Usage

```
  =====================================================
       STUDENT GRADE MANAGEMENT SYSTEM  v1.0
  =====================================================
  Students: 3   |   Courses: 4
  =====================================================

  ---- MAIN MENU ----
  1. Student Management
  2. Course Management
  3. Grade Management
  4. Reports & Analytics
  0. Exit
  >
```

### Sample Report Card Output
```
============================================================
  STUDENT REPORT CARD
============================================================
  ID       : S001
  Name     : Arjun Sharma
  Email    : arjun@college.edu
  Year     : 2
------------------------------------------------------------
  Code       Course Name                Score  Grade
------------------------------------------------------------
  CS101      Data Structures            88.0%  A
  CS102      Database Systems           75.5%  B+
  MA101      Discrete Mathematics       91.0%  A+
------------------------------------------------------------
  GPA      : 3.77 / 4.00
  Standing : Distinction
============================================================
```

---

## 📊 Grade Scale

| Score     | Letter | GPA Points |
|-----------|--------|------------|
| 90 – 100  | A+     | 4.0        |
| 85 – 89   | A      | 4.0        |
| 80 – 84   | A-     | 3.7        |
| 75 – 79   | B+     | 3.3        |
| 70 – 74   | B      | 3.0        |
| 65 – 69   | B-     | 2.7        |
| 60 – 64   | C+     | 2.3        |
| 55 – 59   | C      | 2.0        |
| 50 – 54   | C-     | 1.7        |
| 45 – 49   | D      | 1.0        |
| 0  – 44   | F      | 0.0        |

---

## 🧠 Concepts Demonstrated

- **OOP** — Encapsulation, inheritance-ready design, separation of concerns
- **Collections** — `HashMap`, `ArrayList`, `Stream` API with lambdas
- **File I/O** — BufferedReader / PrintWriter for CSV persistence
- **Exception Handling** — Input validation with graceful error messages
- **Java 17+** — Switch expressions, enhanced for loops

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first.

---

## 📄 License

[MIT](https://choosealicense.com/licenses/mit/)
