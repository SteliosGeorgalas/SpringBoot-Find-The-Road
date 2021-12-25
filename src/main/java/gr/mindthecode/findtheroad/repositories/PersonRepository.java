package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(String lastName);

    List<Person> findByLastNameStartingWith(String lastName);
}
