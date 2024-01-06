package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.ticketcenter.persistence.entities.EventSeller;
import org.springframework.stereotype.Component;

@Component
public class UserController {
    @FXML
    private Label lbName;

    @FXML
    private Label lbRating;

    @FXML
    private Label lbRole;

    @FXML
    private Label lbUsername;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtFRating;

    @FXML
    private TextField txtFRole;

    @FXML
    private TextField txtFUsername;

    public void setData(String name, String username, String role) {
        txtFName.setText(name);
        txtFUsername.setText(username);
        txtFRole.setText(role);
        lbRating.setVisible(false);
        txtFRating.setVisible(false);
    }

    public void setDataSeller(EventSeller eventSeller) {
        setData(eventSeller.getName(), eventSeller.getUsername(), eventSeller.getRole().name());
        lbRating.setVisible(true);
        txtFRating.setVisible(true);
        txtFRating.setText(eventSeller.getRating().toString());
    }
}
