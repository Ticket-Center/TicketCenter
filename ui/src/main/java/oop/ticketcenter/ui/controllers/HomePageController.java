package oop.ticketcenter.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.ticketcenter.core.interfaces.users.logout.Logout;
import oop.ticketcenter.core.interfaces.users.logout.LogoutInput;
import oop.ticketcenter.core.services.helpers.ActiveUserSingleton;
import oop.ticketcenter.core.services.helpers.GetEvents;
import oop.ticketcenter.persistence.entities.Event;
import oop.ticketcenter.persistence.enums.Roles;
import oop.ticketcenter.ui.helpers.FXMLPaths;
import oop.ticketcenter.ui.helpers.SceneSwitcher;
import oop.ticketcenter.ui.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class HomePageController {

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
    private GridPane ticketGrid;

    private List<Ticket> tickets;

    @FXML
    private VBox vboxTicket;

    @Autowired
    private Logout logout;

    @Autowired
    private GetEvents getEvents;

    @FXML
    public void initialize() {
        if(!ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN)){
            btnUsers.setVisible(false);
        }
        //if(!(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ADMIN) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.ORGANIZER)||ActiveUserSingleton.getInstance().getUserRole().equals(Roles.OWNER) || ActiveUserSingleton.getInstance().getUserRole().equals(Roles.SELLER))){
        if(ActiveUserSingleton.getInstance().getUserRole().equals(Roles.CLIENT)){
            btnCreate.setVisible(false);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
        }
        lbName.setText(ActiveUserSingleton.getInstance().getUsername());

        //Set<Event> events = getEvents.getEvents();
        Set<Event> events =getEvents.fetchAllEvents();

        int row = 1;
        try {
            for (Event event : events) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/ticket.fxml"));
                BorderPane box = fxmlLoader.load();
                TicketController ticketController = fxmlLoader.getController();

                ticketController.setData(event);

                ticketGrid.add(box, 0, row++);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*tickets=new ArrayList<>(tickets());
        int row=1;
        try{
            for (int i=0; i<tickets.size();i++){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxmls/ticket.fxml"));
                BorderPane box = fxmlLoader.load();
                TicketController ticketController=fxmlLoader.getController();
                ticketController.setData(tickets.get(i));

                ticketGrid.add(box,0,row++);
                GridPane.setMargin(box, new Insets(10));
            }
        }catch(IOException e){
            e.printStackTrace();
        }*/
    }


    @FXML
    private void logoutuser() throws IOException {
        logout.process(new LogoutInput());
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow() , FXMLPaths.LOGIN_FORM.getPath());
    }

    /*private List<Ticket> tickets(){
        List<Ticket> ticketList=new ArrayList<>();

        for (int i=0; i<10;i++) {
            Ticket ticket = new Ticket();
            ticket.setTitle("proba");
            ticket.setGenre("genre");
            ticket.setType("type");
            ticket.setDate("28.12.2023");
            ticketList.add(ticket);
        }
        return ticketList;
    }*/

    @FXML
    private void createevent() throws IOException {
        SceneSwitcher.switchScene((Stage) btnEvents.getScene().getWindow() , FXMLPaths.CREATE_EVENT.getPath());
    }
}
