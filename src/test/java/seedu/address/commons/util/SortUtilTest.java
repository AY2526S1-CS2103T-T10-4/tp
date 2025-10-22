package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class SortUtilTest {
    private static final Person A_UNPINNED = new PersonBuilder()
            .withName("A").withPin(false).build();
    private static final Person B_PINNED = new PersonBuilder()
            .withName("B").withPin(true).build();
    private static final Person C_UNPINNED = new PersonBuilder()
            .withName("A").withPin(false).build();
    private static final Person D_PINNED = new PersonBuilder()
            .withName("B").withPin(true).build();

    @Test
    public void sortDefaultPin_sortsCorrectly() {
        List<Person> unsortedList = List.of(A_UNPINNED, B_PINNED, C_UNPINNED, D_PINNED);
        List<Person> sortedList = unsortedList.stream().sorted(SortUtil.SORT_DEFAULT_PIN).toList();

        List<Person> expectedList = List.of(B_PINNED, D_PINNED, A_UNPINNED, C_UNPINNED);

        assertEquals(expectedList, sortedList);
    }

    @Test
    public void sortDefaultPin_isStable() {
        List<Person> unsortedList = List.of(D_PINNED, B_PINNED, C_UNPINNED, A_UNPINNED);
        List<Person> sortedList = unsortedList.stream().sorted(SortUtil.SORT_DEFAULT_PIN).toList();

        List<Person> expectedList = List.of(D_PINNED, B_PINNED, C_UNPINNED, A_UNPINNED);

        assertEquals(expectedList, sortedList);
    }
}
