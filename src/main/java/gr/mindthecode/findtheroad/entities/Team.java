package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Document(collection = "team")
public class Team {
    @Id
    private String id;
    private String name;
    private String email;                   //may remove it
    private String telephoneNumber;         //optional, may remove it

    private Employee teamLeader; // The team's team leader, who should belong in the employeeList List below.

    @DBRef
    @JsonBackReference
    private List<Project> projectList; // The team's project List currently active

    @DBRef
    @JsonManagedReference
    private List<Employee> employeeList; //The team's list of employees

    public Team(String name, String email, String telephoneNumber) {
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Team(String email, String telephoneNumber) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

}
