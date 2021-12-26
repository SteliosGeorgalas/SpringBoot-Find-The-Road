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
@Document(collection = "team")
public class Team {
    @Id
    private String id;

    private String department;
    private String email;                   //may remove it
    private String telephoneNumber;         //optional, may remove it

//    @ManyToMany(mappedBy = "team")
//    @JsonBackReference
    private List<Project> projectList;

    private List<Employee> teamsEmployees;

    public Team(String department, String email, String telephoneNumber) {
        this.department = department;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

}
