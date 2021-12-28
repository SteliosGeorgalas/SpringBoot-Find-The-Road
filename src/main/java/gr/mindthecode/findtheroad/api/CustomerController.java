package gr.mindthecode.findtheroad.api;

import gr.mindthecode.findtheroad.entities.Customer;
import gr.mindthecode.findtheroad.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/customers")
    List<Customer> getCustomer(
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

    @GetMapping("/api/customers/{id}")
    Customer getCustomer(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find customer with id " + id));
    }

    @PutMapping("/api/customers/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setFirstName(newCustomer.getFirstName());
                    match.setLastName(newCustomer.getLastName());
                    match.setAge(newCustomer.getAge());
                    match.setAddress(newCustomer.getAddress());
                    match.setEmail(newCustomer.getEmail());
                    match.setPersonalPhoneNumber(newCustomer.getPersonalPhoneNumber());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }

    @DeleteMapping("/api/customers")
    void deleteAllCustomers() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/customers/{id}")
    void deleteCustomer(@PathVariable String id) {
        repository.deleteById(id);
    }
}

