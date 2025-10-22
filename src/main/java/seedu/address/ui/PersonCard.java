package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    private final Image pinImage = new Image("/images/pin.png");

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private ImageView pin;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label emergency;
    @FXML
    private Label enrollmentYear;
    @FXML
    private FlowPane roles;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        // Sets visibility of pin icon next to name.
        // Modifying Ui element on JavaFx Application Thread with runLater prevents UI flickering.
        pin.setImage(pinImage);
        boolean isPinned = person.getPin().value;
        pin.setVisible(isPinned);

        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        if (person.getEmergencyContact().isPresent()) {
            EmergencyContact emergencyContact = person.getEmergencyContact().get();
            String displayMessage = String.format("Emergency Contact: %s, %s, %s", emergencyContact.name.fullName,
                    emergencyContact.phone.value, emergencyContact.email.value);
            emergency.setText(displayMessage);
        } else {
            emergency.setText("Missing emergency contact.");
        }

        String enrollmentYearStr = person.getEnrollmentYear().toString();
        enrollmentYear.setText(enrollmentYearStr.isEmpty()
                ? "No enrollment year" : "Enrollment Year: " + enrollmentYearStr);
        person.getRoles().stream()
                .sorted(Comparator.comparing(role -> role.roleName))
                .forEach(role -> roles.getChildren().add(new Label(role.roleName)));

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
