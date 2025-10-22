package seedu.address.ui.detailedpanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

/**
 * Panel showing helpful information on initialization of AddressBook, or when filtered person list is empty.
 */
public class HelpPanel extends UiPart<VBox> {
    private static final String FXML = "detailedpanel/HelpPanel.fxml";
    private static final Image tentImage = new Image("/images/tent.png");

    @FXML
    private Label title;
    @FXML
    private ImageView tentIcon;
    @FXML
    private Label description;

    /**
     * Constructs help panel with text.
     */
    public HelpPanel() {
        super(FXML);
        tentIcon.setImage(tentImage);
        title.setGraphic(tentIcon);
    }
}
