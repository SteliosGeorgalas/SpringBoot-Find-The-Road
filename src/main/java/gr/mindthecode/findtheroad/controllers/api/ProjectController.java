package gr.mindthecode.findtheroad.controllers.api;

import gr.mindthecode.findtheroad.entities.Project;
import gr.mindthecode.findtheroad.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectRepository repository;

    public ProjectController(ProjectRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/projects")
    List<Project> getProject(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "titleStartsWith", required = false) String titleStartsWith
    ) {
        if (title != null && !title.equals("")) {
            return repository.findByTitle(title);
        }
        if (titleStartsWith != null && !titleStartsWith.equals("")) {
            return repository.findByTitleStartingWith(titleStartsWith);
        }
        return repository.findAll();
    }

    @GetMapping("/api/projects/{id}")
    Project getProject(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find project with id " + id));
    }

    @PutMapping(value = "/api/projects/{id}", consumes = "application/json")
    Project updateProject(@RequestBody Project newProject, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setTitle(newProject.getTitle());
                    match.setDescription(newProject.getDescription());
                    match.setPrice(newProject.getPrice());
                    match.setDueDate(newProject.getDueDate());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newProject.setId(id);
                    return repository.save(newProject);
                });
    }

    @DeleteMapping("/api/projects")
    void deleteAllProjects() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/projects/{id}")
    void deleteProject(@PathVariable String id) {
        repository.deleteById(id);
    }
}
