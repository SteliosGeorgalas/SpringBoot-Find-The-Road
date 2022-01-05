package gr.mindthecode.findtheroad.controllers.MVC;

import gr.mindthecode.findtheroad.controllers.MVC.searchModels.PersonSearchModel;
import gr.mindthecode.findtheroad.entities.Person;
import gr.mindthecode.findtheroad.repositories.PersonRepository;
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
public class PersonWebController {
    private final PersonRepository repository;

    PersonWebController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/person")
    public String searchPersonSubmit(
            @ModelAttribute PersonSearchModel searchModel) {
                return "redirect:/person?searchByLastName=" + searchModel.getLastName();
    }

    @GetMapping("/person")
    public String showPerson(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchByLastName
    ) {
        if (page < 1) {
            return "redirect:/person?page=1&size="+ size;
        };

        Page<Person> person = findPaginated(
                !searchByLastName.equals("") ?
                        repository.findByLastName(searchByLastName) :
                        repository.findAll(),
                PageRequest.of(page - 1, size)
        );

        int totalPages = person.getTotalPages();

        if (totalPages > 0 && page > totalPages) {
            return "redirect:/person?size="+ size + "&page=" + totalPages;
        };

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page-2), Math.min(page + 2, totalPages))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("page", page);
        model.addAttribute("person", person);
        model.addAttribute("searchModel", new PersonSearchModel(searchByLastName));
        return "person";
    }

    @GetMapping("/person/addperson")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    @PostMapping("/person/addperson")
    public String addPerson(Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-person";
        }

        repository.save(person);
        model.addAttribute("person", person);
        return "redirect:/person";
    }

    @GetMapping("/person/update/{id}")
    public String updatePerson(@PathVariable("id") String id, Model model) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        model.addAttribute("person", person);
        return "update-person";
    }

    @PostMapping("/person/update/{id}")
    public String updatePerson(@PathVariable("id") String id, Person person,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-person";
        }

        repository.save(person);
        return "redirect:/person";
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable("id") String id, Model model) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        repository.delete(person);
        return "redirect:/person";
    }

    private Page<Person> findPaginated(List<Person> person, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Person> result;

        if (person.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, person.size());
            result = person.subList(startItem, toIndex);
        }

        Page<Person> personPage = new PageImpl<Person>(result, PageRequest.of(currentPage, pageSize), person.size());

        return personPage;
    }



}
