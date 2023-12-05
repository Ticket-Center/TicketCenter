package oop.ticketcenter.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.login.Login;
import oop.ticketcenter.core.interfaces.login.LoginInput;
import oop.ticketcenter.core.interfaces.login.LoginResult;
import oop.ticketcenter.core.services.LoginCore;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongcredentials;

    @FXML
    private Label forgotpass;

    @FXML
    private Label newuser;

    @FXML
    private Button signin;

    @Autowired
    private LoginCore login;

    @FXML
    private void signingin() {
        //final Login login;
        LoginInput input = LoginInput.builder()
                .password(password.getText())
                .username(username.getText())
                .build();
        try {
            LoginResult result = login.process(input);
            // TODO add switch form to home page
        } catch (UserNotFoundException e) {
            wrongcredentials.setText(e.getMessage());
        }
    }

    @FXML
    private void forgotpassword() throws IOException {
        SceneSwitcher.switchScene((Stage) forgotpass.getScene().getWindow() , FXMLPaths.FORGOT_PASS_FORM.getPath()); // all magic strings to be enum
    }
}
