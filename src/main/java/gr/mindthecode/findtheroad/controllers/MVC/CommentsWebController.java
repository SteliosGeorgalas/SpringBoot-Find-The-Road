package gr.mindthecode.findtheroad.controllers.MVC;

import gr.mindthecode.findtheroad.controllers.MVC.searchModels.CommentSearchModel;
import gr.mindthecode.findtheroad.entities.Comment;
import gr.mindthecode.findtheroad.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CommentsWebController {
    private final CommentRepository repository;

    CommentsWebController(CommentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/comment")
    public String searchCommentSubmit(
            @ModelAttribute CommentSearchModel searchModel) {
        return "redirect:/comment?searchByComment=" + searchModel.getComment();
    }

    @GetMapping("/comments/responsibleForProject/{id}")
    public String searchCommentId(@PathVariable("id") String id,
                                  @ModelAttribute CommentSearchModel searchModel) {
        return "redirect:/comment?searchByProjectId=" + id;
    }

    @GetMapping("/comment")
    public String showComment(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchByComment,
            @RequestParam(defaultValue = "") String searchByProjectId
    ) {
        if (page < 1) {
            return "redirect:/comment?size=" + size + "&page=1";
        }
        ;

        List<Comment> commentList = repository.findAll();
        if (!searchByProjectId.equals("")) {
            commentList = repository.findCustomByProjectId(searchByProjectId);
        } else if (!searchByComment.equals("")) {
            commentList = repository.findByComment(searchByComment);
        }

        Page<Comment> comment = findPaginated(
                commentList,
                PageRequest.of(page - 1, size)
        );

        int totalPages = comment.getTotalPages();

        if (totalPages > 0 && page > totalPages) {
            return "redirect:/comment?size=" + size + "&page=" + totalPages;
        }
        ;

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page - 2), Math.min(page + 2, totalPages))
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("page", page);
        model.addAttribute("comment", comment);
        model.addAttribute("searchModel", new CommentSearchModel(searchByComment, searchByProjectId));
//        model.addAttribute("searchModel", new CommentSearchModel(searchById));
        return "comment";
    }

    @GetMapping("/comment/addcomment")
    public String addComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "add-comment";
    }

    @PostMapping("/comment/addcomment")
    public String addComment(Comment comment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-comment";
        }

        repository.save(comment);
        model.addAttribute("comment", comment);
        return "redirect:/comment";
    }

    @GetMapping("/comment/update/{id}")
    public String updateComment(@PathVariable("id") String id, Model model) {
        Comment comment = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));

        model.addAttribute("comment", comment);
        return "update-comment";
    }

    @PostMapping("/comment/update/{id}")
    public String updateComment(@PathVariable("id") String id, Comment comment,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            comment.setId(id);
            return "update-comment";
        }
        
        repository.save(comment);
        return "redirect:/comment";
    }

    @GetMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") String id, Model model) {
        Comment comment = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment Id:" + id));
        repository.delete(comment);
        return "redirect:/comment";
    }

    private Page<Comment> findPaginated(List<Comment> comment, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Comment> result;

        if (comment.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, comment.size());
            result = comment.subList(startItem, toIndex);
        }

        Page<Comment> commentPage = new PageImpl<Comment>(result, PageRequest.of(currentPage, pageSize), comment.size());

        return commentPage;
    }


}
