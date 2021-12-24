package gr.mindthecode.findtheroad.entities;

import lombok.Data;

import java.util.List;

@Data
public class Customer extends Person {
    // Freelancer or not
    // private String type = "Free-lancer";
    private List<Product> cart;
}
