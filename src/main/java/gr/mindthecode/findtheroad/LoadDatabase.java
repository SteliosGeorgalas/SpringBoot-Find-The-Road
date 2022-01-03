package gr.mindthecode.findtheroad;

import gr.mindthecode.findtheroad.entities.Customer;
import gr.mindthecode.findtheroad.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private static final String[] personNames = new String[]{
            "Giannis",
            "Stelios",
            "Spyros",
            "Panagiotis",
            "Lefteris",
            "Vangelis",
            "Kostantinos",
            "Giorgos",
            "Georgia",
            "Kostantina",
            "Evaggelia",
            "Eleutheria",
            "Panagiota",
            "Eleni",
            "Stella",
            "Zoi"
    };

    private static final String[] personLastNames = new String[]{
            "Papakostantinou",
            "Papapetrou",
            "Eleutheriou",
            "Sotiriou",
            "Karakostas",
            "Charalampou",
            "Chatzichristou",

    };

    private static final String[] projectNames = new String[]{
            "Entity",
            "Meta",
            "Cascade",
            "Mercury",
            "Whistler",
            "Matador",
            "Yosemite",
            "Sirius",
            "Sputnik",
            "Grindstone",
            "Impact"
    };

    private static final String[] projectWork = new String[]{
            "New",
            "Update",
            "Maintain",
    };

    private static final String[] projectType = new String[]{
            "e-Shop",
            "website",
            "ERP",
            "banking system",
    };

    private static final String[] addresses = new String[]{
            "Iakovou",
            "Karaiskaki",
            "Panormou",
            "Smirnis",
    };

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CommentRepository commentRepository;

    private static List<Customer> generateRandomCustomers() {
        int count = getRandomUpperBound(15) + 5;

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String name = personNames[getRandomUpperBound(personNames.length)];
            String lastName = personLastNames[getRandomUpperBound(personLastNames.length)];
            int age = getRandomUpperBound(80) + 20;
            customers.add(
                    new Customer(
                            name,
                            lastName,
                            age,
                            (addresses[getRandomUpperBound(addresses.length)] + " " + getRandomUpperBound(100)),
                            getEmailFromName(name, lastName),
                            ("210" + getRandomUpperBound(60000000))
                    )
            );
        }

        return customers;
    }

    private static String getEmailFromName(String name, String lastName) {
        return (name.charAt(0) + "." + lastName + "@domainname.com");
    }

    private static int getRandomUpperBound(int i) {
        return new Random().nextInt(i);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        log.info("Preloading " + customerRepository.saveAll(generateRandomCustomers()));
    }
}
