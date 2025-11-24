/**
 * Enum representing different branches/departments in the university
 */
public enum Branch {
    COMPUTER_SCIENCE("Computer Science"),
    ELECTRICAL_ENGINEERING("Electrical Engineering"),
    MECHANICAL_ENGINEERING("Mechanical Engineering"),
    CIVIL_ENGINEERING("Civil Engineering"),
    ELECTRONICS_ENGINEERING("Electronics Engineering"),
    INFORMATION_TECHNOLOGY("Information Technology"),
    BUSINESS_ADMINISTRATION("Business Administration"),
    MATHEMATICS("Mathematics"),
    PHYSICS("Physics"),
    CHEMISTRY("Chemistry");

    private final String displayName;

    Branch(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

