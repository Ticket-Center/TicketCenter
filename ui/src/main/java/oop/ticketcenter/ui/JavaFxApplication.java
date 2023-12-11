package oop.ticketcenter.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;


public class JavaFxApplication extends javafx.application.Application {


//    private ConfigurableApplicationContext context;

    private AppContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        AppContext.getInstance().setContext(SpringApplication.run(oop.ticketcenter.ui.SpringApplication.class));
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource("/fxmls/login.fxml"));
        fxmlLoader.setControllerFactory(AppContext.getInstance().getContext()::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void stop() {
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(rootNode));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
