package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.ActiveUserSingleton;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomePageController {

    @FXML
    private Button btnEvents;

    @FXML
    private Button btnHome;

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

    @Autowired
    private Logout logout;

    @FXML
    public void initialize() {
        if(!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)){
            btnUsers.setVisible(false);
        }
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());
    }


    @FXML
    private void logoutuser() throws IOException {
        logout.process(new LogoutInput());
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow() , FXMLPaths.LOGIN_FORM.getPath());
    }
}
