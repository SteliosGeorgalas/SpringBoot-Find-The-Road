package gr.mindthecode.findtheroad.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.Id;

import java.util.List;

public class CommentSection {
    @Id
    private String id;

    @JsonBackReference
    private Company company;

    private List<Comment> commentList;
}
