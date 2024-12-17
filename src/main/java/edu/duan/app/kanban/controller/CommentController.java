package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.comment.AddCommentRequest;
import edu.duan.app.kanban.api.comment.AddCommentResponse;
import edu.duan.app.kanban.api.comment.CommentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @PostMapping("/add")
    public ResponseEntity<AddCommentResponse> addComment(@Valid @RequestBody AddCommentRequest request) {
        AddCommentResponse response = new AddCommentResponse(new CommentDTO());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
