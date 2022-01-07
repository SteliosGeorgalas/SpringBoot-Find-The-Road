package gr.mindthecode.findtheroad.controllers.MVC.searchModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TeamsSearchModel {
    private String name;

    private String projectId;
}
