package gr.mindthecode.findtheroadapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"gr.mindthecode.findtheroadmiddleware"})
@EntityScan("gr.mindthecode.findtheroadmiddleware")
@EnableMongoRepositories("gr.mindthecode.findtheroadmiddleware.repositories")
public class FindTheRoadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindTheRoadApiApplication.class, args);
    }

}
