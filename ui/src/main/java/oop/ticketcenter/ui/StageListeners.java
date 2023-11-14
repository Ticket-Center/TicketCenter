package oop.ticketcenter.ui;

//import lombok.Value;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListeners implements ApplicationListener<StagereadyEvent> {

    private final String applicationTitle;
   // private final Resource fxml;
    private final ApplicationContext ac;

    public StageListeners(@Value("${app.custom.title}") String applicationTitle,
                          //Resource fxml,
                          ApplicationContext ac) {
        this.applicationTitle = applicationTitle;
        //this.fxml = fxml;
        this.ac = ac;
    }

    @Override
    public void onApplicationEvent(StagereadyEvent event) {
        try {
            Stage stage = event.getStage();
            //URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource("/fxmls/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
