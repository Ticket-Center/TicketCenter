package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetEventSellers;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Component
public class SellerPageController {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnEvents;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSellers;

    @FXML
    private Label lbName;

    @FXML
    private VBox navigationBar;

    @FXML
    private GridPane sellerGrid;;

    @FXML
    private VBox vboxTicket;

    @Autowired
    private Logout logout;

    @Autowired
    private GetEventSellers eventSellers;

    @Autowired
    private GetEvents getEvents;

    //private Set<EventSeller> retrievedSellers;
    @FXML
    void editSeller() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.EDIT_RATING_SELLER.getPath());
    }

    @FXML
    public void goToEvent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());

    }

    @FXML
    void logoutuser(MouseEvent event) throws IOException {
        logout.process(new LogoutInput());
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.LOGIN_FORM.getPath());
    }

    @FXML
    public void initialize() {
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());

        Set<EventSeller> sellers;
        Set<UUID> eventsId;

        if(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER)){
            eventsId=getEvents.fetchEventIdByOwner(ActiveUserSingleton.getInstance().getUsername());
        }
        else{
            eventsId=getEvents.fetchEventIdByOrganizator(ActiveUserSingleton.getInstance().getUsername());
        }
        sellers=eventSellers.getSellersByEventIds(eventsId);
        updateSellerGrid(sellers);
        //retrievedSellers=sellers;
    }

    private void updateSellerGrid(Set<EventSeller> sellers){
        Logger logger = Logger.getLogger(this.getClass().getName());
        int row=1;

        try {
            for (EventSeller seller : sellers) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPaths.USER.getPath()));
                BorderPane box = fxmlLoader.load();
                UserController userController = fxmlLoader.getController();

                userController.setDataSeller(seller);

                sellerGrid.add(box, 0, row++);
                GridPane.setMargin(box, new Insets(10));
                logger.log(Level.INFO, "Seller displayed successfully");
            }
        } catch (IOException e) {
            logger.log(Level.ERROR,e.getMessage());
            e.printStackTrace();
        }
    }

    public void goToProfile() throws IOException {
        SceneSwitcher.switchScene((Stage) btnProfile.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
    }
}
