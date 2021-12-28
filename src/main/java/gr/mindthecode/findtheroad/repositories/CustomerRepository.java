package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameStartingWith(String lastName);
}
