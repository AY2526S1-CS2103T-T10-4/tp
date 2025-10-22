package seedu.address.model.role;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code roles} contains any of the strings given as substrings.
 */
public class RolesContainSubstringsPredicate implements Predicate<Person> {
    private final List<String> strings;

    public RolesContainSubstringsPredicate(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public boolean test(Person person) {
        return person.getRoles().stream().anyMatch(this::roleContainsSubstrings);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RolesContainSubstringsPredicate)) {
            return false;
        }

        RolesContainSubstringsPredicate otherRoleContainsSubstringsPredicate = (RolesContainSubstringsPredicate) other;
        return strings.equals(otherRoleContainsSubstringsPredicate.strings);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("strings", strings).toString();
    }

    /**
     * Returns true if the tag contains any of the keywords as a substring.
     */
    private boolean roleContainsSubstrings(Role role) {
        return strings.stream().anyMatch(str -> str.isEmpty()
                || StringUtil.containsMultiWordSubstringIgnoreCase(role.roleName, str));
    }
}
