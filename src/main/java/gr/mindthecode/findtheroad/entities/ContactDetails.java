package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class ContactDetails {
    @Id
    private String id;

    @DBRef
    @JsonBackReference
    private Company company;
    private String email;
    private int telephoneNumber;
    private String website;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ContactDetails() {}

    public ContactDetails(String email, int telephoneNumber, String website) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.website = website;
    }

    public ContactDetails(String email, int telephoneNumber, String website, Company company) {
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.website = website;
        this.company = company;
    }
}
