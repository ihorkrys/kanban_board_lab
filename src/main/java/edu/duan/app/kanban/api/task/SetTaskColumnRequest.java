package edu.duan.app.kanban.api.task;

import edu.duan.app.kanban.validation.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetTaskColumnRequest {
    @UUID
    @NotBlank
    @NotNull
    private String taskId;
    @NotBlank(message = "Slug cannot be blank")
    @Size(max = 25, message = "Slug length must be less than or equal to 25 characters")
    private String columnSlug;
}
