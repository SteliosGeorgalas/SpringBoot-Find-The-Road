package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Company {
    @Id
    private String id;

    @DBRef
    @JsonManagedReference
    private ContactDetails contactDetails;

    //    @DBRef
//    @JsonManagedReference
    private List<Employee> employeeList;
    private String name;
    private String address;
    private String bio;
    private List<Product> productList;


    public Company(ContactDetails contactDetails, String name, String address, String bio, List<Employee> employeeList) {
        this.contactDetails = contactDetails;
        this.name = name;
        this.address = address;
        this.bio = bio;
        this.employeeList = employeeList;
    }

    public Company(ContactDetails contactDetails, String name, String address, String bio) {
        this.contactDetails = contactDetails;
        this.name = name;
        this.address = address;
        this.bio = bio;
    }
}
