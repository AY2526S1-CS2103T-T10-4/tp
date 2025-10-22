package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PinTest {
    @Test
    public void equals() {
        Pin pin = new Pin(true);

        // same values -> returns true
        assertTrue(pin.equals(new Pin(true)));

        // same object -> returns true
        assertTrue(pin.equals(pin));

        // null -> returns false
        assertFalse(pin.equals(null));

        // different types -> returns false
        assertFalse(pin.equals(5.0f));

        // different values -> returns false
        assertFalse(pin.equals(new Pin(false)));
    }
}
