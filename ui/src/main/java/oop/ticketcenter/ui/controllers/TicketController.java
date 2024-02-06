package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import oop.ticketcenter.core.interfaces.tickets.buy.BuyTicketInput;
import oop.ticketcenter.core.interfaces.tickets.buy.BuyTicketResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.Notifications;
import oop.ticketcenter.core.services.implementations.BuyTicketCore;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.entities.EventSeatPrice;
import oop.ticketcenter.persistence.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
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
    @FXML
    private Label lbResult;

    @Autowired
    private BuyTicketCore buyTicketCore;

    public void setData(Event event, Set<EventSeatPrice> ticketsInfo, Notifications notifications){
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
        notifications.quantityNotifications(quantity, event);
        lbSoldTickets.setText("Sold tickets:\n"+ event.getSoldTickets() +"/ "+quantity);
        cbBoxTicket.setItems(ticketOptions);
    }

    @FXML
    public void initialize() {
        if (!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)) {
            btnBuy.setVisible(false);
        }
    }

    @FXML
    public void buyTicket() {
        BuyTicketInput input = BuyTicketInput.builder()
                .eventTitle(txtFTitle.getText())
                .numberTickets(Integer.valueOf(txtFQuantity.getText()))
                .ticketType(extractTicketType(cbBoxTicket.getValue()))
                .build();
        try {
            BuyTicketResult result = buyTicketCore.process(input);
            lbResult.setText("Successfully bought ticket");
        } catch (RuntimeException e) {
            lbResult.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    private String extractTicketType(String ticketType) {
        if (ticketType != null && ticketType.contains(",")) {
            return ticketType.substring(0, ticketType.indexOf(",")).trim();
        } else {
            throw new IllegalArgumentException("Invalid ticket type format.");
        }
    }

}
