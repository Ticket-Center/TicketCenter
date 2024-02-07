package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.changePassword.ChangePasswordInput;
import oop.ticketcenter.core.interfaces.users.changePassword.ChangePasswordResult;
import oop.ticketcenter.core.services.implementations.ChangePasswordCore;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewPasswordController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonChange;

    @FXML
    private Label lbConfirmNewPassword;

    @FXML
    private Label lbCurrentPassword;

    @FXML
    private Label lbNewPassword;

    @FXML
    private PasswordField txtPFConfirmPassword;

    @FXML
    private PasswordField txtPFCurrentPassword;

    @FXML
    private PasswordField txtPFNewPassword;

    @FXML
    private Label wrongInput;

    @Autowired
    private ChangePasswordCore changePasswordCore;


    @FXML
    void cancel() throws IOException {
        SceneSwitcher.switchScene((Stage) buttonBack.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
    }

    public void changePassword() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        ChangePasswordInput input =ChangePasswordInput.builder()
                .currentPassword(txtPFCurrentPassword.getText())
                .newPassword(txtPFNewPassword.getText())
                .confirmPassword(txtPFConfirmPassword.getText())
                .build();
        try{
            ChangePasswordResult result=changePasswordCore.process(input);
            wrongInput.setText("Successfully changed password");
            logger.log(Level.INFO,"Successfully changed password");
        }catch(RuntimeException e){
            wrongInput.setText(e.getMessage());
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
