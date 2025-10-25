package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TRAINING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TRAINING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TRAINING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {
    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event MEETING = new EventBuilder().withName(VALID_NAME_MEETING)
            .withDuration(VALID_DURATION_MEETING).withDescription(VALID_DESCRIPTION_MEETING).build();
    public static final Event TRAINING = new EventBuilder().withName(VALID_NAME_TRAINING)
            .withDuration(VALID_DURATION_TRAINING).withDescription(VALID_DESCRIPTION_TRAINING).build();

    public static final Event TRAINING_2 = new EventBuilder().withName(VALID_NAME_TRAINING)
            .withDuration("1/9/2025").withDescription(VALID_DESCRIPTION_TRAINING).build();

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, TRAINING, TRAINING_2));
    }

    /**
     * Returns an {@code AddressBook} with all the typical events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }
}
