package edu.duan.app.kanban.api.context;

import edu.duan.app.kanban.api.board.column.BoardColumnDTO;
import edu.duan.app.kanban.api.task.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAppContextResponse {
    private List<BoardColumnDTO> boardColumnsSortedByPriority;
    private Map<String, List<TaskDTO>> tasksGroupedByColumnSlug;
}
