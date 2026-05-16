import manager.GradeManager;
import model.Course;
import model.Student;

import java.util.List;
import java.util.Scanner;

/**
 * Student Grade Management System
 * Entry point with interactive CLI menu.
 *
 * How to compile and run:
 *   javac -d out src/model/*.java src/util/*.java src/manager/*.java src/Main.java
 *   java -cp out Main
 */
public class Main {

    private static final GradeManager manager = new GradeManager();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> studentMenu();
                case "2" -> courseMenu();
                case "3" -> gradeMenu();
                case "4" -> reportMenu();
                case "0" -> { System.out.println("\n  Goodbye!\n"); running = false; }
                default  -> System.out.println("  [!] Invalid option. Try again.");
            }
        }
        sc.close();
    }

    // ── MENUS ─────────────────────────────────────────────────────────────────

    static void printBanner() {
        System.out.println();
        System.out.println("  =====================================================");
        System.out.println("       STUDENT GRADE MANAGEMENT SYSTEM  v1.0          ");
        System.out.println("  =====================================================");
        System.out.printf("  Students: %d   |   Courses: %d%n",
                manager.getTotalStudents(), manager.getTotalCourses());
        System.out.println("  =====================================================");
    }

    static void printMainMenu() {
        System.out.println("\n  ---- MAIN MENU ----");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Grade Management");
        System.out.println("  4. Reports & Analytics");
        System.out.println("  0. Exit");
        System.out.print("  > ");
    }

    // ── STUDENT MENU ──────────────────────────────────────────────────────────

    static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ---- STUDENT MANAGEMENT ----");
            System.out.println("  1. Add Student");
            System.out.println("  2. Remove Student");
            System.out.println("  3. View All Students");
            System.out.println("  4. Search by Name");
            System.out.println("  0. Back");
            System.out.print("  > ");
            switch (sc.nextLine().trim()) {
                case "1" -> addStudent();
                case "2" -> removeStudent();
                case "3" -> listStudents();
                case "4" -> searchStudent();
                case "0" -> back = true;
                default  -> System.out.println("  [!] Invalid option.");
            }
        }
    }

    static void addStudent() {
        System.out.print("  Student ID   : "); String id    = sc.nextLine().trim();
        System.out.print("  Full Name    : "); String name  = sc.nextLine().trim();
        System.out.print("  Email        : "); String email = sc.nextLine().trim();
        System.out.print("  Year (1-4)   : ");
        int year = 1;
        try { year = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { }
        if (manager.addStudent(new Student(id, name, email, year)))
            System.out.println("  [+] Student added successfully.");
    }

    static void removeStudent() {
        System.out.print("  Student ID to remove: ");
        String id = sc.nextLine().trim();
        if (manager.removeStudent(id))
            System.out.println("  [-] Student removed.");
    }

    static void listStudents() {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) { System.out.println("  No students registered."); return; }
        System.out.println("\n  ---- ALL STUDENTS (" + students.size() + ") ----");
        System.out.printf("  %-10s %-22s %-28s %-4s  %s%n", "ID", "Name", "Email", "Yr", "GPA");
        System.out.println("  " + "-".repeat(75));
        for (Student s : students)
            System.out.printf("  %-10s %-22s %-28s %-4d  %.2f%n",
                    s.getStudentId(), s.getName(), s.getEmail(), s.getYear(), s.calculateGPA());
    }

    static void searchStudent() {
        System.out.print("  Enter name keyword: ");
        List<Student> results = manager.searchByName(sc.nextLine().trim());
        if (results.isEmpty()) { System.out.println("  No students found."); return; }
        results.forEach(s -> System.out.println("  " + s));
    }

    // ── COURSE MENU ───────────────────────────────────────────────────────────

    static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ---- COURSE MANAGEMENT ----");
            System.out.println("  1. Add Course");
            System.out.println("  2. Remove Course");
            System.out.println("  3. View All Courses");
            System.out.println("  0. Back");
            System.out.print("  > ");
            switch (sc.nextLine().trim()) {
                case "1" -> addCourse();
                case "2" -> removeCourse();
                case "3" -> listCourses();
                case "0" -> back = true;
                default  -> System.out.println("  [!] Invalid option.");
            }
        }
    }

    static void addCourse() {
        System.out.print("  Course Code   : "); String code  = sc.nextLine().trim();
        System.out.print("  Course Name   : "); String name  = sc.nextLine().trim();
        System.out.print("  Instructor    : "); String instr = sc.nextLine().trim();
        System.out.print("  Credits       : ");
        int credits = 3;
        try { credits = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { }
        if (manager.addCourse(new Course(code, name, instr, credits)))
            System.out.println("  [+] Course added successfully.");
    }

    static void removeCourse() {
        System.out.print("  Course code to remove: ");
        if (manager.removeCourse(sc.nextLine().trim()))
            System.out.println("  [-] Course removed.");
    }

    static void listCourses() {
        List<Course> courses = manager.getAllCourses();
        if (courses.isEmpty()) { System.out.println("  No courses registered."); return; }
        System.out.println("\n  ---- ALL COURSES (" + courses.size() + ") ----");
        System.out.printf("  %-10s %-28s %-22s %s%n", "Code", "Name", "Instructor", "Credits");
        System.out.println("  " + "-".repeat(70));
        for (Course c : courses)
            System.out.printf("  %-10s %-28s %-22s %d%n",
                    c.getCourseCode(), c.getCourseName(), c.getInstructor(), c.getCredits());
    }

    // ── GRADE MENU ────────────────────────────────────────────────────────────

    static void gradeMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ---- GRADE MANAGEMENT ----");
            System.out.println("  1. Assign / Update Grade");
            System.out.println("  0. Back");
            System.out.print("  > ");
            switch (sc.nextLine().trim()) {
                case "1" -> assignGrade();
                case "0" -> back = true;
                default  -> System.out.println("  [!] Invalid option.");
            }
        }
    }

    static void assignGrade() {
        System.out.print("  Student ID  : "); String sid  = sc.nextLine().trim();
        System.out.print("  Course Code : "); String code = sc.nextLine().trim();
        System.out.print("  Score (0-100): ");
        double score = 0;
        try { score = Double.parseDouble(sc.nextLine().trim()); } catch (Exception e) {
            System.out.println("  [!] Invalid score."); return;
        }
        System.out.print("  Semester    : "); String sem = sc.nextLine().trim();
        if (manager.assignGrade(sid, code, score, sem))
            System.out.println("  [+] Grade assigned successfully.");
    }

    // ── REPORT MENU ───────────────────────────────────────────────────────────

    static void reportMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ---- REPORTS & ANALYTICS ----");
            System.out.println("  1. Student Report Card");
            System.out.println("  2. Class Leaderboard (by GPA)");
            System.out.println("  3. Course Statistics");
            System.out.println("  0. Back");
            System.out.print("  > ");
            switch (sc.nextLine().trim()) {
                case "1" -> { System.out.print("  Student ID: "); manager.printStudentReport(sc.nextLine().trim()); }
                case "2" -> manager.printLeaderboard();
                case "3" -> { System.out.print("  Course Code: "); manager.printCourseStatistics(sc.nextLine().trim()); }
                case "0" -> back = true;
                default  -> System.out.println("  [!] Invalid option.");
            }
        }
    }
}
