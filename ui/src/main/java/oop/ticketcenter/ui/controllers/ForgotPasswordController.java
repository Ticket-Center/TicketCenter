package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oop.ticketcenter.core.interfaces.forgotpassword.ForgotPasswordInput;
import oop.ticketcenter.core.interfaces.forgotpassword.ForgotPasswordResult;
import oop.ticketcenter.core.services.ForgotPasswordCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordController {

    @FXML
    private TextField forgotpassword;

    @FXML
    private Button generate;

    @FXML
    private TextField newpass;

    @Autowired
    private ForgotPasswordCore forgotPasswordCore;
    @FXML
    private void generatetemppass(){
        ForgotPasswordInput input = ForgotPasswordInput.builder()
                .passwordkey(forgotpassword.getText())
                .build();
        ForgotPasswordResult result = forgotPasswordCore.process(input);
        newpass.setText(result.getNewPassword());
    }
}
