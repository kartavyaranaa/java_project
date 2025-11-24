import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for managing library operations
 */
public class LibraryManagement {
    private Map<String, Book> books;
    private Map<String, LibraryTransaction> transactions;
    private int transactionCounter;
    private static final int DEFAULT_BORROW_DAYS = 14; // 14 days default
    private static final double FINE_PER_DAY = 5.0; // $5 per day fine

    public LibraryManagement() {
        this.books = new HashMap<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 1;
    }

    // Book Management
    public void addBook(String bookId, String title, String author, String isbn, String category, int totalCopies, int publicationYear) {
        if (books.containsKey(bookId)) {
            System.out.println("Book with ID " + bookId + " already exists!");
            return;
        }
        Book book = new Book(bookId, title, author, isbn, category, totalCopies, publicationYear);
        books.put(bookId, book);
        System.out.println("Book added successfully!");
    }

    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByCategory(String category) {
        return books.values().stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void displayAllBooks() {
        System.out.println("\n=== All Books ===");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    // Borrowing Management
    public boolean borrowBook(String studentId, String bookId) {
        return borrowBook(studentId, bookId, DEFAULT_BORROW_DAYS);
    }

    public boolean borrowBook(String studentId, String bookId, int daysAllowed) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is not available. All copies are borrowed.");
            return false;
        }

        // Check if student already has this book
        boolean alreadyBorrowed = transactions.values().stream()
                .anyMatch(t -> t.getStudentId().equals(studentId) && 
                              t.getBookId().equals(bookId) && 
                              !t.isReturned());

        if (alreadyBorrowed) {
            System.out.println("Student has already borrowed this book!");
            return false;
        }

        String transactionId = "TXN" + String.format("%06d", transactionCounter++);
        LibraryTransaction transaction = new LibraryTransaction(transactionId, studentId, bookId, LocalDate.now(), daysAllowed);
        transactions.put(transactionId, transaction);
        book.borrowBook();

        System.out.println("Book borrowed successfully!");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Due Date: " + transaction.getDueDate());
        return true;
    }

    public boolean returnBook(String transactionId) {
        LibraryTransaction transaction = transactions.get(transactionId);
        if (transaction == null) {
            System.out.println("Transaction not found!");
            return false;
        }

        if (transaction.isReturned()) {
            System.out.println("Book already returned!");
            return false;
        }

        Book book = books.get(transaction.getBookId());
        if (book != null) {
            book.returnBook();
        }

        transaction.setReturned(true);
        transaction.setReturnDate(LocalDate.now());
        transaction.calculateFine(FINE_PER_DAY);

        System.out.println("Book returned successfully!");
        if (transaction.getFine() > 0) {
            System.out.println("Fine amount: $" + String.format("%.2f", transaction.getFine()));
        }
        return true;
    }

    public boolean returnBookByStudentAndBook(String studentId, String bookId) {
        LibraryTransaction transaction = transactions.values().stream()
                .filter(t -> t.getStudentId().equals(studentId) && 
                            t.getBookId().equals(bookId) && 
                            !t.isReturned())
                .findFirst()
                .orElse(null);

        if (transaction == null) {
            System.out.println("No active transaction found for this student and book!");
            return false;
        }

        return returnBook(transaction.getTransactionId());
    }

    // Transaction Management
    public List<LibraryTransaction> getStudentTransactions(String studentId) {
        return transactions.values().stream()
                .filter(t -> t.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public List<LibraryTransaction> getActiveTransactions(String studentId) {
        return transactions.values().stream()
                .filter(t -> t.getStudentId().equals(studentId) && !t.isReturned())
                .collect(Collectors.toList());
    }

    public List<LibraryTransaction> getOverdueTransactions() {
        return transactions.values().stream()
                .filter(t -> !t.isReturned() && t.isOverdue())
                .collect(Collectors.toList());
    }

    public void displayStudentBorrowedBooks(String studentId) {
        List<LibraryTransaction> activeTransactions = getActiveTransactions(studentId);
        System.out.println("\n=== Books Borrowed by Student " + studentId + " ===");
        if (activeTransactions.isEmpty()) {
            System.out.println("No active borrowings.");
        } else {
            for (LibraryTransaction transaction : activeTransactions) {
                Book book = books.get(transaction.getBookId());
                String bookTitle = book != null ? book.getTitle() : "Unknown";
                System.out.println("Transaction ID: " + transaction.getTransactionId());
                System.out.println("  Book: " + bookTitle + " (ID: " + transaction.getBookId() + ")");
                System.out.println("  Borrowed: " + transaction.getBorrowDate());
                System.out.println("  Due Date: " + transaction.getDueDate());
                transaction.calculateFine(FINE_PER_DAY);
                if (transaction.isOverdue()) {
                    System.out.println("  Status: OVERDUE - Fine: $" + String.format("%.2f", transaction.getFine()));
                } else {
                    System.out.println("  Status: Active");
                }
                System.out.println();
            }
        }
    }

    public void displayOverdueBooks() {
        List<LibraryTransaction> overdue = getOverdueTransactions();
        System.out.println("\n=== Overdue Books ===");
        if (overdue.isEmpty()) {
            System.out.println("No overdue books.");
        } else {
            for (LibraryTransaction transaction : overdue) {
                Book book = books.get(transaction.getBookId());
                String bookTitle = book != null ? book.getTitle() : "Unknown";
                transaction.calculateFine(FINE_PER_DAY);
                System.out.println("Student ID: " + transaction.getStudentId());
                System.out.println("  Book: " + bookTitle + " (ID: " + transaction.getBookId() + ")");
                System.out.println("  Due Date: " + transaction.getDueDate());
                System.out.println("  Days Overdue: " + 
                    java.time.temporal.ChronoUnit.DAYS.between(transaction.getDueDate(), LocalDate.now()));
                System.out.println("  Fine: $" + String.format("%.2f", transaction.getFine()));
                System.out.println();
            }
        }
    }

    public double calculateTotalFine(String studentId) {
        return getActiveTransactions(studentId).stream()
                .mapToDouble(t -> {
                    t.calculateFine(FINE_PER_DAY);
                    return t.getFine();
                })
                .sum();
    }
}

