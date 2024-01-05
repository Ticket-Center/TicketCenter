package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import oop.ticketcenter.core.services.helpers.*;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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
    private ChoiceBox<String> eventOrganizator;

    @FXML
    private ChoiceBox<String> eventOwner;

    @FXML
    private ChoiceBox<String> eventPlace;

    @FXML
    private VBox eventSellers;

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
    private DatePicker dateOfEvent;

    @FXML
    private Label wrongInput;

    @FXML
    private CheckBox seller1;

    @Autowired
    private CreateEvent createEvent;

    @Autowired
    private GetEventSellers getEventSellers;

    @Autowired
    private GetEventTypes getEventTypes;

    @Autowired
    private GetEventGenres getEventGenres;

    @Autowired
    private GetEventOrganizators getEventOrganizators;

    @Autowired
    private GetEventPlaces getEventPlaces;

    @Autowired
    private GetEventOwners getEventOwners;

    @Autowired
    private GetEventSeatTypes getEventSeatTypes;
    @FXML
    public void initialize() {
        ObservableList<String> listGenres = FXCollections.observableArrayList();
        listGenres.addAll(getEventGenres.getGenres());
        eventGenre.setItems(listGenres);

        ObservableList<String> listEventType = FXCollections.observableArrayList();
        listEventType.addAll(getEventTypes.getTypes());
        eventType.setItems(listEventType);

        eventSellers.getChildren().clear();
        List<String> listSellers = new ArrayList<>();
        listSellers.addAll(getEventSellers.getSellers());
        listSellers.forEach(seller ->{
            CheckBox seller1 = new CheckBox(seller.toString());
            eventSellers.getChildren().add(seller1);
        });

        ObservableList<String> listOrganizators = FXCollections.observableArrayList();
        listOrganizators.addAll(getEventOrganizators.getOrganizators());
        eventOrganizator.setItems(listOrganizators);

        ObservableList<String> listOwners = FXCollections.observableArrayList();
        listOwners.addAll(getEventOwners.getOwners());
        eventOwner.setItems(listOwners);

        ObservableList<String> listPlaces = FXCollections.observableArrayList();
        listPlaces.addAll(getEventPlaces.getPlaces());
        eventPlace.setItems(listPlaces);

        eventPlace.setOnAction((event) -> {
            seatTypesLabels.getChildren().clear();
            seatTypesQuantity.getChildren().clear();
            Map<String, Integer> listSeatTypes = new HashMap<>();
            listSeatTypes.putAll(getEventSeatTypes.getSeatTypes(eventPlace.getValue()));
            listSeatTypes.forEach((seatType, quantity) ->{
                Label label2 = new Label(seatType);
                seatTypesLabels.getChildren().add(label2);
                seatTypesLabels.setSpacing(20);
                TextField textField2 = new TextField();
                seatTypesQuantity.getChildren().add(textField2);
                seatTypesQuantity.setSpacing(20);
            });
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
                .maxTicketsPerPerson(Integer.valueOf(maxTicketsPerPerson.getText()))
                .eventGenre(eventGenre.getValue())
                .eventType(eventType.getValue())
                .eventOwnerUsername(eventOwner.getValue())
                .eventOrganizatorUsername(eventOrganizator.getValue())
                .eventPlace(eventPlace.getValue())
                .eventSellers(getEventSellers())
                .seatTypes(getSeatTypes())
                .dateOfEvent(dateOfEvent.getValue().atStartOfDay())
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
        List<String> sellersPressed = new ArrayList<>();
        ObservableList<Node> sellers = eventSellers.getChildren();
        sellers.forEach(checkbox ->{
            if(((CheckBox) checkbox).isSelected()){
                sellersPressed.add(((CheckBox) checkbox).getText());
            }
        });
        return sellersPressed;
    }

    private Set<SeatTypes> getSeatTypes(){
        Set<SeatTypes> seatTypesSet = new HashSet<>();
        ObservableList<Node> seatsLabels = seatTypesLabels.getChildren();
        ObservableList<Node> seatsPrice = seatTypesQuantity.getChildren();

        var ref = new Object() {
            int index = 0;
        };
        seatsLabels.forEach(seatType ->{
            SeatTypes newSeat = SeatTypes.builder()
                    .type(((Label)seatType).getText())
                    .price(Double.valueOf(((TextField)seatsPrice.get(ref.index)).getText()))
                    .build();
            seatTypesSet.add(newSeat);
            ref.index++;
        });
        return seatTypesSet;
    }

}
