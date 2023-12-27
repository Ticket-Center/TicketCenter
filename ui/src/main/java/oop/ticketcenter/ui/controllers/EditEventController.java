package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.edit.EditEvent;
import oop.ticketcenter.core.interfaces.events.edit.EditEventInput;
import oop.ticketcenter.core.interfaces.events.edit.EditEventResult;
import oop.ticketcenter.core.services.helpers.*;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class EditEventController {
    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEdit;

    @FXML
    private TextField eventGenre;

    @FXML
    private TextField eventOrganizator;

    @FXML
    private TextField eventOwner;

    @FXML
    private TextField eventPlace;

    @FXML
    private TextArea eventSellers;

    @FXML
    private TextField eventTitle;

    @FXML
    private TextField eventType;

    @FXML
    private TextField maxTicketsPerPerson;

    @FXML
    private Label wrongInput;

    @Autowired
    private EditEvent editEvent;

    @FXML
    public void initialize() {
        eventGenre.setVisible(false);
        eventType.setVisible(false);
        eventOwner.setVisible(false);
        eventOrganizator.setVisible(false);
        eventSellers.setVisible(false);
        eventPlace.setVisible(false);
    }

    @FXML
    void back() throws IOException {
        SceneSwitcher.switchScene((Stage) eventOwner.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    void editevent() {
        EditEventInput input = EditEventInput.builder()
                .maxTicketsPerPerson(Integer.getInteger(maxTicketsPerPerson.getText()))
                .newTitle(eventTitle.getText())
                .ownerUsername(ActiveUserSingleton.getInstance().getUsername())
                .build();
        wrongInput.setText("");
        try {
            EditEventResult result = editEvent.process(input);
            showEventProperties(result);
            //SceneSwitcher.switchScene((Stage) eventOwner.getScene().getWindow() , FXMLPaths.HOME_PAGE.getPath());
        } catch (UserNotFoundException | IncorrectInputException e) {
            wrongInput.setText(e.getMessage());
        }

    }

    @FXML
    void eventTitleEntered() {
        Set<Event> events = GetEventByOwner.getInstance().getEvents();
        events.forEach(event -> {
            if (event.getTitle().equals(eventTitle.getText())) {
                maxTicketsPerPerson.setText(event.getMaxTicketsPerPerson().toString());
                eventTitle.setText(event.getTitle());
            }
        });
    }

    void showEventProperties(EditEventResult result) {
        wrongInput.setText("Event edited successfully");
        eventGenre.setVisible(true);
        eventType.setVisible(true);
        eventOwner.setVisible(true);
        eventOrganizator.setVisible(true);
        eventSellers.setVisible(true);
        eventPlace.setVisible(true);

        eventGenre.setText(result.getEventGenre());
        eventType.setText(result.getEventType());
        eventOwner.setText(result.getEventOwnerUsername());
        eventOrganizator.setText(result.getEventOrganizatorUsername());
        eventSellers.setText(result.getEventSellers().toString());
        eventPlace.setText(result.getEventPlace());
    }

}
