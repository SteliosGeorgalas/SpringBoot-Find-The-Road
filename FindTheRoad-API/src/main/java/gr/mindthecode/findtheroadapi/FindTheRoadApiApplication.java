package gr.mindthecode.findtheroadapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan({"gr.mindthecode.findtheroadmiddleware"})
public class FindTheRoadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindTheRoadApiApplication.class, args);
    }

}
