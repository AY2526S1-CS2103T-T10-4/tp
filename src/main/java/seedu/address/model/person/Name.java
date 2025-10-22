package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS = "Names should not be blank, bracket sequences in names should be"
            + " matched, and names should only contain alphanumeric characters, spaces, and the following characters: "
            + ".,-'()";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}.,\\-'()][\\p{Alnum}.,\\-'() ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        final boolean hasValidCharacters = test.matches(VALIDATION_REGEX);
        final boolean hasBalancedBrackets = checkBalancedBrackets(test);
        return hasValidCharacters && hasBalancedBrackets;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
     * Returns true if the given string has a balanced bracket matching.
     */
    private static boolean checkBalancedBrackets(String s) {
        int openBracketCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBracketCount++;
            } else if (c == ')') {
                openBracketCount--;
            }
            if (openBracketCount < 0) {
                return false;
            }
        }
        return openBracketCount == 0;
    }
}
