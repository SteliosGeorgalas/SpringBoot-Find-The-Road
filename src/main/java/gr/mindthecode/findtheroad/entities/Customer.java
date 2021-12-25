package gr.mindthecode.findtheroad.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "person")
public class Customer extends Person {
    // Freelancer or not
    // private String type = "Free-lancer";
    private List<Product> cart;
}
