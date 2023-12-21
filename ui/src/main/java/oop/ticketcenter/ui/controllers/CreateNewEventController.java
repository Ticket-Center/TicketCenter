package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.events.create.CreateEvent;
import oop.ticketcenter.core.interfaces.events.create.CreateEventInput;
import oop.ticketcenter.core.interfaces.events.create.CreateEventResult;
import oop.ticketcenter.core.interfaces.events.create.SeatTypes;
import oop.ticketcenter.core.services.helpers.GetEventGenres;
import oop.ticketcenter.core.services.helpers.GetEventSeatTypes;
import oop.ticketcenter.core.services.helpers.GetEventSellers;
import oop.ticketcenter.core.services.helpers.GetEventTypes;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    private ChoiceBox<String> eventGenre;

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
    private ChoiceBox<String> eventType;

    @FXML
    private TextField maxTicketsPerPerson;

    @FXML
    private TextField quantity1;

    @FXML
    private HBox seatTypes;

    @FXML
    private VBox seatTypesLabels;

    @FXML
    private VBox seatTypesQuantity;


    @FXML
    private Label wrongInput;

    @FXML
    private CheckBox seller1;

    @Autowired
    private CreateEvent createEvent;

    private GetEventSellers getEventSellers;
    private GetEventTypes getEventTypes;
    private GetEventSeatTypes getEventSeatTypes = GetEventSeatTypes.getInstance();
    private GetEventGenres getEventGenres;

    @FXML
    public void initialize() {
       // TODO: load availiable event genres, event sellers, seat types, event types ? could persistence layer be invoked here??????
        ObservableList<String> listGenres = FXCollections.observableArrayList();
        getEventGenres.getGenres().forEach(genre->{
            listGenres.add(genre);
        });
        eventGenre.setItems(listGenres);

        ObservableList<String> listEventType = FXCollections.observableArrayList();
        getEventTypes.getTypes().forEach(type ->{
            listEventType.add(type);
        });
        eventType.setItems(listEventType);

        ObservableList<String> listSellers = FXCollections.observableArrayList();
        getEventSellers.getSellers().forEach(seller -> {
            listSellers.add(seller);
        });

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

    @FXML
    void enteredEventPlace(){
        ObservableList<Map<String, Integer>> listSeatTypes = FXCollections.observableArrayList();
        listSeatTypes.add(getEventSeatTypes.getSeatTypes(eventPlace.getText()));
        listSeatTypes.forEach(seatType ->{
            Label label2 = new Label(seatType.toString());
            seatTypesLabels.getChildren().add(label2);
            TextField textField2 = new TextField(seatType.get(seatType.toString()).toString());
            seatTypesQuantity.getChildren().add(textField2);
        });
    }

    private List<String> getEventSellers(){
        return null;
    }

    private Set<SeatTypes> getSeatTypes(){
        return null;
    }

}
