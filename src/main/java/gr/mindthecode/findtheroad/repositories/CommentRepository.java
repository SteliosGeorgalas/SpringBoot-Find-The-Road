package gr.mindthecode.findtheroad.repositories;

import gr.mindthecode.findtheroad.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
