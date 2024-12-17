package edu.duan.app.kanban.api.board.column;

import edu.duan.app.kanban.validation.UUID;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardColumnRequest {
    @UUID
    @NotBlank
    @NotNull
    private String columnId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name length must be less than or equal to 100 characters")
    private String name;

    @NotBlank(message = "Slug cannot be blank")
    @Size(max = 25, message = "Slug length must be less than or equal to 25 characters")
    private String slug;

    @Pattern(regexp = "^#[A-Fa-f0-9]{6}$", message = "HexColor must be in the format #RRGGBB")
    private String hexColor;

    private boolean active;

    @Min(value = 0, message = "Position must be a positive number")
    private int position;
}
