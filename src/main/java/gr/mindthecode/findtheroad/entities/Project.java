package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "project")
public class Project { // A generic item class either tangible or intangible. All items have an ID, name, price and description.
    //We primarily deal with technology.

    @Id
    private String id;

    float price;
    private String description;
    private String title;
    private Date dueDate;

    //    @ManyToMany
//    @JoinTable(
//            name = "PROJECT_TEAM",
//            joinColumns = @JoinColumn(name = "project_id"),
//            inverseJoinColumns = @JoinColumn(name = "team_id"))
//    @JsonManagedReference
    private List<Team> team; // The list of teams currently working on the project.
    private Customer customer; // The customer who requested the project.

    public Project(String title, String description, Customer customer) {
        this.title = title;
        this.description = description;
        this.customer = customer;
    }

    public Project(String title, String description, float price, Date date) {
        this.title = title;
        this.description = description;
    }

}
