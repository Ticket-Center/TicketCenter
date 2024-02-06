package oop.ticketcenter.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketInput;
import oop.ticketcenter.core.interfaces.tickets.free.FreeTicketResult;
import oop.ticketcenter.core.services.implementations.FreeTicketCore;
import oop.ticketcenter.persistence.entities.Ticket;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import oop.ticketcenter.ui.helpers.SoldTicketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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


    public void setData(Ticket ticket){
        txtFTitle.setText(ticket.getEventSeatPrice().getEvent().getTitle());
        txtFDate.setText(String.valueOf(ticket.getEventSeatPrice().getEvent().getDate()));
        txtFPlace.setText(ticket.getEventSeatPrice().getPlaceSeatType().getEventPlace().getName());
        txtFType.setText(ticket.getEventSeatPrice().getPlaceSeatType().getSeatType().getType());
        txtFPrice.setText(ticket.getEventSeatPrice().getPrice().toString());
    }

    public void freeTicket(ActionEvent event) throws IOException {
        Button clickedButton=(Button) event.getSource();
        SoldTicketData soldTicketData=(SoldTicketData) clickedButton.getUserData();
        if(soldTicketData!=null){
            FreeTicketInput input=FreeTicketInput.builder()
                    .ticketId(soldTicketData.getTicket().getId())
                    .build();
            try{
                FreeTicketResult result=freeTicketCore.process(input);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Ticket free successfully.");
            }catch (RuntimeException e){
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to free ticket: " + e.getMessage());
                e.printStackTrace();
            }
        }
        else throw new RuntimeException("No data for sold tickets");
        SceneSwitcher.switchScene((Stage) btnFree.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

