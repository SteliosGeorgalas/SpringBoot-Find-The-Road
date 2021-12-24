package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "person")
public abstract class Person {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String personalPhoneNumber;

    public Person(String firstName, String lastName, int age, String address, String personalPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.personalPhoneNumber = personalPhoneNumber;
    }


}
