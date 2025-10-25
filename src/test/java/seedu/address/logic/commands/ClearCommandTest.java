package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.ClearCommand.COMMAND_WORD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.executeCommandOnEmptyModel;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        try {
            executeCommandOnEmptyModel(new ClearCommand());
            fail(); // test should not reach this line
        } catch (CommandException e) {
            assertEquals(Messages.specifyEmptyUserListMessage(COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addEvent(MEETING);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());
        expectedModel.addEvent(MEETING);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_selectedPersonReturnsNull() {
        try {
            Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
            Person lastPerson = model.getFilteredPersonList().get(model.getFilteredPersonList().size() - 1);
            model.getSelectedPerson().set(lastPerson);
            new ClearCommand().execute(model);
            assertNull(model.getSelectedPerson().get());
        } catch (CommandException e) {
            fail();
        }
    }
}
