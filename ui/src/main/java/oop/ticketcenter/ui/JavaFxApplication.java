package oop.ticketcenter.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;


public class JavaFxApplication extends javafx.application.Application {


    private static Logger logger;

    private AppContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        logger = Logger.getLogger(this.getClass().getName());
        PropertyConfigurator.configure(getClass().getResource("src/main/resources/log4j.properties"));
        AppContext.getInstance().setContext(SpringApplication.run(oop.ticketcenter.ui.SpringApplication.class));
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource("/fxmls/login.fxml"));
        fxmlLoader.setControllerFactory(AppContext.getInstance().getContext()::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void stop() {
        logger.log(Level.INFO, "Ended application");
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(rootNode));
        stage.show();
        logger.log(Level.INFO, "Started application");
    }

    public static void main(String[] args) {
        launch();
    }
}
