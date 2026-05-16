package model;

/**
 * Represents a grade assigned to a student for a specific course.
 */
public class Grade {
    private Course course;
    private double score;
    private String letterGrade;
    private String semester;

    public Grade(Course course, double score, String semester) {
        if (score < 0 || score > 100)
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        this.course   = course;
        this.score    = score;
        this.semester = semester;
        this.letterGrade = convertToLetterGrade(score);
    }

    private String convertToLetterGrade(double score) {
        if (score >= 90) return "A+";
        if (score >= 85) return "A";
        if (score >= 80) return "A-";
        if (score >= 75) return "B+";
        if (score >= 70) return "B";
        if (score >= 65) return "B-";
        if (score >= 60) return "C+";
        if (score >= 55) return "C";
        if (score >= 50) return "C-";
        if (score >= 45) return "D";
        return "F";
    }

    public double getGradePoints() {
        return switch (letterGrade) {
            case "A+", "A" -> 4.0;
            case "A-"      -> 3.7;
            case "B+"      -> 3.3;
            case "B"       -> 3.0;
            case "B-"      -> 2.7;
            case "C+"      -> 2.3;
            case "C"       -> 2.0;
            case "C-"      -> 1.7;
            case "D"       -> 1.0;
            default        -> 0.0;
        };
    }

    public boolean isPassing()     { return score >= 50.0; }
    public Course getCourse()      { return course; }
    public double getScore()       { return score; }
    public String getLetterGrade() { return letterGrade; }
    public String getSemester()    { return semester; }

    @Override
    public String toString() {
        return String.format("Grade[Course=%s, Score=%.1f, Letter=%s, Points=%.1f, Semester=%s]",
                course.getCourseCode(), score, letterGrade, getGradePoints(), semester);
    }

    public String toCSV(String studentId) {
        return String.join(",", studentId, course.getCourseCode(), String.valueOf(score), semester);
    }
}
