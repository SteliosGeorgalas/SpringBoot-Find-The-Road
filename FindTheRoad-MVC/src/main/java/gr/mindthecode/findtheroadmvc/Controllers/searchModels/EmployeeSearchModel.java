package gr.mindthecode.findtheroadmvc.Controllers.searchModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchModel {
    private String lastName;

    private String teamId;

}
