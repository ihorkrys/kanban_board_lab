package edu.duan.app.kanban.api.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private String id;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author name must be less than or equal to 100 characters")
    private String author;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 5000, message = "Content length must be less than or equal to 5000 characters")
    private String content;

    @NotNull(message = "Publish date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishDate;
}