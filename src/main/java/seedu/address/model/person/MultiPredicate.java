package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person} satisfies a list of predicates.
 */
public class MultiPredicate implements Predicate<Person> {
    private final List<Predicate<Person>> predicates;

    public MultiPredicate(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        return predicates.stream().allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MultiPredicate)) {
            return false;
        }

        MultiPredicate otherMultiPredicate = (MultiPredicate) other;
        return predicates.equals(otherMultiPredicate.predicates);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicates", predicates).toString();
    }
}
