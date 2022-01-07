package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Project;
import gr.mindthecode.findtheroad.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByTitle(String title);

    List<Project> findByTeamListIn(String id);

    List<Project> findByTitleStartingWith(String title);
}
