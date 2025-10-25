package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

public class ClearEventCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearEventCommand(), model, ClearEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        for (Event e: getTypicalEvents()) {
            model.addEvent(e);
        }
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ClearEventCommand(), model, ClearEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_selectedEventReturnsNull() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        for (Event e: getTypicalEvents()) {
            model.addEvent(e);
        }
        Event lastEvent = model.getFilteredEventList().get(model.getFilteredEventList().size() - 1);
        model.getSelectedEvent().set(lastEvent);
        new ClearEventCommand().execute(model);

        assertNull(model.getSelectedEvent().get());
    }
}
