package seedu.address.ui.detailedpanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel showing detailed information of the person selected.
 */
public class PersonPanel extends UiPart<VBox> {
    private static final String FXML = "detailedpanel/PersonPanel.fxml";

    private static final String MESSAGE_NO_EMERGENCY_CONTACT =
            "No Emergency Contact detail provided.\n"
            + "Add one using `edit INDEX ecn/NAME ecp/PHONE`";

    private static final Image pinImage = new Image("/images/pin.png");
    private static final Image contactImage = new Image("/images/contact.png");
    private static final Image phoneImage = new Image("/images/telephone.png");
    private static final Image emailImage = new Image("/images/email.png");
    private static final Image addressImage = new Image("/images/home.png");
    private static final Image yearImage = new Image("/images/year.png");

    @FXML
    private Label name;
    @FXML
    private ImageView pinIcon;
    @FXML
    private FlowPane roles;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private Label phone;
    @FXML
    private ImageView emailIcon;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private ImageView addressIcon;
    @FXML
    private Label year;
    @FXML
    private ImageView yearIcon;
    @FXML
    private VBox emergencyTitle;
    @FXML
    private Label emergencyName;
    @FXML
    private ImageView emergencyNameIcon;
    @FXML
    private Label emergencyPhone;
    @FXML
    private ImageView emergencyPhoneIcon;
    @FXML
    private Label emergencyEmail;
    @FXML
    private ImageView emergencyEmailIcon;

    /**
     * Constructs PersonPanel and links the graphics.
     */
    public PersonPanel() {
        super(FXML);
        pinIcon.setImage(pinImage);
        phoneIcon.setImage(phoneImage);
        emailIcon.setImage(emailImage);
        addressIcon.setImage(addressImage);
        yearIcon.setImage(yearImage);
        emergencyNameIcon.setImage(contactImage);
        emergencyPhoneIcon.setImage(phoneImage);
        emergencyEmailIcon.setImage(emailImage);

        name.setGraphic(pinIcon);
        phone.setGraphic(phoneIcon);
        email.setGraphic(emailIcon);
        address.setGraphic(addressIcon);
        year.setGraphic(yearIcon);
        emergencyName.setGraphic(emergencyNameIcon);
        emergencyPhone.setGraphic(emergencyPhoneIcon);
        emergencyEmail.setGraphic(emergencyEmailIcon);
    }

    /**
     * Populates the panel with details of the selected person.
     */
    public void updateDetails(Person selected) {
        assert selected != null : "person cannot be null";

        name.setText(selected.getName().fullName);

        if (selected.getPin().value) {
            pinIcon.setManaged(true);
            pinIcon.setVisible(true);
        } else {
            pinIcon.setManaged(false);
            pinIcon.setVisible(false);
        }

        roles.getChildren().clear();
        tags.getChildren().clear();
        if (selected.getRoles().isEmpty()) {
            roles.setManaged(false);
        } else {
            roles.setManaged(true);
            roles.getChildren().addAll(selected.getRoles().stream().map(r -> new Label(r.roleName)).toList());
        }
        if (selected.getTags().isEmpty()) {
            tags.setManaged(false);
        } else {
            tags.setManaged(true);
            tags.getChildren().addAll(selected.getTags().stream().map(r -> new Label(r.tagName)).toList());
        }

        phone.setText(selected.getPhone().value);
        email.setText(selected.getEmail().value);
        address.setText(selected.getAddress().value);
        if (selected.getEnrollmentYear().isPresent) {
            year.setText(String.valueOf(selected.getEnrollmentYear().year));
        } else {
            year.setText("No year specified");
        }

        if (selected.getEmergencyContact().isPresent()) {
            emergencyPhone.setManaged(true);
            emergencyPhone.setVisible(true);
            emergencyEmail.setManaged(true);
            emergencyEmail.setVisible(true);

            EmergencyContact ec = selected.getEmergencyContact().get();
            emergencyName.setText(ec.name.fullName);
            emergencyPhone.setText(ec.phone.value);
            emergencyEmail.setText(ec.email.value);
        } else {
            emergencyName.setText(MESSAGE_NO_EMERGENCY_CONTACT);
            emergencyPhone.setManaged(false);
            emergencyPhone.setVisible(false);
            emergencyEmail.setManaged(false);
            emergencyEmail.setVisible(false);
        }
    }
}
