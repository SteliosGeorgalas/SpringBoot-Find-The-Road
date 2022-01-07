package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    private String comment;
    private String date;
    @DBRef
    @JsonBackReference(value = "projectComments")
    private Project project;


    public Comment(String comment, String date) {
        this.comment = comment;
        this.date = date;
    }

    public Comment(Project project) {
        this.project = project;
    }
}
