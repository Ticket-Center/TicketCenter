package oop.ticketcenter.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.rating.RatingSellerInput;
import oop.ticketcenter.core.interfaces.users.rating.RatingSellerResult;
import oop.ticketcenter.core.services.implementations.EditRatingCore;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.enums.Rating;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class EditRatingSellerController {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonEdit;

    @FXML
    private ChoiceBox<Rating> cbRating;

    @FXML
    private ChoiceBox<String> cbSeller;

    @FXML
    private Label lbEditRating;

    @FXML
    private Label lbRating;

    @FXML
    private Label lbSeller;

    @FXML
    private Label wrongInput;

    @Autowired
    private EditRatingCore ratingSeller;
    @FXML
    public void initialize() {
        ObservableList<Rating> ratings = FXCollections.observableArrayList(Rating.values());
        cbRating.setItems(ratings);
    }
    @FXML
    void editRating() {
        RatingSellerInput input= RatingSellerInput.builder()
                .sellerName(cbSeller.getValue())
                .rating(cbRating.getValue())
                .build();
        try {
            RatingSellerResult result= ratingSeller.process(input);
            wrongInput.setText("Rating updated successfully");
        }catch (RuntimeException e){
            wrongInput.setText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    public void cancel() throws IOException {
        SceneSwitcher.switchScene((Stage) buttonBack.getScene().getWindow(), FXMLPaths.SELLER_PAGE.getPath());
    }

    public void setSellers(Set<EventSeller> sellers) {
        ObservableList<String> sellerNames = FXCollections.observableArrayList();
        for (EventSeller seller : sellers) {
            sellerNames.add(seller.getUsername());
        }
        cbSeller.setItems(sellerNames);
    }
}
