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

    @GetMapping("/api/loadDB")
    public String GenerateDataForDB() {

        loaddb.fillDatabase();
        return ("redirect:/projects");
    }

}
