package oop.ticketcenter.ui.helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Stage stage, String sceneName) throws IOException {
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(sceneName));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
