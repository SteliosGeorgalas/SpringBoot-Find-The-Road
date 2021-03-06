package gr.mindthecode.findtheroad.controllers.MVC.searchModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectSearchModel {
    private String title;

    private String teamId;
}
