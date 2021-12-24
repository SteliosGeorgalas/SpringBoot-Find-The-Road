package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class Product { // A generic item class either tangible or intangible. All items have an ID, name, price and description.
    //We primarily deal with technology.

    @Id
    private int id;

    private String name;

    private float price;

    private String description;

    private Company company;

    private List<Comment> commentList;

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, float price, String description, Company company) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.company = company;
    }
}
