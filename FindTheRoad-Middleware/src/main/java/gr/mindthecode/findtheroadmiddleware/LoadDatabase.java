package gr.mindthecode.findtheroadmiddleware;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import gr.mindthecode.findtheroadmiddleware.entities.*;
import gr.mindthecode.findtheroadmiddleware.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    static List<Team> updatedTeams = new ArrayList<>();
    static List<Customer> updatedCustomers = new ArrayList<>();
    static List<Project> updatedProjects = new ArrayList<>();


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

    // Meta - Update ERP
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


    private  static final String[] employeeRoles = new String[]{
            "Full-Stack Developer",
            "Front-end Developer",
            "Back-end Developer",
            "UX designer",
            "UI designer",
            "Architectural Manager",
            "QA tester"
    };


    private static final String[] teamNamesNouns = new String[]{
            "Department",
            "Potato",
            "Attitude",
            "Marketing",
            "Hospital",
            "Relation",
            "Permission",
            "Candidate",
            "Revenue",
            "Charity",
            "Information"
    };

    private static final String[] teamNamesAdjectives = new String[]{
            "Financial",
            "Abhorrent",
            "Puzzling",
            "Impartial",
            "Wide-eyed",
            "Colossal",
            "Powerful",
            "Optimal",
            "Infamous",
            "Arrogant",
            "Wild",
            "Colorful"
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

    @EventListener(ApplicationReadyEvent.class)
    public void restoreDatabase() {
        //delete data
        log.info("Deleting Customers"); customerRepository.deleteAll();
        log.info("Deleting Projects");  projectRepository.deleteAll();
        log.info("Deleting Teams"); teamRepository.deleteAll();
        log.info("Deleting Employees"); employeeRepository.deleteAll();
        log.info("Deleting Comments"); commentRepository.deleteAll();


        // initialize data
        log.info("Preloading Customers"); customerRepository.saveAll(generateRandomCustomers());
        log.info("Preloading Projects"); projectRepository.saveAll(generateRandomProjects(customerRepository.findAll()));
        log.info("Connecting Customers with Projects"); customerRepository.saveAll(updatedCustomers);

        log.info("Preloading Teams"); teamRepository.saveAll(generateRandomTeams());
        log.info("Preloading Employees");  employeeRepository.saveAll(generateRandomEmployees(teamRepository.findAll()));
        log.info("Connecting Teams with Employees");  teamRepository.saveAll(updatedTeams);

        log.info("Preloading Comments"); commentRepository.saveAll(generateRandomComments(projectRepository.findAll()));
        log.info("Connecting Projects with Comments");  projectRepository.saveAll(updatedProjects);

        log.info("Connecting Teams with Projects");
        updatedTeams.clear(); updatedProjects = projectRepository.findAll();
        MatchTeamWithProjects(teamRepository.findAll(),updatedProjects);
        MatchProjectWithTeams(updatedTeams,updatedProjects);
        updateTeamsAndProjects();

        log.info("Database setup completed");
    }

    //    @EventListener(ApplicationReadyEvent.class)
//    public void fillDatabase() {
//        log.info("Preloading Customers"); customerRepository.saveAll(generateRandomCustomers());
//        log.info("Preloading Projects"); projectRepository.saveAll(generateRandomProjects(customerRepository.findAll()));
//        log.info("Updating Customers "); customerRepository.saveAll(updatedCustomers);
//        log.info("Preloading Teams"); teamRepository.saveAll(generateRandomTeams());
//        log.info("Preloading Employees");  employeeRepository.saveAll(generateRandomEmployees(teamRepository.findAll()));
//        log.info("Updating Teams");  teamRepository.saveAll(updatedTeams);
//        log.info("Preloading Comments"); commentRepository.saveAll(generateRandomComments(projectRepository.findAll()));
//        log.info("Updating Projects");  projectRepository.saveAll(updatedProjects);
//
//    }
    private static List<Customer> generateRandomCustomers() {
        int count = 1;

        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String name = personNames[getRandomUpperBound(personNames.length)];
            String lastName = personLastNames[getRandomUpperBound(personLastNames.length)];
            int age = getRandomUpperBound(20) + 5;
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

    private  List<Project> generateRandomProjectsForEachCustomer(Customer customer) {
        Lorem lorem = LoremIpsum.getInstance();
        int projectCount = getRandomUpperBound(3);

        List<Project> projects = new ArrayList<>();
        for (int i = 0; i <= projectCount; i++) {
            String title = projectNames[getRandomUpperBound(projectNames.length)]
                    + " - " +
                    projectWork[getRandomUpperBound(projectWork.length)]
                    + " " +
                    projectType[getRandomUpperBound(projectType.length)];

            String description = lorem.getWords(5, 20);
            float price = getRandomUpperBound(1500000);
            String dueDate = getDueDate();
            Project p = new Project(
                    title,
                    description,
                    price,
                    dueDate,
                    customer
            );
            p.setCustomer(customer);
            projects.add(p);
        }
        customer.setProjectList(projects);
        updatedCustomers.add(customer);
        return projects;
    }

    private  <T extends Customer> List<Project> generateRandomProjects(List<T> allCustomers) {
        List<Project> projectList = new ArrayList<>();
        allCustomers.forEach((n) -> projectList.addAll(generateRandomProjectsForEachCustomer(n)));
        return projectList;
    }

    private <T extends Project> List<Comment> generateRandomComments(List<T> allProjects) {
        List<Comment> totalComments= new ArrayList<>();

        for (Project  project: allProjects) {
            List< Comment> comments = new ArrayList<>();
            int count = 5;//getRandomUpperBound(5) + 3;
            Lorem lorem = LoremIpsum.getInstance();
            for (int i = 0; i < count; i++) {
                Comment comment = new Comment ();
                comment.setComment(lorem.getWords(10, 20));
                comment.setDate(getCommentDate());

                comments.add(comment);
            }

            project.setComments(comments);
            updatedProjects.add(project);

            totalComments.addAll(comments);

        }
        return totalComments;
    }

    private <T extends Team> List<Employee> generateRandomEmployees(List<T> allTeams) {

        List<Employee> totalEmployees = new ArrayList<>();


        for (Team team : allTeams) {

            List<Employee> employees = new ArrayList<>();

            int count = getRandomUpperBound(5) + 3;
            for (int i = 0; i < count; i++) {
                Employee employee = new Employee();
                employee.setFirstName(personNames[getRandomUpperBound(personNames.length)]);
                employee.setLastName(personLastNames[getRandomUpperBound(personLastNames.length)]);
                employee.setAge(getRandomUpperBound(80) + 20);
                employee.setRole(employeeRoles[getRandomUpperBound(employeeRoles.length)]);
                employee.setEmail(getEmailFromName(employee.getFirstName(),employee.getLastName()));
                employee.setPersonalPhoneNumber(("210" + getRandomUpperBound(60000000)));

                employee.setTeam(team);
                employees.add(employee);
            }
            employees.get(getRandomUpperBound(count)).setRole("Project Manager");

            team.setEmployeeList(employees);
            updatedTeams.add(team);

            totalEmployees.addAll(employees);

        }

        return totalEmployees;
    }

    private static  List<Team> generateRandomTeams() {
            List<Team> teams = new ArrayList<>();

            int count =getRandomUpperBound(5) + 3;
            for (int i = 0; i < count; i++) {
                Team team = new Team();
                team.setName(teamNamesAdjectives[getRandomUpperBound(teamNamesAdjectives.length)]
                        + " - " +
                        teamNamesNouns[getRandomUpperBound(teamNamesNouns.length)]);
                teams.add(team);
            }
        return teams;
    }

    private static void MatchTeamWithProjects(List<Team> allTeams, List<Project> allProjects) {



            updatedTeams = allTeams.stream().map( team ->
            {
                int projectCount = getRandomUpperBound(2) + 1;

                for (int i = 0; i < projectCount;i++){
                   Project randomProject = allProjects.get(getRandomUpperBound(allProjects.size()));

                    if (team.getProjectList().contains(randomProject))
                        continue;
                    team.getProjectList().add(randomProject);

                    randomProject.getTeamList().add(team);

                }

                return team; }).collect(Collectors.toList());

           }

    private static void MatchProjectWithTeams(List<Team> allTeams, List<Project> allProjects) {


        updatedProjects = allProjects.stream().map( project ->
        {

            int projectCount = getRandomUpperBound(2) + 1;
            for (int i = 0; i < projectCount;i++){
                Team randomTeam = allTeams.get(getRandomUpperBound(allTeams.size()));

                if (project.getTeamList().contains(randomTeam))
                    continue;
                project.getTeamList().add(randomTeam);

                if (!randomTeam.getProjectList().contains(project))
                    randomTeam.getProjectList().add(project);

            }

            return project; }).collect(Collectors.toList());

        }

    private void updateTeamsAndProjects(){
        teamRepository.saveAll(updatedTeams);
        projectRepository.saveAll(updatedProjects);
    }

    private static String getCommentDate() {
        String month = Integer.toString((getRandomUpperBound(11) + 1));
        String year = Integer.toString(getRandomUpperBound(2022 - 2020) + 2020);
        return (month + "/" + year);
    }

    private static String getEmailFromName(String name, String lastName) {
        return (name.toLowerCase().charAt(0) + "." + lastName.toLowerCase() + "@domainname.com");
    }

    private static String getDueDate() {
        String month = Integer.toString((getRandomUpperBound(11) + 1));
        String year = Integer.toString(getRandomUpperBound(2) + 2022);
        return (month + "/" + year);
    }

    private static int getRandomUpperBound(int i) {
        return new Random().nextInt(i);
    }
}
