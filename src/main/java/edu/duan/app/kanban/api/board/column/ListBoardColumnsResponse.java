package edu.duan.app.kanban.api.board.column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListBoardColumnsResponse {
    private List<BoardColumnDTO> columns;
}
