package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import lombok.Setter;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketInput;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketResult;
import oop.ticketcenter.core.services.implementations.FreeTicketCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oop.ticketcenter.persistence.entities.Ticket;

import java.util.UUID;

@Component
public class SoldTicketController {

    @FXML
    private BorderPane Ticket;

    @FXML
    private Button btnFree;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbPlace;

    @FXML
    private Label lbPrice;

    @FXML
    private AnchorPane lbTicket;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbType;

    @FXML
    private TextField txtFDate;

    @FXML
    private TextField txtFPlace;

    @FXML
    private TextField txtFPrice;

    @FXML
    private TextField txtFTitle;

    @FXML
    private TextField txtFType;

    @FXML
    private Label lbResult;

    @Autowired
    private FreeTicketCore freeTicketCore;

    @Getter
    @Setter
    private UUID ticketId;


    public void setData(Ticket ticket){
        txtFTitle.setText(ticket.getEventSeatPrice().getEvent().getTitle());
        txtFDate.setText(String.valueOf(ticket.getEventSeatPrice().getEvent().getDate()));
        txtFPlace.setText(ticket.getEventSeatPrice().getPlaceSeatType().getEventPlace().getName());
        txtFType.setText(ticket.getEventSeatPrice().getPlaceSeatType().getSeatType().getType());
        txtFPrice.setText(ticket.getEventSeatPrice().getPrice().toString());
    }

    public void freeTicket() {
        FreeTicketInput input=FreeTicketInput.builder()
                .ticketId(ticketId)
                .build();

        try{
            FreeTicketResult result=freeTicketCore.process(input);
            lbResult.setText("Successfully free ticket");
        }catch (RuntimeException e){
            lbResult.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}

