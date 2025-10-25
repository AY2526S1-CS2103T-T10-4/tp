package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ConsolidateCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MultiPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();
    private String invalidCommandFormat = "When using %1$s, "
            + "there should not be any characters (except whitespace, which is allowed) "
            + "that comes before and/or follow %1$s.";

    private void createInvalidCommandWthOtherCommandWords(String firstCommand) throws ParseException {
        String invalidCommand = firstCommand + " " + FindCommand.COMMAND_WORD;
        parser.parseCommand(invalidCommand);
    }

    private void createInvalidCommandWthRandomWords(String firstCommand) throws ParseException {
        String invalidCommand = firstCommand + " aab";
        parser.parseCommand(invalidCommand);
    }

    private String populateInvalidCommandFormat(String commandWord) {
        return String.format(invalidCommandFormat, commandWord);
    }

    private Command createCommandWithSpace(String commandWord) throws ParseException {
        return parser.parseCommand("  " + commandWord + "    ");
    }

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(createCommandWithSpace(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_invalidClearCommandWithMultipleCommand_throwsParseException() {
        try {
            createInvalidCommandWthOtherCommandWords(ClearCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ClearCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidClearCommandWithRandomWord_throwsParseException() {
        try {
            createInvalidCommandWthRandomWords(ClearCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ClearCommand.COMMAND_WORD), e.getMessage());
        }
    }


    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(createCommandWithSpace(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_invalidExitCommandWithMultipleCommand_throwsParseException() {
        try {
            createInvalidCommandWthOtherCommandWords(ExitCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ExitCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidExitCommandWithRandomWord_throwsParseException() {
        try {
            createInvalidCommandWthRandomWords(ExitCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ExitCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                PersonUtil.getFindCommand(keywords));
        List<Predicate<Person> > predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(keywords));
        assertEquals(new FindCommand(new MultiPredicate(predicates)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(createCommandWithSpace(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_invalidHelpCommandWithMultipleCommand_throwsParseException() {
        try {
            createInvalidCommandWthOtherCommandWords(HelpCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(HelpCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidHelpCommandWithRandomWord_throwsParseException() {
        try {
            createInvalidCommandWthRandomWords(HelpCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(HelpCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(createCommandWithSpace(ListCommand.COMMAND_WORD)instanceof ListCommand);
    }

    @Test
    public void parseCommand_invalidListCommandWithMultipleCommand_throwsParseException() {
        try {
            createInvalidCommandWthOtherCommandWords(ListCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ListCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidListCommandWithRandomWord_throwsParseException() {
        try {
            createInvalidCommandWthRandomWords(ListCommand.COMMAND_WORD);
            fail(); // test should not reach this line
        } catch (ParseException e) {
            assertEquals(populateInvalidCommandFormat(ListCommand.COMMAND_WORD), e.getMessage());
        }
    }

    @Test
    public void parseCommand_consolidate() throws Exception {
        assertTrue(parser.parseCommand(ConsolidateCommand.COMMAND_WORD) instanceof ConsolidateCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
