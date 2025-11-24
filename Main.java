import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class with menu-driven interface for the Student Management System
 */
public class Main {
    private static StudentManagementSystem system;
    private static LibraryManagement library;
    private static Scanner scanner;

    public static void main(String[] args) {
        system = new StudentManagementSystem();
        library = new LibraryManagement();
        scanner = new Scanner(System.in);
        
        // Add some sample subjects
        initializeSampleSubjects();
        
        // Add some sample books
        initializeSampleBooks();
        
        System.out.println("========================================");
        System.out.println("  University Student Management System");
        System.out.println("========================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    enrollStudentInSubject();
                    break;
                case 4:
                    addGrade();
                    break;
                case 5:
                    markAttendance();
                    break;
                case 6:
                    viewStudentDetails();
                    break;
                case 7:
                    viewBranchStatistics();
                    break;
                case 8:
                    viewStudentsByBranch();
                    break;
                case 9:
                    viewAllStudents();
                    break;
                case 10:
                    viewAllSubjects();
                    break;
                case 11:
                    removeStudent();
                    break;
                case 12:
                    bulkRegisterStudents();
                    break;
                case 13:
                    enrollStudentInMultipleSubjects();
                    break;
                case 14:
                    bulkEnrollBranchInSubjects();
                    break;
                case 15:
                    addBook();
                    break;
                case 16:
                    borrowBook();
                    break;
                case 17:
                    returnBook();
                    break;
                case 18:
                    viewAllBooks();
                    break;
                case 19:
                    searchBooks();
                    break;
                case 20:
                    viewStudentBorrowedBooks();
                    break;
                case 21:
                    viewOverdueBooks();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the Student Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1.  Add Student");
        System.out.println("2.  Add Subject");
        System.out.println("3.  Enroll Student in Subject");
        System.out.println("4.  Add Grade");
        System.out.println("5.  Mark Attendance");
        System.out.println("6.  View Student Details");
        System.out.println("7.  View Branch Statistics");
        System.out.println("8.  View Students by Branch");
        System.out.println("9.  View All Students");
        System.out.println("10. View All Subjects");
        System.out.println("11. Remove Student");
        System.out.println("12. Bulk Register Students (Custom Range)");
        System.out.println("13. Enroll Student in Multiple Subjects");
        System.out.println("14. Bulk Enroll Branch Students in Subjects");
        System.out.println("\n--- LIBRARY MANAGEMENT ---");
        System.out.println("15. Add Book to Library");
        System.out.println("16. Borrow Book");
        System.out.println("17. Return Book");
        System.out.println("18. View All Books");
        System.out.println("19. Search Books");
        System.out.println("20. View Student's Borrowed Books");
        System.out.println("21. View Overdue Books");
        System.out.println("0.  Exit");
        System.out.println("===============================");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.println("Available Branches:");
        Branch[] branches = Branch.values();
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i].getDisplayName());
        }
        
        System.out.print("Select Branch (1-" + branches.length + "): ");
        try {
            int branchChoice = Integer.parseInt(scanner.nextLine().trim());
            if (branchChoice >= 1 && branchChoice <= branches.length) {
                Branch branch = branches[branchChoice - 1];
                system.addStudent(studentId, name, branch);
            } else {
                System.out.println("Invalid branch selection!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void addSubject() {
        System.out.println("\n--- Add Subject ---");
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine().trim();
        
        System.out.print("Enter Subject Name: ");
        String subjectName = scanner.nextLine().trim();
        
        System.out.print("Enter Credits: ");
        try {
            int credits = Integer.parseInt(scanner.nextLine().trim());
            system.addSubject(subjectCode, subjectName, credits);
        } catch (NumberFormatException e) {
            System.out.println("Invalid credits input!");
        }
    }

    private static void enrollStudentInSubject() {
        System.out.println("\n--- Enroll Student in Subject ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine().trim();
        
        system.enrollStudentInSubject(studentId, subjectCode);
    }

    private static void addGrade() {
        System.out.println("\n--- Add Grade ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine().trim();
        
        System.out.print("Enter Marks (0-100): ");
        try {
            double marks = Double.parseDouble(scanner.nextLine().trim());
            if (marks >= 0 && marks <= 100) {
                system.addGrade(studentId, subjectCode, marks);
            } else {
                System.out.println("Marks should be between 0 and 100!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks input!");
        }
    }

    private static void markAttendance() {
        System.out.println("\n--- Mark Attendance ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine().trim();
        
        System.out.print("Enter Date (YYYY-MM-DD) or press Enter for today: ");
        String dateInput = scanner.nextLine().trim();
        LocalDate date;
        
        if (dateInput.isEmpty()) {
            date = LocalDate.now();
        } else {
            try {
                date = LocalDate.parse(dateInput);
            } catch (Exception e) {
                System.out.println("Invalid date format! Using today's date.");
                date = LocalDate.now();
            }
        }
        
        System.out.print("Present? (y/n): ");
        String presentInput = scanner.nextLine().trim().toLowerCase();
        boolean present = presentInput.equals("y") || presentInput.equals("yes");
        
        system.markAttendance(studentId, subjectCode, date, present);
    }

    private static void viewStudentDetails() {
        System.out.println("\n--- View Student Details ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = system.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n" + student);
        System.out.println("\nSubjects Enrolled:");
        if (student.getSubjects().isEmpty()) {
            System.out.println("  No subjects enrolled.");
        } else {
            for (Subject subject : student.getSubjects()) {
                System.out.println("  - " + subject);
            }
        }
        
        system.displayStudentGrades(studentId);
        system.displayStudentAttendance(studentId);
    }

    private static void viewBranchStatistics() {
        system.displayBranchStatistics();
    }

    private static void viewStudentsByBranch() {
        System.out.println("\n--- View Students by Branch ---");
        System.out.println("Available Branches:");
        Branch[] branches = Branch.values();
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i].getDisplayName());
        }
        
        System.out.print("Select Branch (1-" + branches.length + "): ");
        try {
            int branchChoice = Integer.parseInt(scanner.nextLine().trim());
            if (branchChoice >= 1 && branchChoice <= branches.length) {
                Branch branch = branches[branchChoice - 1];
                system.displayStudentsByBranch(branch);
            } else {
                System.out.println("Invalid branch selection!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void viewAllStudents() {
        system.displayAllStudents();
    }

    private static void viewAllSubjects() {
        System.out.println("\n=== All Subjects ===");
        var subjects = system.getAllSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects registered.");
        } else {
            for (Subject subject : subjects) {
                System.out.println(subject);
            }
        }
    }

    private static void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        system.removeStudent(studentId);
    }

    private static void bulkRegisterStudents() {
        System.out.println("\n--- Bulk Register Students ---");
        System.out.println("Format: 24bce[ID_NUMBER]");
        
        System.out.print("Enter starting ID number (default: 10001): ");
        String startIdInput = scanner.nextLine().trim();
        int startId = startIdInput.isEmpty() ? 10001 : Integer.parseInt(startIdInput);
        
        System.out.print("Enter number of students to register (default: 110): ");
        String countInput = scanner.nextLine().trim();
        int count = countInput.isEmpty() ? 110 : Integer.parseInt(countInput);
        
        if (count <= 0) {
            System.out.println("Invalid number of students!");
            return;
        }
        
        int endId = startId + count - 1;
        System.out.println("This will register students from 24bce" + startId + " to 24bce" + endId);
        
        System.out.println("\nAvailable Branches:");
        Branch[] branches = Branch.values();
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i].getDisplayName());
        }
        
        System.out.print("Select Branch for all students (1-" + branches.length + "): ");
        try {
            int branchChoice = Integer.parseInt(scanner.nextLine().trim());
            if (branchChoice >= 1 && branchChoice <= branches.length) {
                Branch branch = branches[branchChoice - 1];
                
                System.out.print("Enter name prefix (e.g., 'Student' will create 'Student 1', 'Student 2', etc.) or press Enter for default: ");
                String namePrefix = scanner.nextLine().trim();
                if (namePrefix.isEmpty()) {
                    namePrefix = "Student";
                }
                
                System.out.println("\nRegistering " + count + " students...");
                int successCount = 0;
                int skipCount = 0;
                
                for (int i = startId; i <= endId; i++) {
                    String studentId = "24bce" + i;
                    String studentName = namePrefix + " " + (i - startId + 1);
                    
                    // Check if student already exists
                    if (system.getStudent(studentId) == null) {
                        system.addStudent(studentId, studentName, branch, true); // silent mode
                        successCount++;
                    } else {
                        skipCount++;
                    }
                    
                    // Show progress every 50 students
                    if ((i - startId + 1) % 50 == 0) {
                        System.out.println("Progress: " + (i - startId + 1) + "/" + count + " students registered...");
                    }
                }
                
                System.out.println("\nBulk registration completed!");
                System.out.println("Successfully registered: " + successCount + " students");
                if (skipCount > 0) {
                    System.out.println("Skipped (already exists): " + skipCount + " students");
                }
                System.out.println("Total: " + (successCount + skipCount) + " students");
            } else {
                System.out.println("Invalid branch selection!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter valid numbers.");
        }
    }

    private static void enrollStudentInMultipleSubjects() {
        System.out.println("\n--- Enroll Student in Multiple Subjects ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = system.getStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        var allSubjects = system.getAllSubjects();
        if (allSubjects.isEmpty()) {
            System.out.println("No subjects available. Please add subjects first.");
            return;
        }
        
        System.out.println("\nAvailable Subjects:");
        for (int i = 0; i < allSubjects.size(); i++) {
            System.out.println((i + 1) + ". " + allSubjects.get(i));
        }
        
        System.out.print("\nEnter subject numbers to enroll (comma-separated, e.g., 1,2,3): ");
        String input = scanner.nextLine().trim();
        String[] indices = input.split(",");
        
        int enrolledCount = 0;
        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr.trim()) - 1;
                if (index >= 0 && index < allSubjects.size()) {
                    Subject subject = allSubjects.get(index);
                    system.enrollStudentInSubject(studentId, subject.getSubjectCode());
                    enrolledCount++;
                } else {
                    System.out.println("Invalid subject number: " + (index + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + indexStr);
            }
        }
        
        System.out.println("\nEnrolled student in " + enrolledCount + " subject(s).");
    }

    private static void bulkEnrollBranchInSubjects() {
        System.out.println("\n--- Bulk Enroll Branch Students in Subjects ---");
        
        System.out.println("Available Branches:");
        Branch[] branches = Branch.values();
        for (int i = 0; i < branches.length; i++) {
            System.out.println((i + 1) + ". " + branches[i].getDisplayName());
        }
        
        System.out.print("Select Branch (1-" + branches.length + "): ");
        try {
            int branchChoice = Integer.parseInt(scanner.nextLine().trim());
            if (branchChoice < 1 || branchChoice > branches.length) {
                System.out.println("Invalid branch selection!");
                return;
            }
            
            Branch branch = branches[branchChoice - 1];
            var branchStudents = system.getStudentsByBranch(branch);
            
            if (branchStudents.isEmpty()) {
                System.out.println("No students found in this branch!");
                return;
            }
            
            var allSubjects = system.getAllSubjects();
            if (allSubjects.isEmpty()) {
                System.out.println("No subjects available. Please add subjects first.");
                return;
            }
            
            System.out.println("\nAvailable Subjects:");
            for (int i = 0; i < allSubjects.size(); i++) {
                System.out.println((i + 1) + ". " + allSubjects.get(i));
            }
            
            System.out.print("\nEnter subject numbers to enroll all branch students (comma-separated, e.g., 1,2,3): ");
            String input = scanner.nextLine().trim();
            String[] indices = input.split(",");
            
            List<Subject> subjectsToEnroll = new ArrayList<>();
            for (String indexStr : indices) {
                try {
                    int index = Integer.parseInt(indexStr.trim()) - 1;
                    if (index >= 0 && index < allSubjects.size()) {
                        subjectsToEnroll.add(allSubjects.get(index));
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid inputs
                }
            }
            
            if (subjectsToEnroll.isEmpty()) {
                System.out.println("No valid subjects selected!");
                return;
            }
            
            System.out.println("\nEnrolling " + branchStudents.size() + " students in " + subjectsToEnroll.size() + " subject(s)...");
            int totalEnrollments = 0;
            
            for (Student student : branchStudents) {
                for (Subject subject : subjectsToEnroll) {
                    if (!student.getSubjects().contains(subject)) {
                        system.enrollStudentInSubject(student.getStudentId(), subject.getSubjectCode());
                        totalEnrollments++;
                    }
                }
            }
            
            System.out.println("Bulk enrollment completed! Total enrollments: " + totalEnrollments);
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    // Library Management Methods
    private static void addBook() {
        System.out.println("\n--- Add Book to Library ---");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        System.out.print("Enter Category: ");
        String category = scanner.nextLine().trim();
        
        System.out.print("Enter Number of Copies: ");
        try {
            int copies = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Publication Year: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            
            library.addBook(bookId, title, author, isbn, category, copies, year);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void borrowBook() {
        System.out.println("\n--- Borrow Book ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        // Verify student exists
        if (system.getStudent(studentId) == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        
        System.out.print("Enter number of days allowed (default 14): ");
        String daysInput = scanner.nextLine().trim();
        int days = daysInput.isEmpty() ? 14 : Integer.parseInt(daysInput);
        
        library.borrowBook(studentId, bookId, days);
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.println("1. Return by Transaction ID");
        System.out.println("2. Return by Student ID and Book ID");
        System.out.print("Select option (1 or 2): ");
        
        String choice = scanner.nextLine().trim();
        
        if (choice.equals("1")) {
            System.out.print("Enter Transaction ID: ");
            String transactionId = scanner.nextLine().trim();
            library.returnBook(transactionId);
        } else if (choice.equals("2")) {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine().trim();
            
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine().trim();
            
            library.returnBookByStudentAndBook(studentId, bookId);
        } else {
            System.out.println("Invalid option!");
        }
    }

    private static void viewAllBooks() {
        library.displayAllBooks();
    }

    private static void searchBooks() {
        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Category");
        System.out.print("Select option (1-3): ");
        
        String choice = scanner.nextLine().trim();
        List<Book> results;
        
        if (choice.equals("1")) {
            System.out.print("Enter title to search: ");
            String title = scanner.nextLine().trim();
            results = library.searchBooksByTitle(title);
        } else if (choice.equals("2")) {
            System.out.print("Enter author to search: ");
            String author = scanner.nextLine().trim();
            results = library.searchBooksByAuthor(author);
        } else if (choice.equals("3")) {
            System.out.print("Enter category to search: ");
            String category = scanner.nextLine().trim();
            results = library.searchBooksByCategory(category);
        } else {
            System.out.println("Invalid option!");
            return;
        }
        
        System.out.println("\n=== Search Results ===");
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private static void viewStudentBorrowedBooks() {
        System.out.println("\n--- View Student's Borrowed Books ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        // Verify student exists
        if (system.getStudent(studentId) == null) {
            System.out.println("Student not found!");
            return;
        }
        
        library.displayStudentBorrowedBooks(studentId);
        
        double totalFine = library.calculateTotalFine(studentId);
        if (totalFine > 0) {
            System.out.println("Total Fine: $" + String.format("%.2f", totalFine));
        }
    }

    private static void viewOverdueBooks() {
        library.displayOverdueBooks();
    }

    private static void initializeSampleSubjects() {
        // Computer Science Subjects
        system.addSubject("CS101", "Introduction to Programming", 3);
        system.addSubject("CS102", "Data Structures", 4);
        system.addSubject("CS103", "Database Management", 3);
        system.addSubject("CS104", "Computer Networks", 4);
        system.addSubject("CS105", "Operating Systems", 4);
        system.addSubject("CS106", "Software Engineering", 3);
        system.addSubject("CS107", "Web Development", 3);
        system.addSubject("CS108", "Machine Learning", 4);
        system.addSubject("CS109", "Artificial Intelligence", 4);
        system.addSubject("CS110", "Cybersecurity", 3);
        
        // Electrical Engineering Subjects
        system.addSubject("EE101", "Circuit Analysis", 4);
        system.addSubject("EE102", "Digital Electronics", 3);
        system.addSubject("EE103", "Power Systems", 4);
        system.addSubject("EE104", "Control Systems", 4);
        system.addSubject("EE105", "Electrical Machines", 4);
        system.addSubject("EE106", "Microprocessors", 3);
        
        // Mechanical Engineering Subjects
        system.addSubject("ME101", "Engineering Mechanics", 4);
        system.addSubject("ME102", "Thermodynamics", 4);
        system.addSubject("ME103", "Fluid Mechanics", 4);
        system.addSubject("ME104", "Machine Design", 4);
        system.addSubject("ME105", "Manufacturing Processes", 3);
        
        // Civil Engineering Subjects
        system.addSubject("CE101", "Structural Analysis", 4);
        system.addSubject("CE102", "Concrete Technology", 3);
        system.addSubject("CE103", "Surveying", 3);
        system.addSubject("CE104", "Transportation Engineering", 4);
        system.addSubject("CE105", "Environmental Engineering", 3);
        
        // Electronics Engineering Subjects
        system.addSubject("EC101", "Analog Electronics", 4);
        system.addSubject("EC102", "Communication Systems", 4);
        system.addSubject("EC103", "Signal Processing", 4);
        system.addSubject("EC104", "VLSI Design", 3);
        
        // Common/General Subjects
        system.addSubject("MATH101", "Calculus", 4);
        system.addSubject("MATH102", "Linear Algebra", 3);
        system.addSubject("MATH103", "Discrete Mathematics", 3);
        system.addSubject("MATH104", "Probability and Statistics", 3);
        system.addSubject("PHY101", "Physics", 3);
        system.addSubject("PHY102", "Applied Physics", 3);
        system.addSubject("CHEM101", "Chemistry", 3);
        system.addSubject("ENG101", "English Communication", 2);
        system.addSubject("ENG102", "Technical Writing", 2);
        system.addSubject("ECON101", "Economics", 3);
        system.addSubject("MGMT101", "Management Principles", 3);
        system.addSubject("PROJ101", "Project Management", 3);
    }

    private static void initializeSampleBooks() {
        // Computer Science Books
        library.addBook("CS001", "Introduction to Algorithms", "Thomas H. Cormen", "978-0262033848", "Computer Science", 5, 2009);
        library.addBook("CS002", "Clean Code", "Robert C. Martin", "978-0132350884", "Computer Science", 3, 2008);
        library.addBook("CS003", "Design Patterns", "Gang of Four", "978-0201633610", "Computer Science", 4, 1994);
        library.addBook("CS004", "The Pragmatic Programmer", "Andrew Hunt", "978-0201616224", "Computer Science", 3, 1999);
        library.addBook("CS005", "Database Systems", "Ramez Elmasri", "978-0133970777", "Computer Science", 5, 2015);
        
        // Electrical Engineering Books
        library.addBook("EE001", "Fundamentals of Electric Circuits", "Charles Alexander", "978-0073529554", "Electrical Engineering", 4, 2012);
        library.addBook("EE002", "Digital Design", "M. Morris Mano", "978-0132774208", "Electrical Engineering", 3, 2012);
        library.addBook("EE003", "Power System Analysis", "John Grainger", "978-0070612938", "Electrical Engineering", 2, 1994);
        
        // Mechanical Engineering Books
        library.addBook("ME001", "Engineering Mechanics: Statics", "Russell Hibbeler", "978-0133918922", "Mechanical Engineering", 4, 2015);
        library.addBook("ME002", "Thermodynamics: An Engineering Approach", "Yunus Cengel", "978-0073398174", "Mechanical Engineering", 3, 2014);
        library.addBook("ME003", "Machine Design", "Robert Norton", "978-0133356717", "Mechanical Engineering", 3, 2013);
        
        // Mathematics Books
        library.addBook("MATH001", "Calculus: Early Transcendentals", "James Stewart", "978-1285741550", "Mathematics", 6, 2015);
        library.addBook("MATH002", "Linear Algebra", "David Lay", "978-0321982384", "Mathematics", 4, 2015);
        library.addBook("MATH003", "Discrete Mathematics", "Kenneth Rosen", "978-0073383095", "Mathematics", 3, 2011);
        
        // Physics Books
        library.addBook("PHY001", "University Physics", "Hugh Young", "978-0321973610", "Physics", 5, 2015);
        library.addBook("PHY002", "Introduction to Electrodynamics", "David Griffiths", "978-0321856562", "Physics", 3, 2012);
        
        // General Books
        library.addBook("GEN001", "The Art of Computer Programming", "Donald Knuth", "978-0201896831", "Computer Science", 2, 1997);
        library.addBook("GEN002", "Structure and Interpretation of Computer Programs", "Harold Abelson", "978-0262510875", "Computer Science", 3, 1996);
        library.addBook("GEN003", "Operating System Concepts", "Abraham Silberschatz", "978-1118063330", "Computer Science", 4, 2012);
    }
}

