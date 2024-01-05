package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEvent;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventInput;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventResult;
import oop.ticketcenter.core.interfaces.events.edit.EditEvent;
import oop.ticketcenter.core.interfaces.events.edit.EditEventInput;
import oop.ticketcenter.core.interfaces.events.edit.EditEventResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetEventByOwner;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class DeleteEventController {
    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDelete;

    @FXML
    private ChoiceBox<String> event;

    @FXML
    private Label wrongInput;

    @Autowired
    private GetEvents getEvents;

    @Autowired
    private DeleteEvent deleteEvent;

    @FXML
    private void initialize() {
        try {
            ObservableList<String> listEvents = FXCollections.observableArrayList();
            listEvents.addAll(getEvents.fetchAllEventsTitles());
            event.setItems(listEvents);
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }
    }

    @FXML
    void back() throws IOException {
        SceneSwitcher.switchScene((Stage) wrongInput.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    void deleteevent() {
        DeleteEventInput input = DeleteEventInput.builder()
                .eventTitle(event.getValue())
                .build();
        wrongInput.setText("");
        try {
            DeleteEventResult result = deleteEvent.process(input);
            wrongInput.setText("Event deleted successfully");
            buttonDelete.setDisable(true);
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }

    }
}
