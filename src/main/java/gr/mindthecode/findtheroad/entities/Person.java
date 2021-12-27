package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "person")
public abstract class Person {

    @Id
    private String id;

    private String firstName; //name, γιατί ο customer που είναι subclass του Person ίσως είναι company και δεν έχει και τα 2
    private String lastName; //κι αυτό να μπει σαν έξτρα attribute του Employee
    private int age;
    private String address;
    private String email;
    private String personalPhoneNumber; //personalPhoneNumber to phoneNumber, εφόσον είμαστε μέσα στο περιβάλλον μιας εταιρείας

    public Person(String firstName, String lastName, int age, String address, String email, String personalPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.personalPhoneNumber = personalPhoneNumber;
    }

}
