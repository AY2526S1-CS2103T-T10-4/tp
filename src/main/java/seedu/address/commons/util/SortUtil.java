package seedu.address.commons.util;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * Defines the sorting comparator logics for persons.
 */
public class SortUtil {
    public static final Comparator<Person> SORT_DEFAULT_PIN = (p1, p2) ->
            Boolean.compare(p2.getPin().value, p1.getPin().value);
    //TODO: Unused, used for testing. Can be added in a future update.
    public static final Comparator<Person> SORT_NAME_ALPHABETICAL_ASC = (p1, p2) ->
            p1.getName().fullName.compareTo(p2.getName().fullName);
}
