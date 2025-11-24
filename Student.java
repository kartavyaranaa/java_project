import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a student
 */
public class Student {
    private String studentId;
    private String name;
    private Branch branch;
    private List<Subject> subjects;
    private List<Grade> grades;
    private List<Attendance> attendanceRecords;

    public Student(String studentId, String name, Branch branch) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.subjects = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.attendanceRecords = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
        }
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void addAttendanceRecord(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    /**
     * Calculate overall attendance percentage for a subject
     */
    public double getAttendancePercentage(Subject subject) {
        long totalClasses = attendanceRecords.stream()
                .filter(a -> a.getSubject().equals(subject))
                .count();
        
        long presentClasses = attendanceRecords.stream()
                .filter(a -> a.getSubject().equals(subject) && a.isPresent())
                .count();

        return totalClasses > 0 ? (presentClasses * 100.0) / totalClasses : 0.0;
    }

    /**
     * Get grade for a specific subject
     */
    public Grade getGradeForSubject(Subject subject) {
        return grades.stream()
                .filter(g -> g.getSubject().equals(subject))
                .findFirst()
                .orElse(null);
    }

    /**
     * Calculate overall GPA
     */
    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            double gradePoints = grade.getGradePoints();
            int credits = grade.getSubject().getCredits();
            totalPoints += gradePoints * credits;
            totalCredits += credits;
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Branch: " + branch.getDisplayName();
    }
}

