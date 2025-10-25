package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the people in the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book contacts has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isEmptyAddressBook()) {
            throw new CommandException(Messages.specifyEmptyUserListMessage(COMMAND_WORD));
        }
        model.clearPerson();;
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
