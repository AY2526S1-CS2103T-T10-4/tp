package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.EnrollmentYear;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pin;
import seedu.address.model.role.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final boolean DEFAULT_PIN = false;
    public static final String DEFAULT_EMERGENCY_NAME = "Alice Bee";
    public static final String DEFAULT_EMERGENCY_PHONE = "88887777";
    public static final String DEFAULT_EMERGENCY_EMAIL = "alice@gmail.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Pin pin;
    private Set<Role> roles;
    private Set<Tag> tags;
    private EmergencyContact emergencyContact;
    private EnrollmentYear enrollmentYear;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        pin = new Pin(DEFAULT_PIN);
        roles = new HashSet<>();
        tags = new HashSet<>();
        emergencyContact = new EmergencyContact(DEFAULT_EMERGENCY_NAME, DEFAULT_EMERGENCY_PHONE,
                DEFAULT_EMERGENCY_EMAIL);
        enrollmentYear = new EnrollmentYear();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        roles = new HashSet<>(personToCopy.getRoles());
        tags = new HashSet<>(personToCopy.getTags());
        pin = personToCopy.getPin();
        emergencyContact = personToCopy.getEmergencyContact().orElse(null);
        enrollmentYear = personToCopy.getEnrollmentYear();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withRoles(String... roles) {
        this.roles = SampleDataUtil.getRoleSet(roles);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Pin} of the {@code Person} that we are building.
     */
    public PersonBuilder withPin(boolean pin) {
        this.pin = new Pin(pin);
        return this;
    }

    /**
     * Sets the {@code EmergencyContact} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergencyContact(String name, String phone, String email) {
        this.emergencyContact = new EmergencyContact(name, phone, email);
        return this;
    }

    /**
     * Sets the {@code EnrollmentYear} of the {@code Person} that we are building.
     */
    public PersonBuilder withEnrollmentYear(String year) {
        this.enrollmentYear = new EnrollmentYear(year);
        return this;
    }

    /**
     * Sets the {@code EnrollmentYear} of the {@code Person} that we are building as empty.
     */
    public PersonBuilder withEnrollmentYear() {
        this.enrollmentYear = new EnrollmentYear();
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, pin, roles, tags, emergencyContact, enrollmentYear);
    }

}
