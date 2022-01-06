package gr.mindthecode.findtheroadmvc.Controllers.searchModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSearchModel {
    private String comment;

    private String projectId;

}
