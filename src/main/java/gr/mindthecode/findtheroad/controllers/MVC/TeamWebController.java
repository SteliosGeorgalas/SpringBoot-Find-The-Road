package gr.mindthecode.findtheroad.controllers.MVC;

import gr.mindthecode.findtheroad.controllers.MVC.searchModels.EmployeeSearchModel;
import gr.mindthecode.findtheroad.controllers.MVC.searchModels.TeamsSearchModel;
import gr.mindthecode.findtheroad.entities.Employee;
import gr.mindthecode.findtheroad.entities.Project;
import gr.mindthecode.findtheroad.entities.Team;
import gr.mindthecode.findtheroad.repositories.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class TeamWebController {

    private final TeamRepository repository;

    public TeamWebController(TeamRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/teams")
    public String searchTeamsSubmit(
            @ModelAttribute TeamsSearchModel searchModel) {
        return "redirect:/teams?searchByName=" + searchModel.getName();
    }

    @GetMapping("/teams")
    public String showTeams(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchByName,
            @RequestParam(defaultValue = "") String searchByProjectId
    ) {
        if (page < 1) {
            return "redirect:/teams?size=" + size + "&page=1";
        };

        List<Team> teamList = repository.findAll();
        if (!searchByProjectId.equals("")) {
            teamList = repository.findByProjectListIn(searchByProjectId);
        } else if (!searchByName.equals("")) {
            teamList = repository.findByNameStartingWith(searchByName);
        }


        Page<Team> teams = findPaginated(teamList,
                PageRequest.of(page - 1, size)
        );

        int totalPages = teams.getTotalPages();

        if (totalPages > 0 && page > totalPages) {
            return "redirect:/teams?size=" + size + "&page=" + totalPages;
        }
        ;

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page - 2), Math.min(page + 2, totalPages))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("page", page);
        model.addAttribute("teams", teams);
        model.addAttribute("searchModel", new TeamsSearchModel(searchByName, searchByProjectId));
        return "teams";
    }

    @GetMapping("/teams/addteam")
    public String addTeam(Model model) {
        model.addAttribute("team", new Team());
        return "add-team";
    }

    @PostMapping("/teams/addteam")
    public String addTeam(Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-team";
        }

        repository.save(team);
        model.addAttribute("team", team);
        return "redirect:/teams";
    }

    @GetMapping("/teams/responsibleForProject/{id}")
    public String searchProjectId(@PathVariable("id") String id) {
        return "redirect:/teams?searchByProjectId=" + id;
    }

    @GetMapping("/teams/update/{id}")
    public String updateTeam(@PathVariable("id") String id, Model model) {
        Team team = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));

        model.addAttribute("team", team);
        return "update-team";
    }

    @PostMapping("/teams/update/{id}")
    public String updateTeam(@PathVariable("id") String id, Team team,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            team.setId(id);
            return "update-team";
        }
        Team teamOld = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));

        team.setEmployeeList(teamOld.getEmployeeList());
        team.setProjectList(teamOld.getProjectList());
        repository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/teams/delete/{id}")
    public String deleteTeam(@PathVariable("id") String id, Model model) {
        Team team = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team Id:" + id));
        repository.delete(team);
        return "redirect:/teams";
    }

    private Page<Team> findPaginated(List<Team> teams, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Team> result;

        if (teams.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, teams.size());
            result = teams.subList(startItem, toIndex);
        }

        Page<Team> teamPage = new PageImpl<Team>(result, PageRequest.of(currentPage, pageSize), teams.size());

        return teamPage;
    }
}
