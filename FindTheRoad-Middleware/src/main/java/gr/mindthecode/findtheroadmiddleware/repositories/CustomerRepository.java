package gr.mindthecode.findtheroadmiddleware.repositories;

import gr.mindthecode.findtheroadmiddleware.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameStartingWith(String lastName);
}
