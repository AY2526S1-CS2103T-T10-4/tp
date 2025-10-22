package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's enrollment year in the address book.
 * Note that this value is optional which is stored as a isPresent field
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class EnrollmentYear {

    public static final String MESSAGE_CONSTRAINTS =
            "Enrollment year should be a positive integer or an empty string.";

    /*
     * The string must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final int year;
    public final boolean isPresent;

    /**
     * Constructs a {@code EnrollmentYear}.
     *
     * @param yearStr String representing a valid year or an empty string indicating empty field.
     */
    public EnrollmentYear(String yearStr) {
        if (yearStr.isEmpty()) {
            this.isPresent = false;
            this.year = 0;
            return;
        }
        checkArgument(isValidYear(yearStr), MESSAGE_CONSTRAINTS);
        this.year = Integer.parseInt(yearStr);
        isPresent = true;
    }

    /**
     * Constructs a empty {@code EnrollmentYear}.
     */
    public EnrollmentYear() {
        isPresent = false;
        year = 0;
    }

    /**
     * Copy constructor of {@code EnrollmentYear}.
     */
    public EnrollmentYear(EnrollmentYear other) {
        isPresent = other.isPresent;
        year = other.year;
    }

    /**
     * @return year as an integer, requires that isPresent be true.
     */
    public int getValue() {
        assert isPresent();
        return year;
    }

    /**
     * @return If enrollmentYear is present
     */
    public boolean isPresent() {
        return isPresent;
    }


    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        try {
            return test != null
                    && !test.isEmpty()
                    && test.matches(VALIDATION_REGEX)
                    && Integer.parseInt(test) > 0;
        } catch (NumberFormatException e) {
            // This happens if the number is too large to fit in an int
            return false;
        }
    }


    @Override
    public String toString() {
        if (isPresent) {
            return Integer.toString(year);
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrollmentYear)) {
            return false;
        }

        EnrollmentYear otherYear = (EnrollmentYear) other;
        return year == otherYear.year && isPresent == otherYear.isPresent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, isPresent);
    }

}
