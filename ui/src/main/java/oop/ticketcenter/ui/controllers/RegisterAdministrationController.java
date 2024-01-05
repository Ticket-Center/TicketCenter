package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserAlreadyExistsException;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministration;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationInput;
import oop.ticketcenter.core.interfaces.administration.register.RegisterAdministrationResult;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class RegisterAdministrationController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField fee;

    @FXML
    private Label lbConfirmPassword;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbPassword;

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
    private Label lblUIC;

    @FXML
    private TextField mol;

    @FXML
    private TextField molPhone;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passFConfirmPassword;

    @FXML
    private PasswordField passFPassword;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private TextField uic;

    @FXML
    private TextField username;
    @FXML
    private TextField txtKeyFP;


    @Autowired
    private RegisterAdministration register;

    @FXML
    public void initialize() {
        ObservableList<String> listRoles = FXCollections.observableArrayList();
        listRoles.addAll(Arrays.stream(Roles.values()).map(value -> value.name()).toList());
        role.setItems(listRoles);

        uic.setVisible(false);
        lblUIC.setVisible(false);
        mol.setVisible(false);
        lblMol.setVisible(false);
        molPhone.setVisible(false);
        lbPhone.setVisible(false);
        fee.setVisible(false);
        lblFee.setVisible(false);

        role.setOnAction((event) -> {
            if (!role.getValue().equals(Roles.OWNER.name())) {
                uic.setVisible(true);
                lblUIC.setVisible(true);
                mol.setVisible(true);
                lblMol.setVisible(true);
                molPhone.setVisible(true);
                lbPhone.setVisible(true);
                fee.setVisible(true);
                lblFee.setVisible(true);
            }
        });
    }

    @FXML
    private void register() throws IOException {
        RegisterAdministrationInput administrationInput = RegisterAdministrationInput.builder()
                .name(name.getText())
                .username(username.getText())
                .password(passFPassword.getText())
                .confirmPassword(passFConfirmPassword.getText())
                .passwordKey(txtKeyFP.getText())
                .fee(Double.valueOf(fee.getText()))
                .uic(uic.getText())
                .molPhone(molPhone.getText())
                .mol(mol.getText())
                .role(role.getValue())
                .build();
        try {
            RegisterAdministrationResult result = register.process(administrationInput);
            lbResult.setText(result.getStr());
        } catch (UserAlreadyExistsException | IncorrectInputException e) {
            lbResult.setText(e.getMessage());
        }
    }

    @FXML
    private void backToLogIn() throws IOException {
        SceneSwitcher.switchScene((Stage) btnCancel.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }
}
