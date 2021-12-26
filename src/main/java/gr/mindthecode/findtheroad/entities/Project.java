package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "project")
public class Project { // A generic item class either tangible or intangible. All items have an ID, name, price and description.
    //We primarily deal with technology.

    @Id
    private String id;

    private String name;        //or title
    private float price;
    private String description;

//    @ManyToMany
//    @JoinTable(
//            name = "PROJECT_TEAM",
//            joinColumns = @JoinColumn(name = "project_id"),
//            inverseJoinColumns = @JoinColumn(name = "team_id"))
//    @JsonManagedReference
    private List<Team> team;

    private Customer customer;


    public Project(String name, float price, String description, Customer customer ) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.customer=customer;
    }


}
