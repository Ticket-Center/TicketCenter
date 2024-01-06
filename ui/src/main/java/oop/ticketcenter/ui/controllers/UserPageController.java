package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserPageController {
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
        private GridPane userGrid;

        @FXML
        private VBox vboxTicket;

        @Autowired
        private Logout logout;

        @FXML
        void createUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.REGISTER_NEW_ADMINISTRATION.getPath());
        }

        @FXML
        void deleteUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.DELETE_ADMINISTRATION.getPath());
        }

        @FXML
        void editUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.EDIT_ADMINISTRATION.getPath());
        }

        @FXML
        void logoutuser(MouseEvent event) throws IOException {
                logout.process(new LogoutInput());
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.LOGIN_FORM.getPath());
        }

        @FXML
        public void goToEvent() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
        }
}
