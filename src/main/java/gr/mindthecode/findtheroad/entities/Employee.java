package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
@TypeAlias("employee")
public class Employee extends Person {

    //private boolean teamLeader; Λέω αυτό να υλοποιηθεί σαν πεδίο της κάθε Team,
    // ούτως ώστε να μην υπάρξει ομάδα με παραπάνω από δύο team leaders
    private String role;

    @DBRef
    @JsonBackReference
    private Team team;


    public Employee(String firstName, String lastName, int age, String address, String email,
                    String personalPhoneNumber, String role) {
        super(firstName, lastName, age, address, email, personalPhoneNumber);
        this.role = role;
    }

}
