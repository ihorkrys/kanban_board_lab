package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.board.column.BoardColumnDTO;
import edu.duan.app.kanban.api.context.GetAppContextResponse;
import edu.duan.app.kanban.api.task.TaskDTO;
import edu.duan.app.kanban.service.BoardColumnService;
import edu.duan.app.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/context")
public class AppContextController {
    private final TaskService taskService;
    private final BoardColumnService boardColumnService;
    @Autowired
    public AppContextController(TaskService taskService, BoardColumnService boardColumnService) {
        this.taskService = taskService;
        this.boardColumnService = boardColumnService;
    }

    @GetMapping("/load")
    public ResponseEntity<GetAppContextResponse> getAppContext() {
        List<BoardColumnDTO> boardColumns = boardColumnService.getActiveBoardColumns();

        List<TaskDTO> allTasks = taskService.getAllTasks();
        Map<String, List<TaskDTO>> tasksGroupedByColumnSlug = allTasks.stream()
                .filter(taskDTO -> taskDTO.getActiveColumn() != null)
                .collect(Collectors.groupingBy(
                        taskDTO -> taskDTO.getActiveColumn().getSlug()
                )
        );

        GetAppContextResponse response = new GetAppContextResponse(boardColumns, tasksGroupedByColumnSlug);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
