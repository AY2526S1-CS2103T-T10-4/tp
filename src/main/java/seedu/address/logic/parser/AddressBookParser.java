package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearEventCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ConsolidateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    private void checkValidUserInput(String userInput, String keyword) throws ParseException {
        boolean validInput = (userInput.replaceAll("\\s", "").equals(keyword));
        if (!validInput) {
            String message = "When using " + keyword + ", "
                    + "there should not be any characters (except whitespace, which is allowed) "
                    + "that comes before and/or follow " + keyword + ".";
            throw new ParseException(message);
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return new DeleteEventCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            checkValidUserInput(userInput, ClearCommand.COMMAND_WORD);
            return new ClearCommand();

        case ClearEventCommand.COMMAND_WORD:
            checkValidUserInput(userInput, ClearEventCommand.COMMAND_WORD);
            return new ClearEventCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ConsolidateCommand.COMMAND_WORD:
            checkValidUserInput(userInput, ConsolidateCommand.COMMAND_WORD);
            return new ConsolidateCommand();

        case ListCommand.COMMAND_WORD:
            checkValidUserInput(userInput, ListCommand.COMMAND_WORD);
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            checkValidUserInput(userInput, ExitCommand.COMMAND_WORD);
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            checkValidUserInput(userInput, HelpCommand.COMMAND_WORD);
            return new HelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
