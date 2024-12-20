package edu.duan.app.kanban.api.board.column;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumnDTO {

    private long id;

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