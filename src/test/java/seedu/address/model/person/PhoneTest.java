package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only

        // valid phone numbers
        assertTrue(Phone.isValidPhone("91")); // less than 3 numbers
        assertTrue(Phone.isValidPhone("phone")); // non-numeric
        assertTrue(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertTrue(Phone.isValidPhone("124293842033123")); // starts with wrong digit
        assertTrue(Phone.isValidPhone("911"));
        assertTrue(Phone.isValidPhone("12345678"));
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("31313131"));
        assertTrue(Phone.isValidPhone("61616161"));
        assertTrue(Phone.isValidPhone("81818112"));
        assertTrue(Phone.isValidPhone("8181-8112"));
        assertTrue(Phone.isValidPhone(" 8181 8162 "));
        assertTrue(Phone.isValidPhone(" 81--81 81 62 "));
        assertTrue(Phone.isValidPhone(" 8181   8162 "));
    }

    @Test
    public void equals() {
        Phone phone = new Phone("99988899");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("99988899")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("99588899")));
    }
}
