package seedu.address.testutil;

import java.util.Comparator;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    /**
     * Sets the sorting method of the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSort(Comparator<Person> personComparator) {
        addressBook.sort(personComparator);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
