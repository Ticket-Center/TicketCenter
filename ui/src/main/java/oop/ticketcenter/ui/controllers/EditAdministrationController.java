package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministration;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.edit.EditAdministrationResult;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministration;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationResult;
import oop.ticketcenter.core.interfaces.events.edit.EditEvent;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.EditUserHelper;
import oop.ticketcenter.core.services.helpers.EditUserModel;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class EditAdministrationController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField fee;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbRegistrationForm;

    @FXML
    private Label lbResult;

    @FXML
    private Label lbUsername;

    @FXML
    private Label lblFee;

    @FXML
    private Label lblMol;

    @FXML
    private TextField mol;

    @FXML
    private TextField molPhone;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @Autowired
    private EditUserHelper editUserHelper;

    @Autowired
    private EditAdministration editAdministration;

    @FXML
    public void initialize() {
        if (!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)){
            username.setText(ActiveUserSingleton.getInstance().getUsername());
        }
        mol.setVisible(false);
        lblMol.setVisible(false);
        molPhone.setVisible(false);
        lbPhone.setVisible(false);
        fee.setVisible(false);
        lblFee.setVisible(false);
    }

    private String editAdministrationRole = "";
    @FXML
    private void register() throws IOException {
        EditAdministrationInput administrationInput = EditAdministrationInput.builder()
                .name(name.getText())
                .username(username.getText())
                .fee(Double.valueOf(fee.getText()))
                .molPhone(molPhone.getText())
                .mol(mol.getText())
                .role(editAdministrationRole)
                .build();
        try {
            EditAdministrationResult result = editAdministration.process(administrationInput);
            lbResult.setText(result.getStr());
        } catch (UserAlreadyExistsException | IncorrectInputException e) {
            lbResult.setText(e.getMessage());
        }
    }

    @FXML
    private void backToLogIn() throws IOException {
        SceneSwitcher.switchScene((Stage) btnCancel.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    @FXML
    private void usernameentered(){
        EditUserModel model = editUserHelper.getEditUser(username.getText());
        name.setText(model.getName());
        editAdministrationRole = model.getRole();
       if(model.getFee() != null && model.getMol() != null && model.getMolPhone() != null){
           mol.setVisible(true);
           lblMol.setVisible(true);
           molPhone.setVisible(true);
           lbPhone.setVisible(true);
           fee.setVisible(true);
           lblFee.setVisible(true);

           mol.setText(model.getMol());
           molPhone.setText(model.getMolPhone());
           fee.setText(String.valueOf(model.getFee()));
       }
    }
}
