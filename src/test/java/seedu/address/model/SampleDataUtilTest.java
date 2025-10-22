package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {

    private final ReadOnlyAddressBook addressBook = SampleDataUtil.getSampleAddressBook();

    @Test
    public void constructor() {
        assertTrue(addressBook.getPersonList().size() > 0);
    }
}
