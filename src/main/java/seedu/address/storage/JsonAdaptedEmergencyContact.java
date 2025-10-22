package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link EmergencyContact}.
 */
class JsonAdaptedEmergencyContact {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Emergency contact's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;

    /**
     * Constructs a {@code JsonAdaptedEmergencyContact} with the given {@code name, phone, email}.
     */
    @JsonCreator
    public JsonAdaptedEmergencyContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                       @JsonProperty("email") String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Converts a given {@code EmergencyContact} into this class for Jackson use.
     */
    public JsonAdaptedEmergencyContact(EmergencyContact source) {
        name = source.name.fullName;
        phone = source.phone.value;
        email = source.email.value;
    }

    /**
     * Converts this Jackson-friendly adapted emergency contact object into the model's {@code EmergencyContact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted emergency contact.
     */
    public EmergencyContact toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new EmergencyContact(name, phone, email);
    }

}
