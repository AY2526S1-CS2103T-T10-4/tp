package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROLL_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EnrollmentYearPredicate;
import seedu.address.model.person.MultiPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagsContainKeywordsPredicate;

/**
 * Parses input arguments, prefix field, and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * with exactly one prefix specified, and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_ENROLL_YEAR);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TAG, PREFIX_ENROLL_YEAR);

        List<Predicate<Person>> predicates = new ArrayList<Predicate<Person>>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String name = argMultimap.getValue(PREFIX_NAME).get();
            String[] nameKeywords = name.split("\\s+");

            if (name.isEmpty()) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            // Throws an error if invalid name is supplied
            for (String s : nameKeywords) {
                ParserUtil.parseName(s);
            }

            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String tag = argMultimap.getValue(PREFIX_TAG).get();
            String[] tagKeywords = tag.split("\\s+");

            if (tag.isEmpty()) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            // Throws an error if invalid tag is supplied
            for (String s : tagKeywords) {
                ParserUtil.parseTag(s);
            }

            predicates.add(new TagsContainKeywordsPredicate(Arrays.asList(tagKeywords)));
        }

        if (argMultimap.getValue(PREFIX_ENROLL_YEAR).isPresent()) {
            String enrollmentConstraint = argMultimap.getValue(PREFIX_ENROLL_YEAR).get();
            predicates.add(new EnrollmentYearPredicate(enrollmentConstraint));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(new MultiPredicate(predicates));
    }
}
