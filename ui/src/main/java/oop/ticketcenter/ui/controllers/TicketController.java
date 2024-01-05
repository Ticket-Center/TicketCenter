package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.enums.Roles;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class TicketController {
    @FXML
    private BorderPane Ticket;

    @FXML
    private Button btnBuy;

    @FXML
    private ComboBox<String> cbBoxTicket;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbGenre;

    @FXML
    private Label lbQuantity;

    @FXML
    private Label lbSoldTickets;

    @FXML
    private AnchorPane lbTicket;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbType;

    @FXML
    private Spinner<?> spinnerQuantity;

    @FXML
    private TextField txtFDate;

    @FXML
    private TextField txtFGenre;

    @FXML
    private TextField txtFTitle;

    @FXML
    private TextField txtFType;
    @FXML
    private TextField txtFQuantity;
    @FXML
    private TextField txtFPlace;

    public void setData(Event event, Set<EventSeatPrice> ticketsInfo){
        int quantity=0;
        txtFTitle.setText(String.valueOf(event.getTitle()));
        txtFType.setText(event.getEventType().getName());
        txtFGenre.setText(event.getEventGenre().getName());
        txtFPlace.setText(event.getEventPlace().getName());
        txtFDate.setText(String.valueOf(event.getDate()));

        ObservableList<String> ticketOptions = FXCollections.observableArrayList();
        for (EventSeatPrice ticket : ticketsInfo) {
            String option = ticket.getPlaceSeatType().getSeatType().getType() + ", " + ticket.getPrice();
            ticketOptions.add(option);
            quantity+=ticket.getPlaceSeatType().getQuantity();
        }
        lbSoldTickets.setText("Sold tickets:\n"+ event.getSoldTickets() +"/ "+quantity);
        cbBoxTicket.setItems(ticketOptions);
    }

    @FXML
    public void initialize() {
        if(!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)){
            btnBuy.setVisible(false);
        }
    }
}
