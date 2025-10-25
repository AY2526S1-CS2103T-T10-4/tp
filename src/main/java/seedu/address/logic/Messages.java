package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.event.Event;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_EMPTY_EVENT = "Event list is empty";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EMPTY_USER_CONTACT = "Nothing to %s so far, "
            + "since no student's data has been entered and stored in CCAmper.";


    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Roles: ");
        person.getRoles().forEach(builder::append);

        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);

        builder.append("; Enrollment Year: ")
               .append(person.getEnrollmentYear());

        builder.append("; Pinned: ")
                .append(person.getPin());

        if (person.getEmergencyContact().isPresent()) {
            EmergencyContact emergencyContact = person.getEmergencyContact().get();
            builder.append("; Emergency Contact Name: ")
                    .append(emergencyContact.name)
                    .append("; Emergency Contact Phone: ")
                    .append(emergencyContact.phone);
        }

        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String format(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append(event.getName())
                .append("; Duration: ")
                .append(event.getDuration())
                .append("; Description: ")
                .append(event.getDescription());

        return builder.toString();
    }

    public static String specifyEmptyUserListMessage(String commandWord) {
        return String.format(MESSAGE_EMPTY_USER_CONTACT, commandWord);
    }
}
