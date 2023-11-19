package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import oop.ticketcenter.core.interfaces.login.Login;
import oop.ticketcenter.core.interfaces.login.LoginInput;
import oop.ticketcenter.core.interfaces.login.LoginResult;
import oop.ticketcenter.core.services.LoginCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class LoginController{

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

    private LoginCore login;

    @FXML
    private void signingin(){
        //final Login login;
        LoginInput input = LoginInput.builder()
                .password(password.getText())
                .username(username.getText())
                .build();
        LoginResult result = login.process(input);
        wrongcredentials.setText(result.getUserId().toString());
    }
}
