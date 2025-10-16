package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import static java.util.Objects.requireNonNull;

public class ListPhoneCommand extends Command {

    public static final String COMMAND_WORD = "list:phone";

    public static final String MESSAGE_SUCCESS = "List all distinct students' phone:";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": list all distinct students' phone numbers currently in address book. \n"
            + "If two students have same phone number, phone number only appears once.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        HashSet<String> container = new HashSet<>();
        for (Person person : model.getAddressBook().getPersonList()) {
            container.add(person.getPhone().toString());
        }

        ArrayList<String> list = new ArrayList<>(container);
        Collections.sort(list);
        String compilation = "";

        for (String s : list) {
            compilation += "\n" + s;
        }

        return new CommandResult(MESSAGE_SUCCESS + compilation);
    }
}
