package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministration;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.delete.DeleteAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministration;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationResult;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventInput;
import oop.ticketcenter.core.interfaces.events.delete.DeleteEventResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.EditUserHelper;
import oop.ticketcenter.core.services.helpers.EditUserModel;
import oop.ticketcenter.core.services.helpers.GetAllUsers;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeleteAdministrationController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonDelete;

    @FXML
    private ChoiceBox<String> user;

    @FXML
    private Label wrongInput;

    @Autowired
    private DeleteAdministration deleteAdministration;

    @Autowired
    private GetAllUsers getAllUsers;

    @FXML
    private void initialize() {
        try {
            ObservableList<String> listEvents = FXCollections.observableArrayList();
            listEvents.addAll(getAllUsers.getUsers());
            user.setItems(listEvents);
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }
    }

    @FXML
    void back() throws IOException {
        SceneSwitcher.switchScene((Stage) wrongInput.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    void deleteuser() {
        DeleteAdministrationInput input = DeleteAdministrationInput.builder()
                .username(user.getValue())
                .build();
        wrongInput.setText("");
        try {
            DeleteAdministrationResult result = deleteAdministration.process(input);
            wrongInput.setText("Event deleted successfully");
            buttonDelete.setDisable(true);
        } catch (RuntimeException e) {
            wrongInput.setText(e.getMessage());
        }

    }
}
