package edu.duan.app.kanban.api.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentResponse {
    private CommentDTO comment;
}
