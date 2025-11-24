/**
 * Class representing a grade for a subject
 */
public class Grade {
    private Subject subject;
    private double marks;
    private String letterGrade;
    private double gradePoints;

    public Grade(Subject subject, double marks) {
        this.subject = subject;
        this.marks = marks;
        this.letterGrade = calculateLetterGrade(marks);
        this.gradePoints = calculateGradePoints(marks);
    }

    private String calculateLetterGrade(double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C+";
        if (marks >= 40) return "C";
        return "F";
    }

    private double calculateGradePoints(double marks) {
        if (marks >= 90) return 4.0;
        if (marks >= 80) return 3.5;
        if (marks >= 70) return 3.0;
        if (marks >= 60) return 2.5;
        if (marks >= 50) return 2.0;
        if (marks >= 40) return 1.5;
        return 0.0;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.letterGrade = calculateLetterGrade(marks);
        this.gradePoints = calculateGradePoints(marks);
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public double getGradePoints() {
        return gradePoints;
    }

    @Override
    public String toString() {
        return subject.getSubjectName() + ": " + marks + " (" + letterGrade + ", " + gradePoints + " points)";
    }
}

