package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "company")
public class Company {
    @Id
    private String id;
    private String name;
    private String email;
    private String telephoneNumber;
    private String address;
    private String whoWeAre;
    private List<Product> productList;


    public Company(String name, String email, String telephoneNumber, String address, String whoWeAre) {
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.whoWeAre = whoWeAre;
    }

    public Company(String id, String name, String email, String telephoneNumber, String address, String whoWeAre) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.whoWeAre = whoWeAre;
    }
}
