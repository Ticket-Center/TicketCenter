package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import oop.ticketcenter.persistence.entities.Event;

public class TicketController {
    @FXML
    private BorderPane Ticket;

    @FXML
    private Button btnBuy;

    @FXML
    private ComboBox<?> cbBoxTicket;

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

    /*public void setData(Ticket ticket){
        txtFTitle.setText(ticket.getTitle());
        txtFType.setText(ticket.getType());
        txtFGenre.setText(ticket.getGenre());
        txtFDate.setText(ticket.getDate());
    }*/

    public void setData(Event event){
        txtFTitle.setText(String.valueOf(event.getTitle()));
        txtFType.setText(String.valueOf(event.getEventType()));
        txtFGenre.setText(String.valueOf(event.getEventGenre()));
        txtFDate.setText(String.valueOf(event.getDate()));
    }
}
