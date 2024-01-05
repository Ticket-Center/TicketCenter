package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.core.services.helpers.GetTicketInfo;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HomePageController {

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnEvents;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lbName;

    @FXML
    private VBox navigationBar;

    @FXML
    private GridPane ticketGrid;

    @FXML
    private VBox vboxTicket;

    @Autowired
    private Logout logout;

    @Autowired
    private GetEvents getEvents;

    @Autowired
    private GetTicketInfo getTicketInfo;

    @FXML
    public void initialize() {
        visibilityForButtons();
        updateTicketGrid();
    }

    private void visibilityForButtons(){
        if(!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)){
            btnUsers.setVisible(false);
        }
        if(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)){
            btnCreate.setVisible(false);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
        }
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());
    }

    private void updateTicketGrid(){
        Set<Event> events = switch (ActiveUserSingleton.getInstance().getUserRole()) {
            case CLIENT -> getEvents.fetchAllEvents();
            case OWNER -> filterEventsForOwner();
            case SELLER -> filterEventsForSeller();
            case ORGANIZER -> filterEventsForOrganizer();
            default -> Collections.emptySet();
        };

        Set<EventSeatPrice> ticketsInfo=getTicketInfo.fetchAllEventSeatPrice();

        int row = 1;
        try {
            for (Event event : events) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(FXMLPaths.TICKET.getPath()));
                BorderPane box = fxmlLoader.load();
                TicketController ticketController = fxmlLoader.getController();

                Set<EventSeatPrice> filteredTicketInfo = ticketsInfo.stream()
                        .filter(ticket -> ticket.getEvent().getId().equals(event.getId()))
                        .collect(Collectors.toSet());

                ticketController.setData(event, filteredTicketInfo);

                ticketGrid.add(box, 0, row++);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Set<Event> filterEventsForOwner() {
        return getEvents.fetchAllEvents().stream()
                .filter(event -> event.getEventOwner().getUsername().equals(ActiveUserSingleton.getInstance().getUsername()))
                .collect(Collectors.toSet());
    }

    private Set<Event> filterEventsForSeller() {
        return Collections.emptySet();
        /*return getEvents.fetchAllEvents().stream()
                .filter(event -> event.getEventSeller().getUsername().equals(ActiveUserSingleton.getInstance().getUsername()))
                .collect(Collectors.toSet());*/
    }

    private Set<Event> filterEventsForOrganizer() {
        return getEvents.fetchAllEvents().stream()
                .filter(event -> event.getEventOrganizator().getUsername().equals(ActiveUserSingleton.getInstance().getUsername()))
                .collect(Collectors.toSet());
    }

    @FXML
    private void logoutuser() throws IOException {
        logout.process(new LogoutInput());
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow() , FXMLPaths.LOGIN_FORM.getPath());
    }
}
