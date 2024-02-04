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
import oop.ticketcenter.core.services.helpers.*;
import oop.ticketcenter.persistence.entities.Client;
import oop.ticketcenter.persistence.entities.EventOrganizator;
import oop.ticketcenter.persistence.entities.EventOwner;
import oop.ticketcenter.persistence.entities.EventSeller;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

@Component
public class UserPageController {
        @FXML
        private Button btnCreate;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnEdit;

        @FXML
        private Button btnEvents;

        @FXML
        private Button btnLogOut;

        @FXML
        private Button btnProfile;

        @FXML
        private Button btnUsers;

        @FXML
        private Label lbName;

        @FXML
        private VBox navigationBar;

        @FXML
        private GridPane userGrid;

        @FXML
        private VBox vboxTicket;

        @Autowired
        private Logout logout;

        @Autowired
        private GetEventOwners eventOwners;

        @Autowired
        private GetEventOrganizators eventOrganizators;

        @Autowired
        private GetEventSellers eventSellers;

        @Autowired
        private GetClients clients;
        @FXML
        void createUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.REGISTER_NEW_ADMINISTRATION.getPath());
        }

        @FXML
        void deleteUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.DELETE_ADMINISTRATION.getPath());
        }

        @FXML
        void editUser() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.EDIT_ADMINISTRATION.getPath());
        }

        @FXML
        void logoutuser(MouseEvent event) throws IOException {
                logout.process(new LogoutInput());
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.LOGIN_FORM.getPath());
        }

        @FXML
        public void goToEvent() throws IOException {
                SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
        }
        @FXML
        public void initialize() {
                loadUsers();
                lbName.setText(ActiveUserSingleton.getInstance().getUsername());
        }

        private void loadUsers() {
                loadEntities(eventOwners.getAllOwners(), EventOwner::getName, EventOwner::getUsername, (EventOwner owner) -> owner.getRole().name());
                loadEntities(eventOrganizators.getAllOrganizators(), EventOrganizator::getName, EventOrganizator::getUsername, (EventOrganizator organizator) -> organizator.getRole().name());
                loadEntities(eventSellers.getAllSellers(), EventSeller::getName, EventSeller::getUsername, (EventSeller seller) -> seller.getRole().name());
                loadEntities(clients.getClients(), Client::getFirstname, Client::getUsername, (Client client) -> client.getRole().name());
        }


        private <T> void loadEntities(Set<T> entities, Function<T, String> nameExtractor, Function<T, String> usernameExtractor, Function<T, String> roleExtractor) {
                int row = userGrid.getRowCount();

                for (T entity : entities) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPaths.USER.getPath()));
                        try {
                                BorderPane box = fxmlLoader.load();
                                UserController userController = fxmlLoader.getController();

                                userController.setData(nameExtractor.apply(entity), usernameExtractor.apply(entity), roleExtractor.apply(entity));

                                if (entity instanceof EventSeller) {
                                        userController.setDataSeller((EventSeller) entity);
                                }

                                userGrid.add(box, 0, row++);
                                GridPane.setMargin(box, new Insets(10));
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
        public void goToProfile() throws IOException {
                SceneSwitcher.switchScene((Stage) btnProfile.getScene().getWindow(), FXMLPaths.PROFILE_PAGE.getPath());
        }
}
