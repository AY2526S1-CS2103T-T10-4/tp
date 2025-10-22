package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.SortUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    private final Person unpinnedA = new PersonBuilder().withName("A").withPin(false).build();
    private final Person unpinnedB = new PersonBuilder().withName("B").withPin(false).build();
    private final Person pinnedC = new PersonBuilder().withName("C").withPin(true).build();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .withPin(VALID_PIN_BOB)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    //// sorting tests

    @Test
    public void sort_noSort_sortedInDefaultOrder() {
        addressBook.addPerson(unpinnedA);
        addressBook.addPerson(unpinnedB);
        addressBook.addPerson(pinnedC);

        assertEquals(pinnedC, addressBook.getPersonList().get(0));
        assertEquals(unpinnedA, addressBook.getPersonList().get(1));
        assertEquals(unpinnedB, addressBook.getPersonList().get(2));
    }

    @Test
    public void sort_validSort_sortedByAscendingNameOrder() {
        addressBook.addPerson(pinnedC);
        addressBook.addPerson(unpinnedA);
        addressBook.addPerson(unpinnedB);
        addressBook.sort(SortUtil.SORT_NAME_ALPHABETICAL_ASC);

        assertEquals(unpinnedA, addressBook.getPersonList().get(0));
        assertEquals(unpinnedB, addressBook.getPersonList().get(1));
        assertEquals(pinnedC, addressBook.getPersonList().get(2));
    }

    @Test
    public void sort_addPerson_sortedInCorrectOrder() {
        addressBook.addPerson(unpinnedA);
        addressBook.addPerson(unpinnedB);

        assertEquals(unpinnedB, addressBook.getPersonList().get(1));

        addressBook.addPerson(pinnedC);
        AddressBook expected = new AddressBookBuilder()
                .withPerson(pinnedC)
                .withPerson(unpinnedA)
                .withPerson(unpinnedB)
                .build();
        assertEquals(expected, addressBook);
    }

    @Test
    public void sort_setPerson_sortedInCorrectOrder() {
        addressBook.addPerson(pinnedC);
        addressBook.addPerson(unpinnedA);
        addressBook.addPerson(unpinnedB);

        Person editedPersonB = new PersonBuilder(unpinnedB).withPin(true).build();
        addressBook.setPerson(unpinnedB, editedPersonB);

        AddressBook expected = new AddressBook();
        expected.addPerson(pinnedC);
        expected.addPerson(editedPersonB);
        expected.addPerson(unpinnedA);

        assertEquals(expected, addressBook);
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(addressBook.equals(addressBook));

        // different types -> returns false
        assertFalse(addressBook.equals(1));

        // null -> returns false
        assertFalse(addressBook.equals(null));
    }

    @Test
    public void hashcode() {
        addressBook.addPerson(pinnedC);
        AddressBook otherAddressBook = new AddressBookBuilder().withPerson(pinnedC).build();
        assertEquals(addressBook, otherAddressBook);
        assertEquals(addressBook.hashCode(), otherAddressBook.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
