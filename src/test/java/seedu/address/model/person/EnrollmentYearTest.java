package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EnrollmentYearTest {

    static final String VALID_YEAR = "2025";
    static final String OTHER_VALID_YEAR = "2024";
    static final String ZERO_YEAR = "0";
    static final String INVALID_YEAR = "20-20";
    static final String LARGE_YEAR = "98765432123456789";
    static final String EMPTY_YEAR = "";

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(INVALID_YEAR));
    }

    @Test
    public void constructor_nonPositiveYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(ZERO_YEAR));
    }

    @Test
    public void constructor_largeYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(LARGE_YEAR));
    }

    @Test
    public void equals() {
        EnrollmentYear enrollmentYear = new EnrollmentYear(VALID_YEAR);

        // same values -> returns true
        assertTrue(enrollmentYear.equals(new EnrollmentYear(VALID_YEAR)));

        // same object -> returns true
        assertTrue(enrollmentYear.equals(enrollmentYear));

        // null -> returns false
        assertFalse(enrollmentYear.equals(null));

        // different types -> returns false
        assertFalse(enrollmentYear.equals(5.0f));

        // different values -> returns false
        assertFalse(enrollmentYear.equals(new EnrollmentYear(OTHER_VALID_YEAR)));
    }

    @Test
    public void toStringMethod() {
        final EnrollmentYear enrollmentYear = new EnrollmentYear(VALID_YEAR);
        final String expected = VALID_YEAR;
        assertEquals(expected, enrollmentYear.toString());
        final EnrollmentYear emptyEnrollmentYear = new EnrollmentYear();
        final String expectedNone = "";
        assertEquals(expectedNone, emptyEnrollmentYear.toString());
    }

    @Test
    public void hashCodeMethod() {
        final EnrollmentYear enrollmentYear = new EnrollmentYear(VALID_YEAR);

        // equal objects returns same hash code
        assertEquals(enrollmentYear.hashCode(), new EnrollmentYear(VALID_YEAR).hashCode());

        // different objects return different hash codes
        assertNotEquals(enrollmentYear.hashCode(), new EnrollmentYear(OTHER_VALID_YEAR).hashCode());
    }
}
