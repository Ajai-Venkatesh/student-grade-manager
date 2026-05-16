package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student in the Grade Management System.
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private int year;
    private List<Grade> grades;

    public Student(String studentId, String name, String email, int year) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.year = year;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.removeIf(g -> g.getCourse().getCourseCode().equals(grade.getCourse().getCourseCode()));
        grades.add(grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        double totalPoints = 0;
        int totalCredits = 0;
        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits();
            totalPoints += grade.getGradePoints() * credits;
            totalCredits += credits;
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public String getAcademicStanding() {
        double gpa = calculateGPA();
        if (gpa >= 3.7) return "Distinction";
        if (gpa >= 3.3) return "First Class";
        if (gpa >= 2.7) return "Second Class";
        if (gpa >= 2.0) return "Pass";
        return "At Risk";
    }

    public String getStudentId() { return studentId; }
    public String getName()      { return name; }
    public String getEmail()     { return email; }
    public int getYear()         { return year; }
    public List<Grade> getGrades() { return grades; }

    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setYear(int year)      { this.year = year; }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s, Year=%d, GPA=%.2f (%s)]",
                studentId, name, year, calculateGPA(), getAcademicStanding());
    }

    public String toCSV() {
        return String.join(",", studentId, name, email, String.valueOf(year));
    }

    public static Student fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Student(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }
}
