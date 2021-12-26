//package gr.mindthecode.findtheroad.entities;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//public class ContactDetails {
//    @Id
//    private String id;
//
//    @DBRef
//    @JsonBackReference
//    private Company company;
//    private String email;
//    private int telephoneNumber;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getTelephoneNumber() {
//        return telephoneNumber;
//    }
//
//    public void setTelephoneNumber(int telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }
//
//    public ContactDetails() {}
//
//    public ContactDetails(String email, int telephoneNumber) {
//        this.email = email;
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public ContactDetails(String email, int telephoneNumber, Company company) {
//        this.email = email;
//        this.telephoneNumber = telephoneNumber;
//        this.company = company;
//    }
//}
