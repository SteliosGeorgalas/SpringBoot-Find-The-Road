package gr.mindthecode.findtheroad.controllers.MVC;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import gr.mindthecode.findtheroad.controllers.MVC.searchModels.ProjectSearchModel;
import gr.mindthecode.findtheroad.entities.Project;
import gr.mindthecode.findtheroad.repositories.ProjectRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProjectWebController {
    private final ProjectRepository repository;

    ProjectWebController(ProjectRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/projects")
    public String searchProjectsSubmit(
            @ModelAttribute ProjectSearchModel searchModel) {
                return "redirect:/projects?searchByTitle=" + searchModel.getTitle();
    }

    @GetMapping("/projects")
    public String showProjects(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchByTitle
    ) {
        if (page < 1) {
            return "redirect:/projects?page=1&size="+ size;
        };

        Page<Project> projects = findPaginated(
                !searchByTitle.equals("") ?
                        repository.findByTitleStartingWith(searchByTitle) :
                        repository.findAll(),
                PageRequest.of(page - 1, size)
        );

        int totalPages = projects.getTotalPages();

        if (totalPages > 0 && page > totalPages) {
            return "redirect:/projects?size="+ size + "&page=" + totalPages;
        };

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page-2), Math.min(page + 2, totalPages))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("page", page);
        model.addAttribute("projects", projects);
        model.addAttribute("searchModel", new ProjectSearchModel(searchByTitle));
        return "projects";
    }

    @GetMapping("/projects/addproject")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "add-project";
    }

    @PostMapping("/projects/addcar")
    public String addProject(Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-project";
        }

        repository.save(project);
        model.addAttribute("project", project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/update/{id}")
    public String updateProject(@PathVariable("id") String id, Model model) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));

        model.addAttribute("project", project);
        return "update-project";
    }

    @PostMapping("/project/update/{id}")
    public String updateProject(@PathVariable("id") String id, Project project,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            project.setId(id);
            return "update-project";
        }

        repository.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable("id") String id, Model model) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));
        repository.delete(project);
        return "redirect:/projects";
    }

    private Page<Project> findPaginated(List<Project> projects, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Project> result;

        if (projects.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, projects.size());
            result = projects.subList(startItem, toIndex);
        }

        Page<Project> projectPage = new PageImpl<Project>(result, PageRequest.of(currentPage, pageSize), projects.size());

        return projectPage;
    }



}
