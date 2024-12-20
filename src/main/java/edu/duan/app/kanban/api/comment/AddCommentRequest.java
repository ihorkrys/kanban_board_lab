package edu.duan.app.kanban.api.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {
    private long taskId;
    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author name must be less than or equal to 100 characters")
    private String author;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 5000, message = "Content length must be less than or equal to 5000 characters")
    private String content;
}
