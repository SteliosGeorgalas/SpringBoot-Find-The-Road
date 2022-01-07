package gr.mindthecode.findtheroadmiddleware.repositories;

import gr.mindthecode.findtheroadmiddleware.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByComment(String lastName);

    List<Comment> findCustomByProjectId(String teamId );

    List<Comment> findByCommentStartingWith(String lastName);
}
