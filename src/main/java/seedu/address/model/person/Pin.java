package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents whether a Person is pinned in the address book.
 * Guarantees: immutable;
 */
public class Pin {
    public static final String MESSAGE_CONSTRAINTS = "Argument should be either TRUE or FALSE";

    public final boolean value;

    /**
     * Constructs a {@code Pin}.
     */
    public Pin(boolean isPinned) {
        value = isPinned;
    }

    @Override
    public String toString() {
        return value ? "True" : "False";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Pin)) {
            return false;
        }

        Pin otherPin = (Pin) other;
        return value == otherPin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
