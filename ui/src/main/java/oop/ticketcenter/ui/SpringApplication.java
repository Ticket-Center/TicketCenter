package oop.ticketcenter.ui;


import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "oop.ticketcenter")
@EntityScan(basePackages = "oop.ticketcenter.persistence.entities")
@EnableJpaRepositories(basePackages = "oop.ticketcenter.persistence.repositories")
public class SpringApplication{

    public static void main(String[] args) {
        // This is how normal Spring Boot app would be launched
        // SpringApplication.run(SpringBootExampleApplication.class, args);

        // JavaFxApplication doesn't exist yet,
        // we'll create it in the next step
        Application.launch(JavaFxApplication.class, args);
    }
}
