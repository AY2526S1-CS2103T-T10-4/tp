package seedu.address.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "president!";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRoleName() {
        // null role name
        assertThrows(NullPointerException.class, () -> Role.isValidRoleName(null));

        // invalid role names
        assertFalse(Role.isValidRoleName("")); // empty string
        assertFalse(Role.isValidRoleName("president@"));

        // valid role names
        assertTrue(Role.isValidRoleName("93121534"));
        assertTrue(Role.isValidRoleName("section leader 1"));
        assertTrue(Role.isValidRoleName("    section leader  2      "));
        assertTrue(Role.isValidRoleName(" ")); // spaces only
    }

    @Test
    public void equals() {
        Role role = new Role("section leader");

        // same values -> returns true
        assertTrue(role.equals(new Role("section leader")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("section lead")));
    }

    @Test
    public void toString_test() {
        Role role = new Role("section leader");

        assertTrue(role.toString().equals("(Role: section leader)"));
    }
}
