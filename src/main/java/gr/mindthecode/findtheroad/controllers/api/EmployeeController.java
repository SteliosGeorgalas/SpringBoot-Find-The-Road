package gr.mindthecode.findtheroad.controllers.api;

import gr.mindthecode.findtheroad.entities.Employee;
import gr.mindthecode.findtheroad.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/employees")
    List<Employee> getEmployee(
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

    @GetMapping("/api/employees/{id}")
    Employee getEmployee(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find employee with id " + id));
    }

    @PutMapping("/api/employees/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setFirstName(newEmployee.getFirstName());
                    match.setLastName(newEmployee.getLastName());
                    match.setAge(newEmployee.getAge());
                    match.setAddress(newEmployee.getAddress());
                    match.setEmail(newEmployee.getEmail());
                    match.setPersonalPhoneNumber(newEmployee.getPersonalPhoneNumber());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/api/employees")
    void deleteAllEmployees() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/employees/{id}")
    void deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
    }
}

