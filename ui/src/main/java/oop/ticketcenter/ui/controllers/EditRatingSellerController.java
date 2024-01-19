package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EditRatingSellerController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEdit;

    @FXML
    private ChoiceBox<?> cbRating;

    @FXML
    private ChoiceBox<?> cbSeller;

    @FXML
    private Label lbEditRating;

    @FXML
    private Label lbRating;

    @FXML
    private Label lbSeller;

    @FXML
    private Label wrongInput;
    @FXML
    public void editRating(MouseEvent mouseEvent) {
    }
    @FXML
    public void cancel(MouseEvent mouseEvent) throws IOException {
        SceneSwitcher.switchScene((Stage) buttonBack.getScene().getWindow(), FXMLPaths.SELLER_PAGE.getPath());
    }
}
