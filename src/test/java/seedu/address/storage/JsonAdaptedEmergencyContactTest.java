package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEmergencyContact.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedEmergencyContactTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();

    private static final String VALID_EMERGENCY_NAME = BENSON.getEmergencyContact().get().name.toString();
    private static final String VALID_EMERGENCY_PHONE = BENSON.getEmergencyContact().get().phone.toString();
    private static final String VALID_EMERGENCY_EMAIL = BENSON.getEmergencyContact().get().email.toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(VALID_EMERGENCY_NAME,
                VALID_EMERGENCY_PHONE, VALID_EMERGENCY_EMAIL);
        assertEquals(BENSON.getEmergencyContact().get(), emergencyContact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(INVALID_NAME, VALID_PHONE,
                VALID_EMAIL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(null, VALID_PHONE, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(VALID_NAME, INVALID_PHONE,
                VALID_EMAIL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(VALID_NAME, null, VALID_EMAIL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(VALID_NAME, VALID_PHONE,
                INVALID_EMAIL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedEmergencyContact emergencyContact = new JsonAdaptedEmergencyContact(VALID_NAME, VALID_PHONE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, emergencyContact::toModelType);
    }
}
