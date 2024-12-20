package edu.duan.app.kanban.service;

import edu.duan.app.kanban.api.task.TaskDTO;
import edu.duan.app.kanban.mapper.TaskMapper;
import edu.duan.app.kanban.model.BoardColumnEntity;
import edu.duan.app.kanban.model.TaskEntity;
import edu.duan.app.kanban.repository.BoardColumnRepository;
import edu.duan.app.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardColumnRepository boardColumnRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, BoardColumnRepository boardColumnRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.boardColumnRepository = boardColumnRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public TaskDTO createTask(TaskDTO task) {
        TaskEntity taskEntity = taskRepository.save(taskMapper.toEntity(task));
        return taskMapper.toDTO(taskEntity);
    }

    public TaskDTO getTaskById(long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        return taskMapper.toDTO(taskEntity);
    }

    @Transactional
    public TaskDTO setColumnToTask(long taskId, String columnSlug) {
        Optional<BoardColumnEntity> boardColumnEntity = boardColumnRepository.findBySlug(columnSlug);
        if (taskRepository.existsById(taskId) && boardColumnEntity.isPresent()) {
            TaskEntity taskEntity = taskRepository.getReferenceById(taskId);
            BoardColumnEntity boardColumn = boardColumnEntity.get();
            taskEntity.setActiveColumn(boardColumn);
            return taskMapper.toDTO(taskEntity);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task or column does not exist");
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO updateTask(TaskDTO updatedTask) {
        if (taskRepository.existsById(updatedTask.getId())) {
            TaskEntity taskEntity = taskRepository.save(taskMapper.toEntity(updatedTask));
            return taskMapper.toDTO(taskEntity);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }
}