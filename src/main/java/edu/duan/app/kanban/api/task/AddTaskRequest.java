package edu.duan.app.kanban.api.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 256, message = "Title length must be less than or equal to 256 characters")
    private String title;
    @Size(max = 5000, message = "Description length must be less than or equal to 5000 characters")
    private String description;
    @Size(max = 100, message = "Assignee length must be less than or equal to 100 characters")
    private String assignee;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadlineTime;
}
