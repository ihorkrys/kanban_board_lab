package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.comment.AddCommentRequest;
import edu.duan.app.kanban.api.comment.AddCommentResponse;
import edu.duan.app.kanban.api.comment.CommentDTO;
import edu.duan.app.kanban.mapper.CommentMapper;
import edu.duan.app.kanban.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddCommentResponse> addComment(@Valid @RequestBody AddCommentRequest request) {
        CommentDTO comment = commentService.createComment(request.getTaskId(), request.getAuthor(), request.getContent());
        return new ResponseEntity<>(new AddCommentResponse(comment), HttpStatus.CREATED);
    }
}
