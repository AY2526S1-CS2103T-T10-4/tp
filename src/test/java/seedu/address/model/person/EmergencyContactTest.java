package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmergencyContactTest {

    static final String VALID_PHONE = "98765432";
    static final String VALID_NAME = "Alice";
    static final String VALID_EMAIL = "alice@example.com";
    static final String OTHER_VALID_PHONE = "91234567";
    static final String OTHER_VALID_NAME = "Bob";
    static final String OTHER_VALID_EMAIL = "bob@example.com";

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(null, VALID_PHONE, VALID_EMAIL));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(invalidName, VALID_PHONE, VALID_EMAIL));
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(VALID_NAME, null, VALID_EMAIL));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(VALID_NAME, invalidPhone, VALID_EMAIL));
    }

    @Test
    public void constructor_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmergencyContact(VALID_NAME, VALID_PHONE, null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new EmergencyContact(VALID_NAME, VALID_PHONE, invalidEmail));
    }

    @Test
    public void equals() {
        EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE, VALID_EMAIL);

        // same values -> returns true
        assertTrue(emergencyContact.equals(new EmergencyContact(VALID_NAME, VALID_PHONE, VALID_EMAIL)));

        // same object -> returns true
        assertTrue(emergencyContact.equals(emergencyContact));

        // null -> returns false
        assertFalse(emergencyContact.equals(null));

        // different types -> returns false
        assertFalse(emergencyContact.equals(5.0f));

        // different values -> returns false
        assertFalse(emergencyContact.equals(new EmergencyContact(OTHER_VALID_NAME, VALID_PHONE, VALID_EMAIL)));
        assertFalse(emergencyContact.equals(new EmergencyContact(VALID_NAME, OTHER_VALID_PHONE, VALID_EMAIL)));
        assertFalse(emergencyContact.equals(new EmergencyContact(VALID_NAME, VALID_PHONE, OTHER_VALID_EMAIL)));
        assertFalse(emergencyContact.equals(new EmergencyContact(OTHER_VALID_NAME, OTHER_VALID_PHONE,
                OTHER_VALID_EMAIL)));
    }

    @Test
    public void toStringMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE, VALID_EMAIL);
        final String expected = String.format("%s{name=%s, phone=%s, email=%s}",
                EmergencyContact.class.getCanonicalName(), VALID_NAME, VALID_PHONE, VALID_EMAIL);
        assertEquals(expected, emergencyContact.toString());
    }

    @Test
    public void hashCodeMethod() {
        final EmergencyContact emergencyContact = new EmergencyContact(VALID_NAME, VALID_PHONE, VALID_EMAIL);

        // equal objects returns same hash code
        assertEquals(emergencyContact.hashCode(),
                new EmergencyContact(VALID_NAME, VALID_PHONE, VALID_EMAIL).hashCode());

        // different objects return different hash codes
        assertNotEquals(emergencyContact.hashCode(),
                new EmergencyContact(OTHER_VALID_NAME, VALID_PHONE, VALID_EMAIL).hashCode());
        assertNotEquals(emergencyContact.hashCode(),
                new EmergencyContact(VALID_NAME, OTHER_VALID_PHONE, VALID_EMAIL).hashCode());
        assertNotEquals(emergencyContact.hashCode(),
                new EmergencyContact(VALID_NAME, VALID_PHONE, OTHER_VALID_EMAIL).hashCode());
        assertNotEquals(emergencyContact.hashCode(),
                new EmergencyContact(OTHER_VALID_NAME, OTHER_VALID_PHONE, OTHER_VALID_EMAIL).hashCode());
    }
}
