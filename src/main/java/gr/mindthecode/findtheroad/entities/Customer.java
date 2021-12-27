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
@TypeAlias("customer")
public class Customer extends Person {
    private List<Project> projectList;

    public Customer(String firstName, String lastName, int age, String address, String phoneNumber,
                    String email, String role) {
        super(firstName, lastName, age, address, phoneNumber, email);
    }

}
