package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetAll;
import oop.ticketcenter.core.services.helpers.GetClients;
import oop.ticketcenter.core.services.helpers.GetTickets;
import oop.ticketcenter.persistence.entities.*;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.AppContext;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProfilePageController {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnEvents;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNewPassword;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSellers;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbFee;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbMOL;

    @FXML
    private Label lbMOLPhone;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbRole;

    @FXML
    private Label lbUIC;

    @FXML
    private Label lbKey;


    @FXML
    private Label lbUsername;

    @FXML
    private VBox navigationBar;

    @FXML
    private GridPane ticketGrid;

    @FXML
    private TextField txtFAddress;

    @FXML
    private TextField txtFFee;

    @FXML
    private TextField txtFLastName;

    @FXML
    private TextField txtFMOL;

    @FXML
    private TextField txtFMolPhone;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtFPhone;

    @FXML
    private TextField txtFRole;

    @FXML
    private TextField txtFUIC;

    @FXML
    private TextField txtFUsername;

    @FXML
    private VBox vboxTicket;

    @FXML
    private Label wrongInput;

    @FXML
    private TextField txtFKey;

    @Autowired
    private Logout logout;

    @Autowired
    private GetAll getAllUsers;

    @Autowired
    private GetTickets getTickets;

    @Autowired
    private GetClients getClients;

    @FXML
    private void initialize() {
        visibilityForControls();
        populateUserProfileInformation();

        Set<Ticket> allTickets = getTickets.fetchAllTickets();

        if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)) {
            String username = ActiveUserSingleton.getInstance().getUsername();
            Set<Ticket> clientTickets = filterTicketsByUsername(allTickets, username);

            updateTicketGrid(clientTickets);
        }
    }

    private Set<Ticket> filterTicketsByUsername(Set<Ticket> allTickets, String username) {
        Client client = getClients.getClients().stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (client == null) {
            return new HashSet<>();
        }

        List<UUID> ticketIds = client.getTickets().stream()
                .map(Ticket::getId)
                .toList();

        return allTickets.stream()
                .filter(ticket -> ticketIds.contains(ticket.getId()))
                .collect(Collectors.toSet());
    }

    private void visibilityForControls() {
        if (!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)) {
            btnUsers.setVisible(false);
        }
        if (!(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ORGANIZER) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER))) {
            btnSellers.setVisible(false);
        }
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());
        if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)) {
            commonForVisibility(lbAddress, txtFAddress, lbPhone, txtFPhone, lbUIC, txtFUIC, lbFee, txtFFee);
            lbMOL.setVisible(false);
            txtFMOL.setVisible(false);
            lbMOLPhone.setVisible(false);
            txtFMolPhone.setVisible(false);
            lbKey.setVisible(false);
            txtFKey.setVisible(false);
        } else if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER)) {
            lbLastName.setVisible(false);
            txtFLastName.setVisible(false);
            lbAddress.setVisible(false);
            txtFAddress.setVisible(false);
            lbPhone.setVisible(false);
            txtFPhone.setVisible(false);
            lbUIC.setVisible(false);
            txtFUIC.setVisible(false);
            lbFee.setVisible(false);
            txtFFee.setVisible(false);
            lbMOL.setVisible(false);
            txtFMOL.setVisible(false);
            lbMOLPhone.setVisible(false);
            txtFMolPhone.setVisible(false);
        } else if (ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ORGANIZER) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.SELLER)) {
            lbLastName.setVisible(false);
            txtFLastName.setVisible(false);
            lbAddress.setVisible(false);
            txtFAddress.setVisible(false);
            lbPhone.setVisible(false);
            txtFPhone.setVisible(false);
        } else {
            commonForVisibility(lbUIC, txtFUIC, lbFee, txtFFee, lbMOL, txtFMOL, lbMOLPhone, txtFMolPhone);
        }

    }

    private void commonForVisibility(Label lbUIC, TextField txtFUIC, Label lbFee, TextField txtFFee, Label lbMOL, TextField txtFMOL, Label lbMOLPhone, TextField txtFMolPhone) {
        lbUIC.setVisible(false);
        txtFUIC.setVisible(false);
        lbFee.setVisible(false);
        txtFFee.setVisible(false);
        lbMOL.setVisible(false);
        txtFMOL.setVisible(false);
        lbMOLPhone.setVisible(false);
        txtFMolPhone.setVisible(false);
    }

    private void populateUserProfileInformation() {
        String activeUsername = ActiveUserSingleton.getInstance().getUsername();
        Set<Object> allUsers = getAllUsers.getAllUsers();

        for (Object userObj : allUsers) {
            if (userObj instanceof Admin) {
                Admin user = (Admin) userObj;
                if (user.getUsername().equals(activeUsername)) {
                    txtFName.setText(user.getFirstname());
                    txtFLastName.setText(user.getLastname());
                    txtFUsername.setText(user.getUsername());
                    txtFRole.setText(user.getRole().toString());
                    break;
                }
            } else if (userObj instanceof EventOwner) {
                EventOwner user = (EventOwner) userObj;
                if (user.getUsername().equals(activeUsername)) {
                    txtFName.setText(user.getName());
                    txtFUsername.setText(user.getUsername());
                    txtFRole.setText(user.getRole().toString());
                    txtFKey.setText(user.getPasswordKey());
                    break;
                }
            } else if (userObj instanceof EventOrganizator) {
                EventOrganizator user = (EventOrganizator) userObj;
                if (checkCommonForPopulation(activeUsername, user.getUsername(), user.getName(), user.getRole(), user.getUic(), user.getFee(), user.getMol(), user.getMolPhone(), user.getPasswordKey()))
                    break;
            } else if (userObj instanceof EventSeller) {
                EventSeller user = (EventSeller) userObj;
                if (checkCommonForPopulation(activeUsername, user.getUsername(), user.getName(), user.getRole(), user.getUic(), user.getFee(), user.getMol(), user.getMolPhone(), user.getPasswordKey()))
                    break;
            } else if (userObj instanceof Client) {
                Client user = (Client) userObj;
                if (user.getUsername().equals(activeUsername)) {
                    txtFName.setText(user.getFirstname());
                    txtFLastName.setText(user.getLastname());
                    txtFUsername.setText(user.getUsername());
                    txtFRole.setText(user.getRole().toString());
                    txtFAddress.setText(user.getAddress());
                    txtFPhone.setText(user.getPhone());
                    txtFKey.setText(user.getPasswordKey());
                    break;
                }
            }

        }
    }

    private boolean checkCommonForPopulation(String activeUsername, String username, String name, Roles role, String uic, Double fee, String mol, String molPhone, String keyPassword) {
        if (username.equals(activeUsername)) {
            txtFName.setText(name);
            txtFUsername.setText(username);
            txtFRole.setText(role.toString());
            txtFUIC.setText(uic);
            txtFFee.setText(fee.toString());
            txtFMOL.setText(mol);
            txtFMolPhone.setText(molPhone);
            txtFKey.setText(keyPassword);
            return true;
        }
        return false;
    }

    @FXML
    void editUser() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEdit.getScene().getWindow(), FXMLPaths.EDIT_PROFILE.getPath());
    }

    @FXML
    void newPassword() throws IOException {
        SceneSwitcher.switchScene((Stage) btnSellers.getScene().getWindow(), FXMLPaths.NEW_PASSWORD.getPath());
    }

    @FXML
    void goToSellers() throws IOException {
        SceneSwitcher.switchScene((Stage) btnSellers.getScene().getWindow(), FXMLPaths.SELLER_PAGE.getPath());
    }

    @FXML
    void goToUsers() throws IOException {
        SceneSwitcher.switchScene((Stage) btnUsers.getScene().getWindow(), FXMLPaths.USER_PAGE.getPath());
    }

    @FXML
    void logoutuser() throws IOException {
        logout.process(new LogoutInput());
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.LOGIN_FORM.getPath());
    }

    @FXML
    public void goToEvent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow(), FXMLPaths.HOME_PAGE.getPath());
    }

    private void updateTicketGrid(Set<Ticket> clientTickets) {
        int row = 1;
        try {
            for (Ticket ticket : clientTickets) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(FXMLPaths.SOLD_TICKET.getPath()));
                fxmlLoader.setControllerFactory(AppContext.getInstance().getContext()::getBean);
                BorderPane box = fxmlLoader.load();
                SoldTicketController soldTicketController = fxmlLoader.getController();

                soldTicketController.setData(ticket);
                soldTicketController.setTicketId(ticket.getId());

                ticketGrid.add(box, 0, row++);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
