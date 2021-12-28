package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findByName(String name);

    List<Team> findByNameStartingWith(String name);
}
