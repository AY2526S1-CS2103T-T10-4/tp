package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's enrollment year in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class EnrollmentYear {

    public static final String MESSAGE_CONSTRAINTS =
            "Enrollment year should be a positive integer.";

    /*
     * The string must be a positive integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final int year;

    /**
     * Constructs a {@code EnrollmentYear}.
     *
     * @param yearStr String representing a valid year.
     */
    public EnrollmentYear(String yearStr) {
        checkArgument(isValidYear(yearStr), MESSAGE_CONSTRAINTS);
        this.year = Integer.parseInt(yearStr);
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test != null
                && !test.isEmpty()
                && test.matches(VALIDATION_REGEX)
                && Integer.parseInt(test) > 0;
    }


    @Override
    public String toString() {
        return Integer.toString(year);
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
        return year == otherYear.year;
    }

    @Override
    public int hashCode() {
        return year;
    }

}
