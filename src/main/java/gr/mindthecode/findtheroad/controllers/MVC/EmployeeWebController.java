package gr.mindthecode.findtheroad.controllers.MVC;

import gr.mindthecode.findtheroad.controllers.MVC.searchModels.EmployeeSearchModel;
import gr.mindthecode.findtheroad.entities.Employee;
import gr.mindthecode.findtheroad.repositories.EmployeeRepository;
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
public class EmployeeWebController {
    private final EmployeeRepository repository;

    EmployeeWebController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/employee")
    public String searchEmployeeSubmit(
            @ModelAttribute EmployeeSearchModel searchModel) {
        return "redirect:/employee?searchByLastName=" + searchModel.getLastName();
    }

    @GetMapping("/employees/responsibleForTeam/{id}")
    public String searchEmployeeId(@PathVariable("id") String id,
            @ModelAttribute EmployeeSearchModel searchModel) {

        return "redirect:/employee?searchByTeamId=" + id;
    }

    @GetMapping("/employee")
    public String showEmployee(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchByLastName,
            @RequestParam(defaultValue = "") String searchByTeamId
    ) {
        if (page < 1) {
            return "redirect:/employee?size=" + size + "&page=1";
        }
        ;

        List<Employee> employeeList = repository.findAll();
        if (!searchByTeamId.equals("")) {
            employeeList = repository.findCustomByTeamId(searchByTeamId);
        } else if (!searchByLastName.equals("")) {
            employeeList = repository.findByLastName(searchByLastName);
        }

        Page<Employee> employee = findPaginated(
                employeeList,
                PageRequest.of(page - 1, size)
        );

        int totalPages = employee.getTotalPages();

        if (totalPages > 0 && page > totalPages) {
            return "redirect:/employee?size=" + size + "&page=" + totalPages;
        }
        ;

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page - 2), Math.min(page + 2, totalPages))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("page", page);
        model.addAttribute("employee", employee);
        model.addAttribute("searchModel", new EmployeeSearchModel(searchByLastName, searchByTeamId));
//        model.addAttribute("searchModel", new EmployeeSearchModel(searchById));
        return "employee";
    }

    @GetMapping("/employee/addemployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/employee/addemployee")
    public String addEmployee(Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }

        repository.save(employee);
        model.addAttribute("employee", employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable("id") String id, Model model) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));

        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @PostMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable("id") String id, Employee employee,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-employee";
        }

        repository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") String id, Model model) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        repository.delete(employee);
        return "redirect:/employee";
    }

    private Page<Employee> findPaginated(List<Employee> employee, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Employee> result;

        if (employee.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, employee.size());
            result = employee.subList(startItem, toIndex);
        }

        Page<Employee> employeePage = new PageImpl<Employee>(result, PageRequest.of(currentPage, pageSize), employee.size());

        return employeePage;
    }


}
