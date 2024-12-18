package edu.duan.app.kanban.mapper;

import edu.duan.app.kanban.api.task.AddTaskRequest;
import edu.duan.app.kanban.api.task.TaskDTO;
import edu.duan.app.kanban.api.task.UpdateTaskRequest;
import edu.duan.app.kanban.model.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDTO toDTO(AddTaskRequest request) {
        return TaskDTO.builder()
                .withTitle(request.getTitle())
                .withDescription(request.getDescription())
                .withAssignee(request.getAssignee())
                .withDeadlineTime(request.getDeadlineTime())
                .build();
    }

    public TaskDTO toDTO(UpdateTaskRequest request) {
        return TaskDTO.builder()
                .withId(request.getTaskId())
                .withTitle(request.getTitle())
                .withDescription(request.getDescription())
                .withAssignee(request.getAssignee())
                .withDeadlineTime(request.getDeadlineTime())
                .build();
    }

    public TaskEntity toEntity(TaskDTO dto) {
        TaskEntity entity = new TaskEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setAssignee(dto.getAssignee());
        entity.setCreatedTime(DateTimeMapper.toTimestamp(dto.getCreatedTime()));
        entity.setDeadlineTime(DateTimeMapper.toTimestamp(dto.getDeadlineTime()));
        entity.setLastUpdatedTime(DateTimeMapper.toTimestamp(dto.getLastUpdatedTime()));
        return entity;
    }

    public TaskDTO toDTO(TaskEntity entity) {
        return TaskDTO.builder()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withDescription(entity.getDescription())
                .withAssignee(entity.getAssignee())
                        .withCreatedTime(DateTimeMapper.toLocalDateTime(entity.getCreatedTime()))
                        .withDeadlineTime(DateTimeMapper.toLocalDateTime(entity.getDeadlineTime()))
                        .withLastUpdatedTime(DateTimeMapper.toLocalDateTime(entity.getLastUpdatedTime()))
                .build();
    }
}
