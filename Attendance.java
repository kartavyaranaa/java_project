import java.time.LocalDate;

/**
 * Class representing an attendance record
 */
public class Attendance {
    private Subject subject;
    private LocalDate date;
    private boolean present;

    public Attendance(Subject subject, LocalDate date, boolean present) {
        this.subject = subject;
        this.date = date;
        this.present = present;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return subject.getSubjectName() + " - " + date + ": " + (present ? "Present" : "Absent");
    }
}

