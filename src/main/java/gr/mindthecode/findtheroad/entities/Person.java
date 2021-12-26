package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


//@Data
//@NoArgsConstructor
@Data
@NoArgsConstructor
@Document(collection = "person")
public class Person {

    @Id
    private String id;

    private String firstName;       //name, γιατί ο customer που είναι subclass του Person ίσως είναι company και δεν έχει και τα 2
    private String lastName;        //κι αυτό να μπει σαν έξτρα attribute του Employee
    private int age;
    private String address;
    private String phoneNumber; //personalPhoneNumber to phoneNumber, εφόσον είμαστε μέσα στο περιβάλλον μιας εταιρείας
    private String email;

    public Person(String firstName, String lastName, int age, String address, String phoneNumber,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
