package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.ticketcenter.core.exceptions.EmptyFieldException;
import oop.ticketcenter.core.interfaces.users.edit.EditProfileInput;
import oop.ticketcenter.core.interfaces.users.edit.EditProfileResult;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.implementations.EditProfileCore;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EditProfileController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEdit;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbEditProfile;

    @FXML
    private Label lbKeyPassword;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPhone;

    @FXML
    private TextField txtFAddress;

    @FXML
    private TextField txtFKeyPassword;

    @FXML
    private TextField txtFLastName;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtFPhone;

    @FXML
    private Label wrongInput;

    @Autowired
    private EditProfileCore editProfileCore;
    @FXML
    private void initialize(){
        if(!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)){
            lbAddress.setVisible(false);
            lbPhone.setVisible(false);
            txtFAddress.setVisible(false);
            txtFPhone.setVisible(false);
        }
        if(!(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT))){
            lbLastName.setVisible(false);
            txtFLastName.setVisible(false);
        }
        if(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)){
            lbKeyPassword.setVisible(false);
            txtFKeyPassword.setVisible(false);
        }
    }
    @FXML
    void cancel() throws IOException {
        SceneSwitcher.switchScene((Stage) buttonBack.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
    }

    public void editProfile() {
        EditProfileInput input=EditProfileInput.builder()
                .firstName(txtFName.getText())
                .lastName(txtFLastName.getText())
                .passwordKey(txtFKeyPassword.getText())
                .address(txtFAddress.getText())
                .phone(txtFPhone.getText())
                .build();
        if(input.getFirstName().isEmpty() && input.getLastName().isEmpty() && input.getPasswordKey().isEmpty() && input.getAddress().isEmpty() && input.getPhone().isEmpty()){
            wrongInput.setText("All fields are empty");
            throw new EmptyFieldException(wrongInput.toString());
        }
        try {
            EditProfileResult result=editProfileCore.process(input);
            wrongInput.setText("Successfully changed profile");
        }catch (RuntimeException e){
            wrongInput.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
