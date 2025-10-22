package seedu.address.model.person;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tests that a {@code Person}'s {@code EnrollmentYear} satisfies a numerical constraint.
 */
public class EnrollmentYearPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS = "Enrollment year filter string should be either a blank "
        + "string to indicate having no enrollment year, or an integer preceded by a constraint of the following: "
        + "(<, <=, >=, >, =).";

    /**
     * Represents the different possible operators that the input can provide.
     */
    public enum Constraint {
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        EQUAL,
        NONE
    };

    private static final String VALIDATION_REGEX = "^(>=|<=|>|<|=)\\s*(\\d+)$";
    private static final Pattern PATTERN = Pattern.compile(VALIDATION_REGEX);

    private final Constraint constraint;
    private final int value;

    /**
     * Creates the Predicate from the user input.
     * @param input User input
     * @throws ParseException if the user input is invalid
     */
    public EnrollmentYearPredicate(String input) throws ParseException {
        String trimmedInput = input.trim();
        try {
            if (trimmedInput.isEmpty()) {
                constraint = Constraint.NONE;
                value = 0;
            } else {
                Matcher matcher = PATTERN.matcher(trimmedInput);
                if (!matcher.matches()) {
                    throw new ParseException(MESSAGE_CONSTRAINTS);
                }

                String operator = matcher.group(1);
                this.value = Integer.parseInt(matcher.group(2));

                switch (operator) {
                case ">" -> this.constraint = Constraint.GREATER_THAN;
                case ">=" -> this.constraint = Constraint.GREATER_THAN_OR_EQUAL;
                case "<" -> this.constraint = Constraint.LESS_THAN;
                case "<=" -> this.constraint = Constraint.LESS_THAN_OR_EQUAL;
                case "=" -> this.constraint = Constraint.EQUAL;
                default -> throw new ParseException(MESSAGE_CONSTRAINTS);
                }
            }
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public boolean test(Person person) {
        EnrollmentYear enrollmentYear = person.getEnrollmentYear();
        if (!enrollmentYear.isPresent()) {
            return this.constraint == Constraint.NONE;
        }
        int year = enrollmentYear.getValue();
        switch (constraint) {
        case GREATER_THAN:
            return year > this.value;
        case GREATER_THAN_OR_EQUAL:
            return year >= this.value;
        case LESS_THAN:
            return year < this.value;
        case LESS_THAN_OR_EQUAL:
            return year <= this.value;
        case EQUAL:
            return year == this.value;
        default:
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrollmentYearPredicate)) {
            return false;
        }

        EnrollmentYearPredicate otherEnrollmentYearPredicate = (EnrollmentYearPredicate) other;
        return constraint.equals(otherEnrollmentYearPredicate.constraint)
            && value == otherEnrollmentYearPredicate.value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("constraint", constraint).add("value", value).toString();
    }
}
