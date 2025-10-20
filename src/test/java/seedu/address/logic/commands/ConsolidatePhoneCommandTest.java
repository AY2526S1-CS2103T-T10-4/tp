package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalPersons;

public class ConsolidatePhoneCommandTest {


    @Test
    public void execute_validAddressBook_success() {
        Model model = new ModelManager();
        model.setAddressBook(new AddressBook());
        model.addPerson(TypicalPersons.CARL);
        model.addPerson(TypicalPersons.DANIEL);
        String commandResult = ConsolidatePhoneCommand.MESSAGE_SUCCESS + "\n";
        if (CARL.getPhone().toString().compareTo(DANIEL.getPhone().toString()) < 0) {
            commandResult += CARL.getPhone() + "\n" + DANIEL.getPhone();
        } else if (CARL.getPhone().toString().compareTo(DANIEL.getPhone().toString()) == 0) {
            commandResult += CARL.getPhone();
        } else {
            commandResult += DANIEL.getPhone() + "\n" + CARL.getPhone();
        }
        assertCommandSuccess(new ConsolidatePhoneCommand(), model, commandResult, model);
    }
}
