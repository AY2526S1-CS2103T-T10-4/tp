package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.PersonBuilder;

public class MultiPredicateTest {

    @Test
    public void equals() throws ParseException {

        MultiPredicate firstPredicate = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025"),
            new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
        MultiPredicate secondPredicate = new MultiPredicate(Arrays.asList(
            new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
            new EnrollmentYearPredicate("<2025")));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MultiPredicate firstPredicateCopy = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025"),
            new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_satisfiesAll_returnsTrue() throws ParseException {

        MultiPredicate multiplePredicate = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025"),
            new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));

        MultiPredicate singlePredicate = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025")));

        Person person = new PersonBuilder().withName("Alice").withEnrollmentYear("2024").build();

        assertTrue(multiplePredicate.test(person));

        assertTrue(singlePredicate.test(person));
    }

    @Test
    public void test_doesNotSatisfiesAll_returnsFalse() throws ParseException {

        MultiPredicate multiplePredicate = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025"),
            new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))));

        MultiPredicate singlePredicate = new MultiPredicate(Arrays.asList(
            new EnrollmentYearPredicate("<2025")));
        Person person = new PersonBuilder().withName("Alice").withEnrollmentYear("2026").build();

        assertFalse(multiplePredicate.test(person));

        assertFalse(singlePredicate.test(person));
    }

    @Test
    public void toStringMethod() throws ParseException {
        Predicate<Person> predicate1 = new EnrollmentYearPredicate("<2025");
        Predicate<Person> predicate2 = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        MultiPredicate predicate = new MultiPredicate(Arrays.asList(predicate1, predicate2));

        String expected = MultiPredicate.class.getCanonicalName()
                + "{predicates=[" + predicate1 + ", " + predicate2 + "]}";
        assertEquals(expected, predicate.toString());
    }
}
