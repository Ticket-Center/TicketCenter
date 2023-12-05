package oop.ticketcenter.ui.helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.ticketcenter.ui.AppContext;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Stage stage, String sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(sceneName));
        loader.setControllerFactory(AppContext.getInstance().getContext()::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
