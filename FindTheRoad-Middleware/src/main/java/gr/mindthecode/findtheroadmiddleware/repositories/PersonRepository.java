package gr.mindthecode.findtheroadmiddleware.repositories;

import gr.mindthecode.findtheroadmiddleware.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(String lastName);
    List<Person> findByLastNameStartingWith(String lastName);
}
