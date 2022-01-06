package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByLastName(String lastName);

    List<Employee> findCustomByTeamId(String teamId);

    List<Employee> findByLastNameStartingWith(String lastName);
}
