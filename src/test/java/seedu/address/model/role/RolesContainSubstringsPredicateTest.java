package seedu.address.model.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RolesContainSubstringsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("president");
        List<String> secondPredicateKeywordList = Arrays.asList("section leader", "secretary");

        RolesContainSubstringsPredicate firstPredicate =
                new RolesContainSubstringsPredicate(firstPredicateKeywordList);
        RolesContainSubstringsPredicate secondPredicate =
                new RolesContainSubstringsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RolesContainSubstringsPredicate firstPredicateCopy =
                new RolesContainSubstringsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different roles -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_rolesContainKeywords_returnsTrue() {
        // One string
        RolesContainSubstringsPredicate predicate =
                new RolesContainSubstringsPredicate(Collections.singletonList("leader"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("president", "section leader").build()));

        // Multiple strings
        predicate = new RolesContainSubstringsPredicate(Arrays.asList("leader", "president"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("leader", "president").build()));

        // Multiple strings (person roles matches subset of strings)
        predicate = new RolesContainSubstringsPredicate(Arrays.asList("leader", "president", "treasurer"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("Section Leader", "Vice President").build()));

        // Only one matching string
        predicate = new RolesContainSubstringsPredicate(Arrays.asList("lead", "secretary"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("Section Leader", "President").build()));

        // Mixed-case strings
        predicate = new RolesContainSubstringsPredicate(Arrays.asList("SeCTiON LeAd", "boBBB"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("President", "Section Leader").build()));

        // Substring match only (not full word).
        predicate = new RolesContainSubstringsPredicate(Collections.singletonList("tion lead"));
        assertTrue(predicate.test(new PersonBuilder().withRoles("Section Leader", "President").build()));

        // Empty substring matches everything
        predicate = new RolesContainSubstringsPredicate(Collections.singletonList(""));
        assertTrue(predicate.test(new PersonBuilder().withRoles("Section Leader", "President").build()));
    }

    @Test
    public void test_rolesDoNotContainKeywords_returnsFalse() {
        // Non-matching string
        RolesContainSubstringsPredicate predicate =
                new RolesContainSubstringsPredicate(Collections.singletonList("president"));
        assertFalse(predicate.test(new PersonBuilder().withRoles("Section Leader").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("str1", "str2");
        RolesContainSubstringsPredicate predicate = new RolesContainSubstringsPredicate(keywords);

        String expected = RolesContainSubstringsPredicate.class.getCanonicalName() + "{strings=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
