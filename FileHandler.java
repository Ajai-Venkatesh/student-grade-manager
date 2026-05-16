package util;

import model.Course;
import model.Grade;
import model.Student;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Handles reading and writing application data to CSV files in the /data directory.
 */
public class FileHandler {

    private static final String DATA_DIR      = "data/";
    private static final String STUDENTS_FILE = DATA_DIR + "students.csv";
    private static final String COURSES_FILE  = DATA_DIR + "courses.csv";
    private static final String GRADES_FILE   = DATA_DIR + "grades.csv";

    public FileHandler() {
        try { Files.createDirectories(Paths.get(DATA_DIR)); }
        catch (IOException e) { System.err.println("Warning: Could not create data directory."); }
    }

    // ── STUDENTS ──────────────────────────────────────────────────────────────

    public void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            pw.println("studentId,name,email,year");
            for (Student s : students) pw.println(s.toCSV());
        } catch (IOException e) { System.err.println("Error saving students: " + e.getMessage()); }
    }

    public List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (!line.trim().isEmpty()) list.add(Student.fromCSV(line.trim()));
            }
        } catch (IOException e) { System.err.println("Error loading students: " + e.getMessage()); }
        return list;
    }

    // ── COURSES ───────────────────────────────────────────────────────────────

    public void saveCourses(List<Course> courses) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(COURSES_FILE))) {
            pw.println("courseCode,courseName,instructor,credits");
            for (Course c : courses) pw.println(c.toCSV());
        } catch (IOException e) { System.err.println("Error saving courses: " + e.getMessage()); }
    }

    public List<Course> loadCourses() {
        List<Course> list = new ArrayList<>();
        File file = new File(COURSES_FILE);
        if (!file.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (!line.trim().isEmpty()) list.add(Course.fromCSV(line.trim()));
            }
        } catch (IOException e) { System.err.println("Error loading courses: " + e.getMessage()); }
        return list;
    }

    // ── GRADES ────────────────────────────────────────────────────────────────

    public void saveGrades(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(GRADES_FILE))) {
            pw.println("studentId,courseCode,score,semester");
            for (Student s : students)
                for (Grade g : s.getGrades()) pw.println(g.toCSV(s.getStudentId()));
        } catch (IOException e) { System.err.println("Error saving grades: " + e.getMessage()); }
    }

    public void loadGrades(Map<String, Student> students, Map<String, Course> courses) {
        File file = new File(GRADES_FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line; boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                if (line.trim().isEmpty()) continue;
                String[] p = line.trim().split(",");
                if (p.length < 4) continue;
                Student s = students.get(p[0]);
                Course  c = courses.get(p[1]);
                if (s != null && c != null)
                    s.addGrade(new Grade(c, Double.parseDouble(p[2]), p[3]));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading grades: " + e.getMessage());
        }
    }
}
