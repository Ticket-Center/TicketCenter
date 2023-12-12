package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.IncorrectInputException;
import oop.ticketcenter.core.exceptions.UserNotFoundException;
import oop.ticketcenter.core.interfaces.users.login.LoginInput;
import oop.ticketcenter.core.interfaces.users.login.LoginResult;
import oop.ticketcenter.core.services.ActiveUserSingleton;
import oop.ticketcenter.core.services.LoginCore;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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


    @FXML
    private AnchorPane anchor;

    @Autowired
    private LoginCore login;

    @FXML
    private void signingin() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        LoginInput input = LoginInput.builder()
                .password(password.getText())
                .username(username.getText())
                .build();
        wrongcredentials.setText("");
        try {
            LoginResult result = login.process(input);
            SceneSwitcher.switchScene((Stage) forgotpass.getScene().getWindow() , FXMLPaths.HOME_PAGE.getPath());
            logger.log(Level.INFO, "Successful logging in");
        } catch (UserNotFoundException | IncorrectInputException e) {
            wrongcredentials.setText(e.getMessage());
            logger.log(Level.ERROR, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void forgotpassword() throws IOException {
        SceneSwitcher.switchScene((Stage) forgotpass.getScene().getWindow() , FXMLPaths.FORGOT_PASS_FORM.getPath()); // all magic strings to be enum
    }
}
