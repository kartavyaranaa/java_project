import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class for managing students, their grades, and attendance
 */
public class StudentManagementSystem {
    private Map<String, Student> students;
    private Map<String, Subject> subjects;
    private Map<Branch, List<Student>> branchStudents;

    public StudentManagementSystem() {
        this.students = new HashMap<>();
        this.subjects = new HashMap<>();
        this.branchStudents = new HashMap<>();
        
        // Initialize branch lists
        for (Branch branch : Branch.values()) {
            branchStudents.put(branch, new ArrayList<>());
        }
    }

    // Student Management
    public void addStudent(String studentId, String name, Branch branch) {
        addStudent(studentId, name, branch, false);
    }

    public void addStudent(String studentId, String name, Branch branch, boolean silent) {
        if (students.containsKey(studentId)) {
            if (!silent) {
                System.out.println("Student with ID " + studentId + " already exists!");
            }
            return;
        }
        
        Student student = new Student(studentId, name, branch);
        students.put(studentId, student);
        branchStudents.get(branch).add(student);
        if (!silent) {
            System.out.println("Student added successfully!");
        }
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public void removeStudent(String studentId) {
        Student student = students.remove(studentId);
        if (student != null) {
            branchStudents.get(student.getBranch()).remove(student);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> getStudentsByBranch(Branch branch) {
        return new ArrayList<>(branchStudents.get(branch));
    }

    public int getStudentCountByBranch(Branch branch) {
        return branchStudents.get(branch).size();
    }

    // Subject Management
    public void addSubject(String subjectCode, String subjectName, int credits) {
        Subject subject = new Subject(subjectCode, subjectName, credits);
        subjects.put(subjectCode, subject);
        System.out.println("Subject added successfully!");
    }

    public Subject getSubject(String subjectCode) {
        return subjects.get(subjectCode);
    }

    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects.values());
    }

    public void enrollStudentInSubject(String studentId, String subjectCode) {
        Student student = students.get(studentId);
        Subject subject = subjects.get(subjectCode);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        if (subject == null) {
            System.out.println("Subject not found!");
            return;
        }
        
        student.addSubject(subject);
        System.out.println("Student enrolled in subject successfully!");
    }

    // Grade Management
    public void addGrade(String studentId, String subjectCode, double marks) {
        Student student = students.get(studentId);
        Subject subject = subjects.get(subjectCode);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        if (subject == null) {
            System.out.println("Subject not found!");
            return;
        }
        
        // Remove existing grade if any
        student.getGrades().removeIf(g -> g.getSubject().equals(subject));
        
        Grade grade = new Grade(subject, marks);
        student.addGrade(grade);
        System.out.println("Grade added successfully!");
    }

    public void displayStudentGrades(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n=== Grades for " + student.getName() + " ===");
        if (student.getGrades().isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            for (Grade grade : student.getGrades()) {
                System.out.println(grade);
            }
            System.out.println("Overall GPA: " + String.format("%.2f", student.calculateGPA()));
        }
    }

    // Attendance Management
    public void markAttendance(String studentId, String subjectCode, java.time.LocalDate date, boolean present) {
        Student student = students.get(studentId);
        Subject subject = subjects.get(subjectCode);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        if (subject == null) {
            System.out.println("Subject not found!");
            return;
        }
        
        Attendance attendance = new Attendance(subject, date, present);
        student.addAttendanceRecord(attendance);
        System.out.println("Attendance marked successfully!");
    }

    public void displayStudentAttendance(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n=== Attendance for " + student.getName() + " ===");
        if (student.getAttendanceRecords().isEmpty()) {
            System.out.println("No attendance records yet.");
        } else {
            Map<Subject, List<Attendance>> attendanceBySubject = student.getAttendanceRecords().stream()
                    .collect(Collectors.groupingBy(Attendance::getSubject));
            
            for (Map.Entry<Subject, List<Attendance>> entry : attendanceBySubject.entrySet()) {
                Subject subject = entry.getKey();
                List<Attendance> records = entry.getValue();
                double percentage = student.getAttendancePercentage(subject);
                
                System.out.println("\n" + subject.getSubjectName() + ":");
                System.out.println("  Attendance Percentage: " + String.format("%.2f", percentage) + "%");
                System.out.println("  Total Classes: " + records.size());
                System.out.println("  Present: " + records.stream().filter(Attendance::isPresent).count());
                System.out.println("  Absent: " + records.stream().filter(a -> !a.isPresent()).count());
            }
        }
    }

    // Statistics
    public void displayBranchStatistics() {
        System.out.println("\n=== Branch Statistics ===");
        for (Branch branch : Branch.values()) {
            int count = getStudentCountByBranch(branch);
            System.out.println(branch.getDisplayName() + ": " + count + " students");
        }
    }

    public void displayAllStudents() {
        System.out.println("\n=== All Students ===");
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    public void displayStudentsByBranch(Branch branch) {
        List<Student> branchStudentList = getStudentsByBranch(branch);
        System.out.println("\n=== Students in " + branch.getDisplayName() + " ===");
        if (branchStudentList.isEmpty()) {
            System.out.println("No students in this branch.");
        } else {
            for (Student student : branchStudentList) {
                System.out.println(student);
            }
        }
    }
}

