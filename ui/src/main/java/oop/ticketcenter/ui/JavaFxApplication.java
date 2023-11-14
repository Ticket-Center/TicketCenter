package oop.ticketcenter.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.io.IOException;


public class JavaFxApplication extends javafx.application.Application {

    private ConfigurableApplicationContext context;
    @Override
    public void init() throws Exception{

        this.context = new SpringApplicationBuilder(SpringApplication.class)
            .run();
    }
    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.context.publishEvent(new StagereadyEvent(stage));
    }

    public static void main(String[] args) {
        launch();
    }
}
class StagereadyEvent extends ApplicationEvent{

    public Stage getStage(){
        return (Stage) getSource();
    }
    public StagereadyEvent(Stage source) {
        super(source);
    }
}