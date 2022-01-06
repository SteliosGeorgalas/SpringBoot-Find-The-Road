package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Comment;
import gr.mindthecode.findtheroad.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByComment(String lastName);

    List<Comment> findCustomByProjectId(String teamId );

    List<Comment> findByCommentStartingWith(String lastName);
}
