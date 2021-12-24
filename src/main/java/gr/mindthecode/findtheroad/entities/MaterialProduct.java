package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialProduct extends Product { // A tangible item, which has attributes such as a color, material and size.
    public String color;
    public String material;
    public String size;

    public MaterialProduct(String name, float price, String description, String color, String material, String size) {
        super(name, price, description);
        this.color = color;
        this.material = material;
        this.size = size;
    }
}
