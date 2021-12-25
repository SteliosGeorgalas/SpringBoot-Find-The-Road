package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {

}
