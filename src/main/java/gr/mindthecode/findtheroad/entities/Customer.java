package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
@TypeAlias("customer")
public class Customer extends Person {
    @DBRef
    @JsonBackReference
    private List<Project> projectList;

    public Customer(String firstName, String lastName, int age, String address, String email,
                    String personalPhoneNumber) {
        super(firstName, lastName, age, address, email, personalPhoneNumber);
    }


}
