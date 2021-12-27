package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "person")
@TypeAlias("employee")
public class Employee extends Person {
    //private boolean teamLeader; Λέω αυτό να υλοποιηθεί σαν πεδίο της κάθε Team,
    // ούτως ώστε να μην υπάρξει ομάδα με παραπάνω από δύο team leaders
    private String role;
    private List<Comment> commentList;
    private Team team;


    public Employee(String firstName, String lastName, int age, String address, String phoneNumber,
                    String email, String role) {
        super(firstName, lastName, age, address, phoneNumber, email);
        this.role = role;
    }

}
