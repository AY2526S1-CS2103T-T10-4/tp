package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the events in the address book.
 */
public class ClearEventCommand extends Command {

    public static final String COMMAND_WORD = "clear:event";
    public static final String MESSAGE_SUCCESS = "Address book events has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearEvent();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
