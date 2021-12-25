package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByName(String name);

    List<Product> findByNameStartingWith(String name);
}
