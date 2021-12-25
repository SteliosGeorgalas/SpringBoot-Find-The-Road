package gr.mindthecode.findtheroad.api;

import gr.mindthecode.findtheroad.repositories.CompanyRepository;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CompanyController {

    private final CompanyRepository repository;

    public CompanyController(CompanyRepository repository) {
        this.repository = repository;
    }

    /* Static response for the GET, remove in the future
    @GetMapping("/api/companies")
    List<Company> getCompanies() {
        return List.of(
                new Company("1",
                        "Emergency Consulting S.A",
                        "emergencyconsulting@templatemail.com",
                        "2105555555",
                        "254 East Yorkshire Blvd, Southampton",
                        "We are a very good company!"
                ),
                new Company("1",
                        "Emergency Consulting S.A",
                        "emergencyconsulting@templatemail.com",
                        "2105555555",
                        "254 East Yorkshire Blvd, Southampton",
                        "We are a very good company!"
                )
        );
    }
     */
}
