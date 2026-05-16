package model;

/**
 * Represents a course/subject in the system.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private String instructor;
    private int credits;

    public Course(String courseCode, String courseName, String instructor, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getCourseCode()  { return courseCode; }
    public String getCourseName()  { return courseName; }
    public String getInstructor()  { return instructor; }
    public int getCredits()        { return credits; }

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setCredits(int credits)          { this.credits = credits; }

    @Override
    public String toString() {
        return String.format("Course[Code=%s, Name=%s, Instructor=%s, Credits=%d]",
                courseCode, courseName, instructor, credits);
    }

    public String toCSV() {
        return String.join(",", courseCode, courseName, instructor, String.valueOf(credits));
    }

    public static Course fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Course(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
    }
}
