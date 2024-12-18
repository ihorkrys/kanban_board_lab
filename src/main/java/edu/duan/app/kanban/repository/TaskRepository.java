package edu.duan.app.kanban.repository;

import edu.duan.app.kanban.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, String> {
}