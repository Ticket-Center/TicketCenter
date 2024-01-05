package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.EventArchivatedException;
import oop.ticketcenter.core.exceptions.EventDoesNotExistException;
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
    private TextField eventTitleNew;

    @FXML
    private TextField eventTitleOld;

    @FXML
    private TextField eventType;

    @FXML
    private TextField maxTicketsPerPerson;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblOrganizator;

    @FXML
    private Label lblOwner;

    @FXML
    private Label lblPlace;

    @FXML
    private Label lblSellers;

    @FXML
    private Label lblType;

    @FXML
    private Label lblNewTitle;

    @FXML
    private Label wrongInput;

    @Autowired
    private EditEvent editEvent;

    @Autowired
    private GetEventByOwner getEventByOwner;

    @FXML
    public void initialize() {
        eventGenre.setVisible(false);
        eventType.setVisible(false);
        eventOwner.setVisible(false);
        eventOrganizator.setVisible(false);
        eventSellers.setVisible(false);
        eventPlace.setVisible(false);
        lblGenre.setVisible(false);
        lblOrganizator.setVisible(false);
        lblOwner.setVisible(false);
        lblPlace.setVisible(false);
        lblType.setVisible(false);
        lblSellers.setVisible(false);
        lblNewTitle.setVisible(false);
        eventTitleNew.setVisible(false);
    }

    @FXML
    void back() throws IOException {
        SceneSwitcher.switchScene((Stage) eventOwner.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    void editevent() {
        EditEventInput input = EditEventInput.builder()
                .maxTicketsPerPerson(Integer.valueOf(maxTicketsPerPerson.getText()))
                .newTitle(eventTitleNew.getText())
                .oldTitle(eventTitleOld.getText())
                .ownerUsername(ActiveUserSingleton.getInstance().getUsername())
                .build();
        wrongInput.setText("");
        try {
            EditEventResult result = editEvent.process(input);
            showEventProperties(result);
            buttonEdit.setDisable(true);
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }

    }

    @FXML
    void eventTitleEntered() {
        try {
            Event event = getEventByOwner.getEvents(eventTitleOld.getText());
            //events.forEach(event -> {
            maxTicketsPerPerson.setText(event.getMaxTicketsPerPerson().toString());
            eventTitleOld.setText(event.getTitle());
            // });
            lblNewTitle.setVisible(true);
            eventTitleNew.setVisible(true);
        } catch (EventArchivatedException | EventDoesNotExistException e) {
            wrongInput.setText(e.getMessage());
        }
    }

    void showEventProperties(EditEventResult result) {
        wrongInput.setText("Event edited successfully");
        eventGenre.setVisible(true);
        eventType.setVisible(true);
        eventOwner.setVisible(true);
        eventOrganizator.setVisible(true);
        eventSellers.setVisible(true);
        eventPlace.setVisible(true);
        lblGenre.setVisible(true);
        lblOrganizator.setVisible(true);
        lblOwner.setVisible(true);
        lblPlace.setVisible(true);
        lblType.setVisible(true);
        lblSellers.setVisible(true);

        eventGenre.setText(result.getEventGenre());
        eventType.setText(result.getEventType());
        eventOwner.setText(result.getEventOwnerUsername());
        eventOrganizator.setText(result.getEventOrganizatorUsername());
        eventSellers.setText(result.getEventSellers().toString());
        eventPlace.setText(result.getEventPlace());
    }

}
