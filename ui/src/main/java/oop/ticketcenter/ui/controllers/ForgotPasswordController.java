package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.forgotpassword.ForgotPasswordInput;
import oop.ticketcenter.core.interfaces.users.forgotpassword.ForgotPasswordResult;
import oop.ticketcenter.core.services.implementations.ForgotPasswordCore;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ForgotPasswordController {

    @FXML
    private TextField forgotpassword;

    @FXML
    private TextField username;

    @FXML
    private Button generate;


    @FXML
    private Button back;

    @FXML
    private TextField newpass;

    @Autowired
    private ForgotPasswordCore forgotPasswordCore;
    @FXML
    private void generatetemppass(){
        Logger logger = Logger.getLogger(this.getClass().getName());
        ForgotPasswordInput input = ForgotPasswordInput.builder()
                .username(username.getText())
                .passwordkey(forgotpassword.getText())
                .build();
        ForgotPasswordResult result = forgotPasswordCore.process(input);
        newpass.setText(result.getNewPassword());
        logger.log(Level.INFO, "Successful generated new password");
    }

    @FXML
    void backToLogin() throws IOException {
        SceneSwitcher.switchScene((Stage) newpass.getScene().getWindow() , FXMLPaths.LOGIN_FORM.getPath()); // all magic strings to be enum
    }
}
