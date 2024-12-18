package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.task.*;
import edu.duan.app.kanban.mapper.TaskMapper;
import edu.duan.app.kanban.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<AddTaskResponse> addTask(@Valid @RequestBody AddTaskRequest request) {
        AddTaskResponse response = new AddTaskResponse(taskService.createTask(taskMapper.toDTO(request)));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponse> getTask(@PathVariable String id) {
        GetTaskResponse response = new GetTaskResponse(taskService.getTaskById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateTaskResponse> updateTask(@Valid @RequestBody UpdateTaskRequest request) {
        TaskDTO updatedTask = taskService.updateTask(taskMapper.toDTO(request));
        return new ResponseEntity<>(new UpdateTaskResponse(updatedTask), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<RemoveTaskResponse> removeTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(new RemoveTaskResponse(), HttpStatus.OK);
    }

    @PostMapping("/set-column")
    public ResponseEntity<SetTaskColumnResponse> setTaskColumn(@Valid @RequestBody SetTaskColumnRequest request) {
        TaskDTO taskDTO = taskService.setColumnToTask(request.getTaskId(), request.getColumnSlug());
        return new ResponseEntity<>(new SetTaskColumnResponse(taskDTO), HttpStatus.OK);
    }
}
