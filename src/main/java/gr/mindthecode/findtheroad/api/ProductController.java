package gr.mindthecode.findtheroad.api;


import gr.mindthecode.findtheroad.entities.Product;
import gr.mindthecode.findtheroad.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/product")
    List<Product> getProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "nameStartsWith", required = false) String nameStartsWith
    ) {
        if (name != null && !name.equals("")) {
            return repository.findAllByName(name);
        }
        if (nameStartsWith != null && !nameStartsWith.equals("")) {
            return repository.findByNameStartingWith(nameStartsWith);
        }
        return repository.findAll();
    }

    @GetMapping("/api/product/{id}")
    Product getProduct(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find product with id " + id));
    }

    @PutMapping("/api/product/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setName(newProduct.getName());
                    match.setDescription(newProduct.getDescription());
                    match.setPrice(newProduct.getPrice());
                    match.setCompany(newProduct.getCompany());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/api/product")
    void deleteAllProducts() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/product/{id}")
    void deleteProduct(@PathVariable String id) {
        repository.deleteById(id);
    }

}
