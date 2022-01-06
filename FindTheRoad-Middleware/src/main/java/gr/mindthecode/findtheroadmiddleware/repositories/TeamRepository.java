package gr.mindthecode.findtheroadmiddleware.repositories;

import gr.mindthecode.findtheroadmiddleware.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findByName(String name);

    List<Team> findByNameStartingWith(String name);
}
