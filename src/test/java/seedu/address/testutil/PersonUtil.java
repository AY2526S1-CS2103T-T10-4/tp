package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROLL_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;
import seedu.address.model.role.Role;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }


    /**
     * Returns the part of command string for the given {@code FindCommand} details.
     */
    public static String getFindCommand(List<String> keywords) {
        return FindCommand.COMMAND_WORD + " " + PREFIX_NAME + String.join(" ", keywords);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getRoles().stream().forEach(
                s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_PIN + person.getPin().toString() + " ");
        if (person.getEmergencyContact().isPresent()) {
            final EmergencyContact emergencyContact = person.getEmergencyContact().get();
            sb.append(PREFIX_EMERGENCY_NAME + emergencyContact.name.fullName + " ");
            sb.append(PREFIX_EMERGENCY_PHONE + emergencyContact.phone.value + " ");
            sb.append(PREFIX_EMERGENCY_EMAIL + emergencyContact.email.value + " ");
        }
        sb.append(PREFIX_ENROLL_YEAR + person.getEnrollmentYear().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getRoles().isPresent()) {
            Set<Role> roles = descriptor.getRoles().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_ROLE).append(" ");
            } else {
                roles.forEach(s -> sb.append(PREFIX_ROLE).append(s.roleName).append(" "));
            }
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getPin().ifPresent(pin ->
                sb.append(PREFIX_PIN)
                        .append(pin.value ? "True" : "False")
                        .append(" "));
        descriptor.getEmergencyContact().ifPresent(emergencyContact -> {
            sb.append(PREFIX_EMERGENCY_NAME + emergencyContact.name.fullName + " ");
            sb.append(PREFIX_EMERGENCY_PHONE + emergencyContact.phone.value + " ");
            sb.append(PREFIX_EMERGENCY_EMAIL + emergencyContact.email.value + " ");
        });
        descriptor.getEnrollmentYear().ifPresent(enrollmentYear ->
            sb.append(PREFIX_ENROLL_YEAR)
              .append(enrollmentYear.isPresent ? enrollmentYear.year : "")
              .append(" "));
        return sb.toString();
    }
}
