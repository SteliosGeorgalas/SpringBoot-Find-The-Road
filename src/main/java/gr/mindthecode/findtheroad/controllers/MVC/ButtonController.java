package gr.mindthecode.findtheroad.controllers.MVC;

import gr.mindthecode.findtheroad.LoadDatabase;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor

public class ButtonController {

    @Autowired
    LoadDatabase loaddb;

    @GetMapping("/api/projects/loadDB")
    public String GenerateDataForDBP() {

        loaddb.fillDatabase();
        return ("redirect:/projects");
    }

    @GetMapping("/api/projects/emptyDB")
    public String ClearDataForDBP() {

        loaddb.clearDatabase();
        return ("redirect:/projects");
    }

    @GetMapping("/api/teams/loadDB")
    public String GenerateDataForDBT() {

        loaddb.fillDatabase();
        return ("redirect:/teams");
    }

    @GetMapping("/api/teams/emptyDB")
    public String ClearDataForDBT() {

        loaddb.clearDatabase();
        return ("redirect:/teams");
    }

    @GetMapping("/api/employees/loadDB")
    public String GenerateDataForDBE() {

        loaddb.fillDatabase();
        return ("redirect:/employee");
    }

    @GetMapping("/api/employees/emptyDB")
    public String ClearDataForDBE() {

        loaddb.clearDatabase();
        return ("redirect:/employee");
    }

    @GetMapping("/api/comments/loadDB")
    public String GenerateDataForDBC() {

        loaddb.fillDatabase();
        return ("redirect:/comment");
    }

    @GetMapping("/api/comments/emptyDB")
    public String ClearDataForDBC() {

        loaddb.clearDatabase();
        return ("redirect:/comment");
    }


}
