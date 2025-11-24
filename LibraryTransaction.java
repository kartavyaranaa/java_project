import java.time.LocalDate;

/**
 * Class representing a library transaction (borrow/return)
 */
public class LibraryTransaction {
    private String transactionId;
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private double fine;

    public LibraryTransaction(String transactionId, String studentId, String bookId, LocalDate borrowDate, int daysAllowed) {
        this.transactionId = transactionId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(daysAllowed);
        this.isReturned = false;
        this.fine = 0.0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public void calculateFine(double finePerDay) {
        if (!isReturned && LocalDate.now().isAfter(dueDate)) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            fine = daysOverdue * finePerDay;
        }
    }

    public boolean isOverdue() {
        return !isReturned && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        String status = isReturned ? "Returned on " + returnDate : "Due on " + dueDate;
        String fineStr = fine > 0 ? ", Fine: $" + String.format("%.2f", fine) : "";
        return "Transaction ID: " + transactionId + ", Book ID: " + bookId + 
               ", Student ID: " + studentId + ", " + status + fineStr;
    }
}

