package oop.ticketcenter.ui.controllers;

import javafx.event.ActionEvent;
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
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.TicketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketController {
    @FXML
    private BorderPane Ticket;

    @FXML
    private Button btnBuy;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbGenre;

    @FXML
    private Label lbPrice;

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
    private TextField txtFPrice;
    @FXML
    private TextField txtFPlace;
    @FXML
    private TextField txtFTypeSeat;
    @FXML
    private Label lbResult;

    @Autowired
    private BuyTicketCore buyTicketCore;

    public void setData(Event event, String seatType, Double price,Integer soldTickets, Integer allTicketsForSeatType, Notifications notifications){
        int quantity=0;
        txtFTitle.setText(String.valueOf(event.getTitle()));
        txtFType.setText(event.getEventType().getName());
        txtFGenre.setText(event.getEventGenre().getName());
        txtFPlace.setText(event.getEventPlace().getName());
        txtFDate.setText(String.valueOf(event.getDate()));
        txtFTypeSeat.setText(seatType);
        txtFPrice.setText(price.toString());

        notifications.quantityNotifications(quantity, event);
        //lbSoldTickets.setText("Sold tickets:\n"+ soldTickets +"/ "+allTicketsForSeatType);
    }

    @FXML
    public void initialize() {
        if (!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)) {
            btnBuy.setVisible(false);
        }
    }

    @FXML
    public void buyTicket(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        TicketData ticketData = (TicketData) clickedButton.getUserData();
        if (ticketData != null) {
            BuyTicketInput input = BuyTicketInput.builder()
                    .eventTitle(ticketData.getEvent().getTitle())
                    .ticketType(ticketData.getSeatType())
                    .numberTickets(1)
                    .build();
            try {
                BuyTicketResult result = buyTicketCore.process(input);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Ticket bought successfully.");
            } catch (RuntimeException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to buy ticket: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
