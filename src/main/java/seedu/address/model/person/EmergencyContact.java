package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person's emergency contact in the address book.
 * Guarantees: immutable; fields name, phone are valid as declared in {@link Name#isValidName},
 * {@link Phone#isValidPhone} respectively.
 */
public class EmergencyContact {
    public static final String MESSAGE_CONSTRAINTS = "The emergency contact fields (`ecn, ecp, ece`) must be either "
            + "all provided or not at all.";

    public final Name name;
    public final Phone phone;
    public final Email email;

    /**
     * Constructs a {@code EmergencyContact}.
     *
     * @param name  A valid name.
     * @param phone A valid phone number.
     * @param email A valid email address.
     */
    public EmergencyContact(String name, String phone, String email) {
        this.name = new Name(name);
        this.phone = new Phone(phone);
        this.email = new Email(email);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("phone", phone).add("email", email).toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmergencyContact)) {
            return false;
        }

        EmergencyContact otherContact = (EmergencyContact) other;
        return name.equals(otherContact.name) && phone.equals(otherContact.phone) && email.equals(otherContact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email);
    }

}
