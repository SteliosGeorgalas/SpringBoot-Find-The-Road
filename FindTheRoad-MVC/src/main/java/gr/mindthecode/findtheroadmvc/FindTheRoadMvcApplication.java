package gr.mindthecode.findtheroadmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"gr.mindthecode.findtheroadmiddleware"})
public class FindTheRoadMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindTheRoadMvcApplication.class, args);
    }

}
