package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class CreateNewEventController {

    @FXML
    private Label Type1;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonCreate;

    @FXML
    private ChoiceBox<?> eventGenre;

    @FXML
    private TextField eventOrganizator;

    @FXML
    private TextField eventOwner;

    @FXML
    private TextField eventPlace;

    @FXML
    private HBox eventSellers;

    @FXML
    private TextField eventTitle;

    @FXML
    private ChoiceBox<?> eventType;

    @FXML
    private TextField maxTicketsPerPerson;

    @FXML
    private TextField quantity1;

    @FXML
    private HBox seatTypes;

    @FXML
    private Label wrongInput;

    @FXML
    private CheckBox seller1;

    @Autowired
    private CreateEvent createEvent;

    @FXML
    public void initialize() {
       // TODO: load availiable event genres, event sellers, seat types, event types ? could persistence layer be invoked here??????
    }

    @FXML
    void back() throws IOException {
        SceneSwitcher.switchScene((Stage) eventOwner.getScene().getWindow() , FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    void createevent() {
        CreateEventInput input = CreateEventInput.builder()
                .title(eventTitle.getText())
                .maxTicketsPerPerson(Integer.getInteger(maxTicketsPerPerson.getText()))
                .eventGenre(eventGenre.getValue().toString())
                .eventType(eventType.getValue().toString())
                .eventOwnerUsername(eventOwner.getText())
                .eventOrganizatorUsername(eventOrganizator.getText())
                .eventPlace(eventPlace.getText())
                .eventSellers(getEventSellers())
                .seatTypes(getSeatTypes())
                .build();
        wrongInput.setText("");
        try {
            CreateEventResult result = createEvent.process(input);
            SceneSwitcher.switchScene((Stage) eventOwner.getScene().getWindow() , FXMLPaths.HOME_PAGE.getPath());
        } catch (UserNotFoundException | IncorrectInputException e) {
            wrongInput.setText(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<String> getEventSellers(){
        return null;
    }

    private Set<SeatTypes> getSeatTypes(){
        return null;
    }

}
