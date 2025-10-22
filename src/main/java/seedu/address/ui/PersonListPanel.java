package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.ui.detailedpanel.DetailedPanel;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Binds the PersonDetailedView to the currently selected person from {@code ListView}.
     * When the selected person changes, the new details are reflected in PersonDetailedView.
     */
    public void listenForSelectionEvent(DetailedPanel personDetailedPanel) {
        personListView.getSelectionModel().selectedItemProperty().addListener(((
                observable, oldValue, newValue) -> {
                    personDetailedPanel.updateDetails(newValue);
                }));
    }

    public void setSelectedPerson(Person p) {
        if (p == null) {
            personListView.getSelectionModel().clearSelection();
        }
        personListView.getSelectionModel().select(p);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                // Updates the pseudo-class "pin" if pin status changes.
                this.pseudoClassStateChanged(PseudoClass.getPseudoClass("pin"), person.getPin().value);
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
