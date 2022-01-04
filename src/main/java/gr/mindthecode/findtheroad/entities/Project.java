package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "project")
public class Project { // A generic item class either tangible or intangible. All items have an ID, name, price and description.
    //We primarily deal with technology.

    @Id
    private String id;

    private String title;
    private String description;
    float price;
    private String dueDate;

    @DBRef
    @JsonManagedReference
    private List<Team> team; // The list of teams currently working on the project.

    @DBRef
    @JsonManagedReference
    private Customer customer; // The customer who requested the project.

    public Project(String title, String description, Customer customer) {
        this.title = title;
        this.description = description;
        this.customer = customer;
    }

    public Project(String title, String description, float price, String dueDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dueDate = dueDate;
    }

    public Project(String title, String description, float price, String dueDate, Customer customer) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dueDate = dueDate;
        this.customer = customer;
    }

}
