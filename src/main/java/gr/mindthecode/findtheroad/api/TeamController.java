package gr.mindthecode.findtheroad.api;

import gr.mindthecode.findtheroad.entities.Team;
import gr.mindthecode.findtheroad.repositories.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamRepository repository;

    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/teams")
    List<Team> getTeam(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "nameStartsWith", required = false) String nameStartsWith
    ) {
        if (name != null && !name.equals("")) {
            return repository.findByName(name);
        }
        if (nameStartsWith != null && !nameStartsWith.equals("")) {
            return repository.findByNameStartingWith(nameStartsWith);
        }
        return repository.findAll();
    }

    @GetMapping("/api/teams/{id}")
    Team getTeam(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find team with id " + id));
    }

    @PutMapping("/api/teams/{id}")
    Team updateTeam(@RequestBody Team newTeam, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setName(newTeam.getName());
                    match.setEmail(newTeam.getEmail());
                    match.setTelephoneNumber(newTeam.getTelephoneNumber());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return repository.save(newTeam);
                });
    }

    @DeleteMapping("/api/teams")
    void deleteAllTeams() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/teams/{id}")
    void deleteTeam(@PathVariable String id) {
        repository.deleteById(id);
    }
}

