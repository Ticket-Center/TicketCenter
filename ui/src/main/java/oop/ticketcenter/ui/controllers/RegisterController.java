package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.register.RegisterInput;
import oop.ticketcenter.core.interfaces.register.RegisterResult;
import oop.ticketcenter.core.services.RegisterCore;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbConfirmPassword;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbKeyFP;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbPassword;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbRegistrationForm;

    @FXML
    private Label lbUsername;

    @FXML
    private PasswordField passFConfirmPassword;

    @FXML
    private PasswordField passFPassword;

    @FXML
    private TextField txtFAddress;

    @FXML
    private TextField txtFFirstName;

    @FXML
    private TextField txtFLastName;

    @FXML
    private TextField txtFPhone;

    @FXML
    private TextField txtFUsername;

    @FXML
    private TextField txtKeyFP;

    @FXML
    private Label lbResult;

    @Autowired
    private RegisterCore register;

    @FXML
    private void register() throws IOException {
        RegisterInput input = RegisterInput.builder()
                .firstName(txtFFirstName.getText())
                .lastName(txtFLastName.getText())
                .username(txtFUsername.getText())
                .password(passFPassword.getText())
                .confirmPassword(passFConfirmPassword.getText())
                .phone(txtFPhone.getText())
                .address(txtFAddress.getText())
                .passwordKey(txtKeyFP.getText())
                .build();
        RegisterResult result = register.process(input);
        lbResult.setText(result.getStr());
    }

    @FXML
    private void backToLogIn() throws IOException {
        SceneSwitcher.switchScene((Stage) btnCancel.getScene().getWindow() , FXMLPaths.LOGIN_FORM.getPath());
    }
}
