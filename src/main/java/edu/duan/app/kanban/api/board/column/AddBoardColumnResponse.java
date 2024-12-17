package edu.duan.app.kanban.api.board.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBoardColumnResponse {
    private BoardColumnDTO boardColumn;
}
