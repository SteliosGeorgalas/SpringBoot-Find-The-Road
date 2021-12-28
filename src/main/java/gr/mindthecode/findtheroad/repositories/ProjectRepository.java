package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByTitle(String title);

    List<Project> findByTitleStartingWith(String title);
}
