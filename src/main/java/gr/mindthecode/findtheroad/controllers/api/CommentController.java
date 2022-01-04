package gr.mindthecode.findtheroad.controllers.api;

import gr.mindthecode.findtheroad.entities.Comment;
import gr.mindthecode.findtheroad.repositories.CommentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentRepository repository;

    CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/api/comments")
    List<Comment> GetComments() {
        return repository.findAll();
    }

    @GetMapping("/api/comments/{id}")
    Comment GetComment(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find comment with id " + id));
    }

    @PutMapping("/api/comments/{id}")
    Comment updateComment(@RequestBody Comment newComment, @PathVariable String id) {

        return repository.findById(id)
                .map(match -> {
                    match.setComment(newComment.getComment());
                    match.setDate(newComment.getDate());
                    return repository.save(match);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return repository.save(newComment);
                });
    }

    @DeleteMapping("/api/comments")
    void deleteAllComments() {
        repository.deleteAll();
    }

    @DeleteMapping("/api/comments/{id}")
    void deleteComment(@PathVariable String id) {
        repository.deleteById(id);
    }
}
