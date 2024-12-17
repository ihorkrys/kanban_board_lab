package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.board.column.BoardColumnDTO;
import edu.duan.app.kanban.api.context.GetAppContextResponse;
import edu.duan.app.kanban.api.task.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/context")
public class AppContextController {
    @GetMapping("/load")
    public ResponseEntity<GetAppContextResponse> getAppContext() {
        BoardColumnDTO todo = new BoardColumnDTO(UUID.randomUUID().toString(), "To Do", "todo", "#FF5733", true, 1);
        BoardColumnDTO inprogress = new BoardColumnDTO(UUID.randomUUID().toString(), "In Progress", "inprogress", "#33C4FF", true, 2);
        List<BoardColumnDTO> boardColumns = List.of(
                new BoardColumnDTO(UUID.randomUUID().toString(), "To Do", "todo", "#FF5733", true, 1),
                new BoardColumnDTO(UUID.randomUUID().toString(), "In Progress", "inprogress", "#33C4FF", true, 2)
        );

        Map<String, List<TaskDTO>> tasksGroupedByColumnSlug = Map.of(
                "todo", List.of(
                        new TaskDTO(UUID.randomUUID().toString(), "Task 1", "Description 1", "John Doe", null, null, null, List.of(), todo)
                ),
                "inprogress", List.of(
                        new TaskDTO(UUID.randomUUID().toString(), "Task 2", "Description 2", "Jane Doe", null, null, null, List.of(), inprogress)
                )
        );

        GetAppContextResponse response = new GetAppContextResponse(boardColumns, tasksGroupedByColumnSlug);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
