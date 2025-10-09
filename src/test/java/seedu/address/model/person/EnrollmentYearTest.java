package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EnrollmentYearTest {

    static final String validYear = "2025";
    static final String otherValidYear = "2024";
    static final String zeroYear = "0";
    static final String invalidYear = "20-20";
    static final String emptyYear = "";

    @Test
    public void constructor_nullYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(null));
    }

    @Test
    public void constructor_invalidYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(invalidYear));
    }

    @Test
    public void constructor_nonPositiveYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(zeroYear));
    }

    @Test
    public void constructor_emptyYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentYear(emptyYear));
    }

    @Test
    public void equals() {
        EnrollmentYear enrollmentYear = new EnrollmentYear(validYear);

        // same values -> returns true
        assertTrue(enrollmentYear.equals(new EnrollmentYear(validYear)));

        // same object -> returns true
        assertTrue(enrollmentYear.equals(enrollmentYear));

        // null -> returns false
        assertFalse(enrollmentYear.equals(null));

        // different types -> returns false
        assertFalse(enrollmentYear.equals(5.0f));

        // different values -> returns false
        assertFalse(enrollmentYear.equals(new EnrollmentYear(otherValidYear)));
    }

    @Test
    public void toStringMethod() {
        final EnrollmentYear enrollmentYear = new EnrollmentYear(validYear);
        final String expected = validYear;
        assertEquals(expected, enrollmentYear.toString());
    }

    @Test
    public void hashCodeMethod() {
        final EnrollmentYear enrollmentYear = new EnrollmentYear(validYear);

        // equal objects returns same hash code
        assertEquals(enrollmentYear.hashCode(), new EnrollmentYear(validYear).hashCode());

        // different objects return different hash codes
        assertNotEquals(enrollmentYear.hashCode(), new EnrollmentYear(otherValidYear).hashCode());
    }
}
