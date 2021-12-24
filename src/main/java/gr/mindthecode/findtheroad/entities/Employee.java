package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee extends Person {
    private String position;
    private String companyPhoneNumber;
    private String companyEmail;

    //    @DBRef
//    @JsonBackReference
    private Company company;

    public Employee(String firstName, String lastName, int age, String address, String personalPhoneNumber,
                    String position, String companyPhoneNumber, String companyEmail, Company company) {
        super(firstName, lastName, age, address, personalPhoneNumber);
        this.position = position;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyEmail = companyEmail;
        this.company = company;
    }
}
