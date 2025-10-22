package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class EnrollmentYearPredicateTest {

    @Test
    public void equals() throws ParseException {

        EnrollmentYearPredicate firstPredicate = new EnrollmentYearPredicate("<2025");
        EnrollmentYearPredicate secondPredicate = new EnrollmentYearPredicate("=2025");
        EnrollmentYearPredicate thirdPredicate = new EnrollmentYearPredicate("<2024");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EnrollmentYearPredicate firstPredicateCopy = new EnrollmentYearPredicate("<2025");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_invalidConstraints_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> new EnrollmentYearPredicate("2025"));
        assertThrows(ParseException.class, () -> new EnrollmentYearPredicate("=>2025"));
        assertThrows(ParseException.class, () -> new EnrollmentYearPredicate("<3457637463746374634"));
    }

    @Test
    public void test_yearSatisfiesConstraint_returnsTrue() throws ParseException {
        EnrollmentYearPredicate greaterPredicate = new EnrollmentYearPredicate(">2025");
        EnrollmentYearPredicate greaterEqPredicate = new EnrollmentYearPredicate(">=2025");
        EnrollmentYearPredicate lesserPredicate = new EnrollmentYearPredicate("<2025");
        EnrollmentYearPredicate lesserEqPredicate = new EnrollmentYearPredicate("<=2025");
        EnrollmentYearPredicate equalPredicate = new EnrollmentYearPredicate("=2025");
        EnrollmentYearPredicate emptyPredicate = new EnrollmentYearPredicate("");

        assertTrue(greaterPredicate.test(new PersonBuilder().withEnrollmentYear("2026").build()));

        assertTrue(greaterEqPredicate.test(new PersonBuilder().withEnrollmentYear("2026").build()));
        assertTrue(greaterEqPredicate.test(new PersonBuilder().withEnrollmentYear("2025").build()));

        assertTrue(lesserPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));

        assertTrue(lesserEqPredicate.test(new PersonBuilder().withEnrollmentYear("2025").build()));
        assertTrue(lesserEqPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));

        assertTrue(equalPredicate.test(new PersonBuilder().withEnrollmentYear("2025").build()));

        assertTrue(emptyPredicate.test(new PersonBuilder().withEnrollmentYear().build()));
    }

    @Test
    public void test_yearDoesNotSatisfiesConstraint_returnsFalse() throws ParseException {
        EnrollmentYearPredicate greaterPredicate = new EnrollmentYearPredicate(">2025");
        EnrollmentYearPredicate greaterEqPredicate = new EnrollmentYearPredicate(">=2025");
        EnrollmentYearPredicate lesserPredicate = new EnrollmentYearPredicate("<2025");
        EnrollmentYearPredicate lesserEqPredicate = new EnrollmentYearPredicate("<=2025");
        EnrollmentYearPredicate equalPredicate = new EnrollmentYearPredicate("=2025");
        EnrollmentYearPredicate emptyPredicate = new EnrollmentYearPredicate("");

        assertFalse(greaterPredicate.test(new PersonBuilder().withEnrollmentYear("2025").build()));
        assertFalse(greaterPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));

        assertFalse(greaterEqPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));

        assertFalse(lesserPredicate.test(new PersonBuilder().withEnrollmentYear("2026").build()));
        assertFalse(lesserPredicate.test(new PersonBuilder().withEnrollmentYear("2025").build()));

        assertFalse(lesserEqPredicate.test(new PersonBuilder().withEnrollmentYear("2026").build()));

        assertFalse(equalPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));
        assertFalse(equalPredicate.test(new PersonBuilder().withEnrollmentYear().build()));

        assertFalse(emptyPredicate.test(new PersonBuilder().withEnrollmentYear("2024").build()));
    }

    @Test
    public void toStringMethod() throws ParseException {
        EnrollmentYearPredicate predicate = new EnrollmentYearPredicate("<2025");

        String expected = EnrollmentYearPredicate.class.getCanonicalName()
                + "{constraint=LESS_THAN, value=2025}";
        assertEquals(expected, predicate.toString());
    }
}
