package gr.mindthecode.findtheroad.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DigitalProduct extends Product { // A service is an intangible object such as an antivirus or digital course.
    private int duration;
    private boolean support;

    public DigitalProduct(String name, float price, String description, int duration, boolean support) {
        super(name, price, description);
        this.duration = duration;
        this.support = support;
    }
}
