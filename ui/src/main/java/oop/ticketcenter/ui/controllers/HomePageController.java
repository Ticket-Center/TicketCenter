package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.FilterByDateException;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDate;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDateInput;
import oop.ticketcenter.core.interfaces.events.filter.bydate.FilterEventByDateResult;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.core.services.helpers.GetTicketInfo;
import oop.ticketcenter.core.services.helpers.Notifications;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    private Button btnFilter;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lbName;

    @FXML
    private Label wrongInput;

    @FXML
    private VBox navigationBar;

    @FXML
    private GridPane ticketGrid;

    @FXML
    private VBox vboxTicket;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button btnSellers;

    @Autowired
    private Logout logout;

    @Autowired
    private GetEvents getEvents;

    @Autowired
    private GetTicketInfo getTicketInfo;

    @Autowired
    private FilterEventByDate filterEventByDate;

    private Set<Event> events;

    @Autowired
    private Notifications notifications;

    @FXML
    public void initialize() {
        visibilityForButtons();
        if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.SELLER) ||
                ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER)) {
            showNotifications();
        }
        events = eventsVisibleByRole();
        updateTicketGrid(events);
    }

    private void visibilityForButtons() {
        if (!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)) {
            btnUsers.setVisible(false);
        }
        if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)) {
            btnCreate.setVisible(false);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
        }
        if(!(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ORGANIZER) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER))){
            btnSellers.setVisible(false);
        }
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());
    }


    private void updateTicketGrid(Set<Event> events) {
        Set<EventSeatPrice> ticketsInfo = getTicketInfo.fetchAllEventSeatPrice();

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

                ticketController.setData(event, filteredTicketInfo, notifications);

                ticketGrid.add(box, 0, row++);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Event> eventsVisibleByRole() {
        Set<Event> events = switch (ActiveUserSingleton.getInstance().getUserRole()) {
            case OWNER -> filterEventsForOwner();
            case SELLER -> getEvents.fetchEventsBySeller(ActiveUserSingleton.getInstance().getUsername());
            case ORGANIZER -> filterEventsForOrganizer();
            default -> getEvents.fetchAllEvents();
        };
        return events;
    }

    private Set<Event> filterEventsForOwner() {
        return getEvents.fetchAllEvents().stream()
                .filter(event -> event.getEventOwner().getUsername().equals(ActiveUserSingleton.getInstance().getUsername()))
                .collect(Collectors.toSet());
    }

    private Set<Event> filterEventsForOrganizer() {
        return getEvents.fetchAllEvents().stream()
                .filter(event -> event.getEventOrganizator().getUsername().equals(ActiveUserSingleton.getInstance().getUsername()))
                .collect(Collectors.toSet());
    }

    private void showNotifications() {
        Set<String> notificationsSet = notifications.checkForNotifications(ActiveUserSingleton.getInstance().getActiveUser());
        if (notificationsSet.isEmpty()) return;
        notificationsSet.forEach(notific -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText(notific);
            a.show();
        });
    }

    @FXML
    private void logoutuser() throws IOException {
        logout.process(new LogoutInput());
        notifications.removeReceivedNotifications();
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.LOGIN_FORM.getPath());
    }

    @FXML
    private void createevent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.CREATE_EVENT.getPath());
    }

    @FXML
    private void editEvent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.EDIT_EVENT.getPath());
    }

    @FXML
    private void deleteEvent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.DELETE_EVENT.getPath());
    }

    @FXML
    public void goToUsers() throws IOException {
        SceneSwitcher.switchScene((Stage) btnUsers.getScene().getWindow(), FXMLPaths.USER_PAGE.getPath());
    }

    @FXML
    public void goToSellers() throws IOException {
        SceneSwitcher.switchScene((Stage) btnSellers.getScene().getWindow(), FXMLPaths.SELLER_PAGE.getPath());
    }

    @FXML
    void filterevents() {
        try {
            ticketGrid.getChildren().clear();
            wrongInput.setText("");
            if (endDate.getValue().isBefore(startDate.getValue())) {
                throw new FilterByDateException("End date should be in the future");
            }
            FilterEventByDateInput input = FilterEventByDateInput.builder()
                    .startDate(startDate.getValue())
                    .endDate(endDate.getValue())
                    .events(events)
                    .build();

            FilterEventByDateResult result = filterEventByDate.process(input);
            updateTicketGrid(result.getEvents());
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }
    }

    public void goToProfile() throws IOException {
        SceneSwitcher.switchScene((Stage) btnProfile.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
    }
}
