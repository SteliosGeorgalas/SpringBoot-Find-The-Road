package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Company {
    @Id
    private String id;

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @DBRef
    @JsonManagedReference
    private ContactDetails contactDetails;

    private String name;
    private String address;

    @DBRef
    @JsonManagedReference
    private CommentSection commentSection;
}
