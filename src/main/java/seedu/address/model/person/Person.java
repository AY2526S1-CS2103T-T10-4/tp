package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null (except optional fields below), field values are validated, immutable.
 * Optional fields: [emergencyContact, enrollmentYear].
 */
public class Person {

    public static final String MESSAGE_CONSTRAINTS =
            "Emergency contact's phone number should not be the same as contact's phone number";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final EmergencyContact emergencyContact;
    private final EnrollmentYear enrollmentYear;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  EmergencyContact emergencyContact, EnrollmentYear enrollmentYear) {
        requireAllNonNull(name, phone, email, address, tags);
        checkArgument(isValidPerson(phone, emergencyContact), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.emergencyContact = emergencyContact;
        this.enrollmentYear = enrollmentYear;
    }

    /**
     * Returns true if the given parameters form a valid Person.
     */
    public static boolean isValidPerson(Phone phone, EmergencyContact emergencyContact) {
        if (emergencyContact != null) {
            return !emergencyContact.phone.equals(phone);
        }
        return true;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Optional<EmergencyContact> getEmergencyContact() {
        return Optional.ofNullable(emergencyContact);
    }

    public Optional<EnrollmentYear> getEnrollmentYear() {
        return Optional.ofNullable(enrollmentYear);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
