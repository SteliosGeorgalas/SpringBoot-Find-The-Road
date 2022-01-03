package gr.mindthecode.findtheroad.controllers.api;

import gr.mindthecode.findtheroad.entities.Person;
import gr.mindthecode.findtheroad.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/person")
    List<Person> getPerson(
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "lastNameStartsWith", required = false) String lastNameStartsWith
    ) {
        if (lastName != null && !lastName.equals("")) {
            return repository.findByLastName(lastName);
        }
        if (lastNameStartsWith != null && !lastNameStartsWith.equals("")) {
            return repository.findByLastNameStartingWith(lastNameStartsWith);
        }
        return repository.findAll();
    }

    @GetMapping("/api/person/{id}")
    Person getPerson(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find person with id " + id));
    }

    @PutMapping("/api/person/{id}")
    Person updatePerson(@RequestBody Person newPerson, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setFirstName(newPerson.getFirstName());
                    match.setLastName(newPerson.getLastName());
                    match.setAge(newPerson.getAge());
                    match.setAddress(newPerson.getAddress());
                    match.setEmail(newPerson.getEmail());
                    match.setPersonalPhoneNumber(newPerson.getPersonalPhoneNumber());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/api/person")
    void deleteAllPersons() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/person/{id}")
    void deletePerson(@PathVariable String id) {
        repository.deleteById(id);
    }
}
