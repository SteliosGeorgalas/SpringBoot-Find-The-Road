package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "person")
public class Employee extends Person {
    private boolean teamLeader;
    private String role;
    private List<Comment> commentList;

    //    @DBRef
//    @JsonBackReference

    public Employee(String firstName, String lastName, int age, String address, String phoneNumber,
                    String email,boolean teamLeader,String role) {
        super(firstName, lastName, age, address, phoneNumber,email);
        this.teamLeader = teamLeader;
        this.role = role;
    }
}
