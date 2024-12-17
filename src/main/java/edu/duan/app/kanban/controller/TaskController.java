package edu.duan.app.kanban.controller;

import edu.duan.app.kanban.api.task.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @PostMapping("/add")
    public ResponseEntity<AddTaskResponse> addTask(@Valid @RequestBody AddTaskRequest request) {

        AddTaskResponse response = new AddTaskResponse(new TaskDTO());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponse> getTask(@PathVariable String id) {
        GetTaskResponse response = new GetTaskResponse(new TaskDTO());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateTaskResponse> updateTask(@Valid @RequestBody UpdateTaskRequest request) {
        UpdateTaskResponse response = new UpdateTaskResponse(new TaskDTO());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<RemoveTaskResponse> removeTask(@PathVariable String id) {
        RemoveTaskResponse response = new RemoveTaskResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/set-column")
    public ResponseEntity<SetTaskColumnResponse> setTaskColumn(@Valid @RequestBody SetTaskColumnRequest request) {
        SetTaskColumnResponse response = new SetTaskColumnResponse(new TaskDTO());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
