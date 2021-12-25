package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "product")
public class Product { // A generic item class either tangible or intangible. All items have an ID, name, price and description.
    //We primarily deal with technology.

    @Id
    private String id;

    private String name;

    private float price;

    private String description;


    //Possible reworking / maybe even removal
    private String company;

    private List<Comment> commentList;

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, float price, String description, String company) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.company = company;
    }

}
